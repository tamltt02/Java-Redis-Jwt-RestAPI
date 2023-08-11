package solutions.ntq.social.NTQ_Social_Project.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private HttpStatus status;
    private String message;
}
