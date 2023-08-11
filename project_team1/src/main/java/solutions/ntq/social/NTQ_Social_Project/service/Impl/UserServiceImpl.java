package solutions.ntq.social.NTQ_Social_Project.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.entity.RequestLog;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.exception.BadRequestException;
import solutions.ntq.social.NTQ_Social_Project.model.mapper.UserMapper;
import solutions.ntq.social.NTQ_Social_Project.model.request.CreateUserRequest;
import solutions.ntq.social.NTQ_Social_Project.model.request.JwtRequest;
import solutions.ntq.social.NTQ_Social_Project.model.response.JwtResponse;
import solutions.ntq.social.NTQ_Social_Project.model.response.LoginResponse;
import solutions.ntq.social.NTQ_Social_Project.model.response.UserInfoResponse;
import solutions.ntq.social.NTQ_Social_Project.repository.RequestLogRepository;
import solutions.ntq.social.NTQ_Social_Project.repository.UserRepository;
import solutions.ntq.social.NTQ_Social_Project.security.JwtTokenUtil;
import solutions.ntq.social.NTQ_Social_Project.service.UserService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@EnableCaching
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisTemplate template;
    public static final String HASH_KEY = "Users";

    @Override
    public ResponseEntity<?> saveUser(CreateUserRequest createUserRequest, HttpSession httpSession) throws BadRequestException {
        User user = userRepository.findByUserNameOrEmail(createUserRequest.getUserName(), createUserRequest.getEmail());
        if (user != null) {
            throw new BadRequestException("UserName or Email already exists in the system. Please try again!");
        } else {
            user = mapper.toUser(createUserRequest);
            userRepository.save(user);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            final String token = jwtTokenUtil.generateToken(userDetails);
            httpSession.setAttribute("user", user.getUserName());
            template.opsForHash().put(HASH_KEY, user.getUserName(), token);
            return ResponseEntity.ok(new LoginResponse(user, token));
        }

    }

    @Cacheable(cacheNames = "cache1", key = "'#key'")
    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return user;
    }

    @Override
    public User getUserFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return getUserByUserName(userName);
    }

    @Override
    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(JwtRequest authenticationRequest, HttpSession httpSession) throws Exception {
       authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = userRepository.findByUserName(authenticationRequest.getUsername());
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.save(user);
        httpSession.setAttribute("user", authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ResponseEntity<?> getInfo(String username) throws Exception {
        try {
            User currentUser = getUserByUserName(username);
            UserInfoResponse infoUserResponse = UserInfoResponse.builder()
                    .userName(currentUser.getUserName())
                    .fullName(currentUser.getFullName())
                    .email(currentUser.getEmail())
                    .department(currentUser.getDepartment())
                    .bio(currentUser.getBio())
                    .build();
            return ResponseEntity.ok(infoUserResponse);
        } catch (Exception e) {
            throw new Exception("USER_DISABLED", e);
        }
    }
}
