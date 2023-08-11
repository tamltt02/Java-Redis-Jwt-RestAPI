package solutions.ntq.social.ntq_fresher_social.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private String password;

    private String department;

    private String bio;

}
