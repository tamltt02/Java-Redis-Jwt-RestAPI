package solutions.ntq.social.NTQ_Social_Project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.ntq.social.NTQ_Social_Project.entity.Group;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.model.request.CreateGroupRequest;
import solutions.ntq.social.NTQ_Social_Project.service.GroupService;
import solutions.ntq.social.NTQ_Social_Project.service.UserGroupService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final UserGroupService userGroupService;

    @PostMapping("/create")
    public ResponseEntity<?> saveGroup(
            @RequestBody CreateGroupRequest groupRequest) {
        return groupService.saveGroup(groupRequest);
    }

    @GetMapping("/add/user={user}/group={group}")
    public ResponseEntity<?> addUser(
            @PathVariable User user,
            @PathVariable Group group
    ) {
        return userGroupService.addUser(user,group);
    }
}
