package solutions.ntq.social.ntq_fresher_social.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private int page;
    private int pageSize;
    private String sortBy;
    private String condition;
}

