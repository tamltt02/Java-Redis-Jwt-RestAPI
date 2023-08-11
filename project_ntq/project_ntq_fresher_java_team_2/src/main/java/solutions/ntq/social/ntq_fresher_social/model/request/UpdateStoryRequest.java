package solutions.ntq.social.ntq_fresher_social.model.request;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UpdateStoryRequest {
    private String id;
    private String img;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-, \\s]{0,500}$", message = "Invalid details!")
    private String content;
}
