package DatabaseProject.Exceptions;

public class ScholarshipIDNotFoundException extends Exception {
    private String message;

    public ScholarshipIDNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
