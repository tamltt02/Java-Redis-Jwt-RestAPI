package solutions.ntq.social.NTQ_Social_Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
