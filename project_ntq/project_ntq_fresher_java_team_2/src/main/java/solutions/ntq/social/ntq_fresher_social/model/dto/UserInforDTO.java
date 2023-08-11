package solutions.ntq.social.ntq_fresher_social.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import solutions.ntq.social.ntq_fresher_social.entity.Group;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInforDTO {
    private Long id;
    private String username;
    private String fullName;
    private String department;
    private String bio;
    private List<Group> groups;
}
