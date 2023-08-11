package solutions.ntq.social.ntq_fresher_social.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solutions.ntq.social.ntq_fresher_social.entity.User;
import solutions.ntq.social.ntq_fresher_social.model.request.SignInRequest;
import solutions.ntq.social.ntq_fresher_social.model.request.SignUpRequest;
import solutions.ntq.social.ntq_fresher_social.model.response.JwtResponse;
import solutions.ntq.social.ntq_fresher_social.security.jwt.JwtTokenProvider;
import solutions.ntq.social.ntq_fresher_social.security.userprincal.CustomUserDetails;
import solutions.ntq.social.ntq_fresher_social.service.AuthService;
import solutions.ntq.social.ntq_fresher_social.utils.RequestStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static solutions.ntq.social.ntq_fresher_social.utils.Constans.INFO_USER;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CacheManager cacheManager;
    private final RedisTemplate<String, Object> redisTemplate;
    Date now = new Date();

    @Override
    public ResponseEntity<?> signUp(SignUpRequest signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("already exist user");
        } else if (userService.existsByEmail(signUpForm.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("already exist email");
        }
        User user = User.builder()
                .username(signUpForm.getUsername())
                .fullName(signUpForm.getFullName())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .department(signUpForm.getDepartment())
                .bio(signUpForm.getBio())
                .build();
        userService.save(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpForm.getUsername(), signUpForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Date now = new Date();
        String jwt = jwtTokenProvider.createTokenForJwt(authentication, now);
        cacheManager.getCache("TokenJwtCache").put(user.getUsername(), jwt);
        redisTemplate.opsForValue().set(user.getUsername() + INFO_USER, user, 10l, TimeUnit.MINUTES);

        boolean check = checkExistenceForRedis(redisTemplate, user.getUsername());
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        JwtResponse jwtResponse = JwtResponse.builder()
                .token(jwt)
                .id(customUserDetails.getId())
                .username(customUserDetails.getUsername())
                .fullName(customUserDetails.getFullName())
                .department(customUserDetails.getDepartment())
                .bio(customUserDetails.getBio())
                .build();
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    public boolean checkExistenceForRedis(RedisTemplate<String, Object> redisTemplate, String keyRedis) {
        return redisTemplate.hasKey(keyRedis);
    }

    @Override
    public ResponseEntity<?> signIn(SignInRequest signInRequest) {
        try {
            String tokenCache = this.getTokenInRedis(signInRequest.getUsername());
            String token = null;
            if (tokenCache != null) {
                User userResponseRedis = (User) redisTemplate.opsForValue().get(signInRequest.getUsername() + INFO_USER);

                JwtResponse jwtResponse = JwtResponse.builder()
                        .token(tokenCache)
                        .id(userResponseRedis.getId())
                        .username(userResponseRedis.getUsername())
                        .fullName(userResponseRedis.getFullName())
                        .department(userResponseRedis.getDepartment())
                        .bio(userResponseRedis.getBio()).build();
                return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
            } else {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                signInRequest.getUsername(), signInRequest.getPassword()
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                token = jwtTokenProvider.createTokenForJwt(authentication, now);
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
                cacheManager.getCache("TokenJwtCache").put(customUserDetails.getUsername(), token);

                JwtResponse jwtResponse = JwtResponse.builder()
                        .token(token)
                        .id(customUserDetails.getId())
                        .username(customUserDetails.getUsername())
                        .fullName(customUserDetails.getFullName())
                        .department(customUserDetails.getDepartment())
                        .bio(customUserDetails.getBio()).build();
                return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
            }
        } catch (Exception exception) {
            log.error("Login exception");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(RequestStatus.ERROR.getMessage());
        }
    }

    public String getTokenInRedis(String username) {
        Cache.ValueWrapper valueWrapper = cacheManager.getCache("TokenJwtCache").get(username);        // Kiểm tra xem token có hợp lệ và tồn tại trong cache không
        if (valueWrapper != null) {
            String token = (String) valueWrapper.get();
            return token;
        }
        return null;
    }

    @Override
    public ResponseEntity logOut(HttpServletRequest httpServletRequest) {
        String token = jwtTokenProvider.getTokenJwtFromRequest(httpServletRequest);
        if (!jwtTokenProvider.validateToken(token)) {
            log.error("token invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(RequestStatus.ERROR.getMessage());
        }
        String username = jwtTokenProvider.getUserNameFromToken(token);
        String cacheJwt = this.getTokenInRedis(username);
        if (cacheJwt == null) {
            log.error("get cache error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(RequestStatus.ERROR.getMessage());
        } else {
            cacheManager.getCache("TokenJwtCache").evict(username);
            log.info("logout success");
            return ResponseEntity.status(HttpStatus.OK.value()).body(RequestStatus.SUCCESS.getMessage());
        }
    }
}
