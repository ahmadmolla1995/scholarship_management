package DatabaseProject.Exceptions;

public class UserNotLoggedInException extends Exception {
    private String message;

    public UserNotLoggedInException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
