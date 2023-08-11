package solutions.ntq.social.NTQ_Social_Project.document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "new_feed")
public class NewFeed {
    @Id
    private String id;
    private Long userId;
    private String content;

    private String image;

    private Long groupId;
    @Indexed(name="createNewFeedIndex", expireAfter ="1d")
    private Date createDate;
    private Date updateDate;
}
