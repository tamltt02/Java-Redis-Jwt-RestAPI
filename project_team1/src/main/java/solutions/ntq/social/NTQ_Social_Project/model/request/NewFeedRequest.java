package solutions.ntq.social.NTQ_Social_Project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewFeedRequest {

    private String id;
    private Long userId;
    @NotBlank(message = "Content Group is empty")
    private String content;
    private String image;
    private Long groupId;
    private Date createDate;
    private Date updateDate;
}
