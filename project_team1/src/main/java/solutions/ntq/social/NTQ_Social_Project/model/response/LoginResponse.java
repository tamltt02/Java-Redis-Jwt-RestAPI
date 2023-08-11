package solutions.ntq.social.NTQ_Social_Project.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import solutions.ntq.social.NTQ_Social_Project.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private User user;
    private String jwtToken;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
