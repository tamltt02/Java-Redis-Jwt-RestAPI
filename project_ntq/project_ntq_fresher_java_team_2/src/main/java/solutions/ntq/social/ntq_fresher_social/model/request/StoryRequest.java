package solutions.ntq.social.ntq_fresher_social.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryRequest {
//    private Long userId;
    private Long groupId;
    private String img;
    private String content;
    private PageRequest pageRequest;
}
