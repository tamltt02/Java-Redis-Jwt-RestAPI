package solutions.ntq.social.NTQ_Social_Project.model.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateUserRequest {

    @NotBlank(message = "Name is empty")
    private String fullName;

    @NotBlank(message = "Email is empty")
    @Email(message = "Email invalidate")
    private String email;

    @NotBlank(message = "Password is empty")
    @Size(min = 6, max = 20, message = "Password must contain between 6-20 characters")
    private String password;

    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$", message = "Invalid username!")
    private String userName;

    private String department;

    private String bio;

    private LocalDateTime lastLoginTime;

}
