package solutions.ntq.social.NTQ_Social_Project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String fullName;
    private String password;
    private String department;
    private String bio;

}