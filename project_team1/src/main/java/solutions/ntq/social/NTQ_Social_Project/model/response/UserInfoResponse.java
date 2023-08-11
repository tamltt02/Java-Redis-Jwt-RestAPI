package solutions.ntq.social.NTQ_Social_Project.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserInfoResponse {
    private String userName;
    private String email;
    private String fullName;
    private String department;
    private String bio;
}
