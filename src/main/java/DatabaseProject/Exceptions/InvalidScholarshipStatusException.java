package DatabaseProject.Exceptions;

public class InvalidScholarshipStatusException extends Exception {
    private String message;

    public InvalidScholarshipStatusException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
