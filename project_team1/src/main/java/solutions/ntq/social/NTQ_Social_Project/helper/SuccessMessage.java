package solutions.ntq.social.NTQ_Social_Project.helper;

public enum SuccessMessage {
    CREATED_SUCCESSFULLY("Created successfully"),
    UPDATED_SUCCESSFULLY("Updated successfully"),
    DELETED_SUCCESSFULLY("Deleted successfully");
    private final String message;
    SuccessMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

