package solutions.ntq.social.ntq_fresher_social.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class GroupRequest {
    @NotBlank(message = "Missing group name")
    @Pattern(regexp = "^[a-zA-Z0-9, \\s]{10,50}$", message = "Invalid groupName!")
    private String nameGroup;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-, \\s]{0,200}$", message = "Invalid details!")
    private String details;
}
