package solutions.ntq.social.ntq_fresher_social.service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solutions.ntq.social.ntq_fresher_social.entity.User;
import solutions.ntq.social.ntq_fresher_social.model.dto.UserInforDTO;
import solutions.ntq.social.ntq_fresher_social.repositories.UserRepository;
import solutions.ntq.social.ntq_fresher_social.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String,Object> redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public UserInforDTO getUserInfor(String username){
        User user = findByUsername(username).get();
        UserInforDTO userInforDTO = modelMapper.map(user,UserInforDTO.class);
        return userInforDTO;
    }

    @Override
    public ResponseEntity<?> getUserInfoLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).get();
        UserInforDTO userInforDTO = modelMapper.map(user,UserInforDTO.class);
        return ResponseEntity.status(HttpStatus.OK.value()).body(userInforDTO) ;
    }


}
