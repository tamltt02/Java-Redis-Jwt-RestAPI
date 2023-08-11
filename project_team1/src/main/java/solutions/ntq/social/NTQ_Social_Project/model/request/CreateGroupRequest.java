package solutions.ntq.social.NTQ_Social_Project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Setter
@Getter
public class CreateGroupRequest {
    @NotBlank(message = "Name Group is empty")
    private String name;
    private String description;
}
