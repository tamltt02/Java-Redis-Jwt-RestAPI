package solutions.ntq.social.NTQ_Social_Project.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.entity.Group;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.entity.UserGroup;
import solutions.ntq.social.NTQ_Social_Project.model.request.CreateGroupRequest;
import solutions.ntq.social.NTQ_Social_Project.repository.GroupRepository;
import solutions.ntq.social.NTQ_Social_Project.service.GroupService;
import solutions.ntq.social.NTQ_Social_Project.service.UserGroupService;
import solutions.ntq.social.NTQ_Social_Project.service.UserService;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
   private final GroupRepository groupRepository;
   private final UserService userService;
   private final UserGroupService userGroupService;

    @Override
    public ResponseEntity<?> saveGroup(CreateGroupRequest groupCreate) {
        User currentUser= userService.getUserFromToken();
        Group newGroup = new Group( groupCreate.getName(), groupCreate.getDescription());
        UserGroup newUserGroup = new UserGroup(currentUser, newGroup);
        groupRepository.save(newGroup);
        userGroupService.saveUserGroup(newUserGroup);
        return ResponseEntity.ok(newGroup);
    }

}
