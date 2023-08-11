package solutions.ntq.social.NTQ_Social_Project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NewFeedDTO {
    private String newFeedId;
    private String userName;
    private String content;
    private String image;
    private Long groupId;
}
