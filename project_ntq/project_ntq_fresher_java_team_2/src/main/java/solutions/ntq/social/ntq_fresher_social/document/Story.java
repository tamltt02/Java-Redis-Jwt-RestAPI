package solutions.ntq.social.ntq_fresher_social.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import solutions.ntq.social.ntq_fresher_social.model.dto.UserDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private UserDTO user;
    private Long groupId;
    private String img;
    private String content;
    @CreatedDate
    private Long createDay;
}