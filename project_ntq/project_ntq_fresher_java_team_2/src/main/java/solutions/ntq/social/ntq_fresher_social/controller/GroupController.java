package solutions.ntq.social.ntq_fresher_social.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.ntq.social.ntq_fresher_social.model.request.GroupRequest;
import solutions.ntq.social.ntq_fresher_social.service.GroupService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group/")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("detail/{idGroup}")
    public ResponseEntity<?> detail(@PathVariable("idGroup") Long id){
        return groupService.getGroupById(id);
    }

    @PostMapping("addUserGroup/{idGroup}/{idUser}")
    public ResponseEntity<?> addUserGroup(@PathVariable("idUser") Long idUser, @PathVariable("idGroup") Long idGroup) {
        return groupService.addUserGroup(idUser,idGroup);
    }

    @PostMapping("createGroup")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupRequest groupRequest) {
        return groupService.createGroup(groupRequest);
    }
}
