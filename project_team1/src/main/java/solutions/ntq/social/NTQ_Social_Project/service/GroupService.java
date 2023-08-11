package solutions.ntq.social.NTQ_Social_Project.service;

import org.springframework.http.ResponseEntity;
import solutions.ntq.social.NTQ_Social_Project.entity.Group;
import solutions.ntq.social.NTQ_Social_Project.model.request.CreateGroupRequest;


public interface GroupService {
     ResponseEntity<?> saveGroup(CreateGroupRequest groupRequset);


}
