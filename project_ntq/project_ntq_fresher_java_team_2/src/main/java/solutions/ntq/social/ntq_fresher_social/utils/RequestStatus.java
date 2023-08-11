package solutions.ntq.social.ntq_fresher_social.utils;

public enum RequestStatus {
    SUCCESS("Request processed successfully"),
    ERROR("An error occurred while processing the request"),
    NOT_FOUND("The requested resource was not found"),
    ALLREADY_EXITS("Already exist!"),
    UNAUTHORIZED("You are not authorized to access this resource"),
    FORBIDDEN("Access to this resource is forbidden"),
    EXIST_GROUP("Group already exist"),
    DELETE_STATUS("Delete success!"),
    NOT_DELETE("Must not delete!");

    private final String message;
    RequestStatus(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}