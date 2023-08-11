package solutions.ntq.social.NTQ_Social_Project.helper;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    NEW_FEED_NOT_FOUND("New feed not found", HttpStatus.NOT_FOUND),
    INVALID_REQUEST("Invalid request", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXIT("User already exits",HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
