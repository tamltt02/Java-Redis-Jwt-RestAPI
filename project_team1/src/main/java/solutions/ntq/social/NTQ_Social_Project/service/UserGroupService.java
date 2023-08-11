package solutions.ntq.social.NTQ_Social_Project.service;

import org.springframework.http.ResponseEntity;
import solutions.ntq.social.NTQ_Social_Project.entity.Group;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.entity.UserGroup;
import java.util.List;

public interface UserGroupService {
     UserGroup saveUserGroup (UserGroup userGroup);
     boolean checkUserInGroup(User user, Group group);
     List<Long> getAllGroupOfUser(String idUser);
    ResponseEntity<?> addUser(User user, Group group);
}
