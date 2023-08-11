package solutions.ntq.social.ntq_fresher_social.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import solutions.ntq.social.ntq_fresher_social.document.Story;

@Data
public class StoryDTO {
 
    private String id;
    private UserDTO user;
    private Long userId;
    private Long groupId;
    private String img;
    private String content;
    private Long createDay;
    public StoryDTO(Story story){
        BeanUtils.copyProperties(story,this);
    }

}
