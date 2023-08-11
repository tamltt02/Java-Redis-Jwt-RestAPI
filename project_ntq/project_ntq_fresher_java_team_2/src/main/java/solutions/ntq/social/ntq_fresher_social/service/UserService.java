package solutions.ntq.social.ntq_fresher_social.service;

import org.springframework.http.ResponseEntity;
import solutions.ntq.social.ntq_fresher_social.entity.User;
import solutions.ntq.social.ntq_fresher_social.model.dto.UserInforDTO;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByUsername(String name); // Tìm kiếm User có tồn tai trong DB hay không?
    Boolean existsByUsername(String username);  // Kiểm tra xem Username cos tồn tại trong DB không?
    Boolean existsByEmail(String email); //  Kiểm tra xem email cos tồn tại trong DB không?
    UserInforDTO getUserInfor(String username);
    ResponseEntity<?> getUserInfoLogin();
}
