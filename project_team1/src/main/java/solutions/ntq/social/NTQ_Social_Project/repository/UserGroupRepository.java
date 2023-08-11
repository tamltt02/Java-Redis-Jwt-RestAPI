package solutions.ntq.social.NTQ_Social_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import solutions.ntq.social.NTQ_Social_Project.entity.Group;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.entity.UserGroup;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup,Long> {

    public UserGroup findByUserAndGroup(User user, Group group);
    @Query(value = "SELECT group_id FROM group_user WHERE user_id = :userID", nativeQuery = true)
    List<Long> findAllGroupOfUser(String userID);
}
