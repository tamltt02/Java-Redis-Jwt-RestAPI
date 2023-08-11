package solutions.ntq.social.ntq_fresher_social.service;

import org.springframework.http.ResponseEntity;
import solutions.ntq.social.ntq_fresher_social.model.request.GroupRequest;

public interface GroupService {
    ResponseEntity<?> createGroup(GroupRequest groupRequest);
    ResponseEntity<?> getGroupById(Long id);
    ResponseEntity<?> addUserGroup(Long idUser, Long idGroup) ;
}
