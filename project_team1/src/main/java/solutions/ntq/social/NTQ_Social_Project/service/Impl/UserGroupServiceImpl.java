package solutions.ntq.social.NTQ_Social_Project.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.entity.Group;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.entity.UserGroup;
import solutions.ntq.social.NTQ_Social_Project.helper.ErrorMessage;
import solutions.ntq.social.NTQ_Social_Project.helper.SuccessMessage;
import solutions.ntq.social.NTQ_Social_Project.repository.UserGroupRepository;
import solutions.ntq.social.NTQ_Social_Project.service.UserGroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {

   private final UserGroupRepository userGroupRepository;

    @Override
    public UserGroup saveUserGroup(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }
    @Override
    public boolean checkUserInGroup(User user, Group group) {
        return userGroupRepository.findByUserAndGroup(user,group) ==null ? false : true;
    }
    @Override
    public List<Long> getAllGroupOfUser(String idUser) {
        List<Long>lst = userGroupRepository.findAllGroupOfUser(idUser);
        return lst;
    }
    @Override
    public ResponseEntity<?> addUser(User user, Group group) {
        if (!checkUserInGroup(user, group)) {
            UserGroup newUserGroup = new UserGroup(user, group);
            userGroupRepository.save(newUserGroup);
            return ResponseEntity.ok(SuccessMessage.CREATED_SUCCESSFULLY.getMessage());
        } else {
            return ResponseEntity.ok(ErrorMessage.USER_ALREADY_EXIT.getMessage());
        }
    }
}
