package solutions.ntq.social.ntq_fresher_social.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
    private int status;
    private Long totalRecords;
    private String message;
    private T data;

}
