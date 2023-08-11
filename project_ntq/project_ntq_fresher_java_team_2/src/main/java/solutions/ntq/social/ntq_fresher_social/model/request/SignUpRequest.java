package solutions.ntq.social.ntq_fresher_social.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "Invalid username!")
    private String username;
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,50}$", message = "Invalid password!")
    private String password;
    private String fullName;
    @Email(message = "Email error")
    @Pattern(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$", message = "Email error")
    private String email;
    private String department;
    private String bio;
}
