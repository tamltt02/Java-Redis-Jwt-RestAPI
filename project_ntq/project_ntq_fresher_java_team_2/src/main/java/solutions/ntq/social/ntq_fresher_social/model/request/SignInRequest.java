package solutions.ntq.social.ntq_fresher_social.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @NotBlank(message = "Email is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    private String password;
}
