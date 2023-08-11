package solutions.ntq.social.NTQ_Social_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solutions.ntq.social.NTQ_Social_Project.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameOrEmail(String userName,String email);
    List<User> findByLastLoginTimeAfter(LocalDateTime time);
    User findByUserName(String userName);

}