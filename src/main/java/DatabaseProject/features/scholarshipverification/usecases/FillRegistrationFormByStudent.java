package DatabaseProject.features.scholarshipverification.usecases;

import DatabaseProject.core.annotations.UseCase;

import java.sql.SQLException;

@UseCase(code=101)
public interface FillRegistrationFormByStudent {
    void submitPersonalInfo(String username, String password, String firstName, String lastName, String personalID, String role, String degree, double gpa)
                            throws UserAlreadyExistsException, TooLimitPasswordLengthException, InvalidUserRoleException,
                            InvalidUniversityDegreeException, InvalidGPAException ,SQLException;

    class UserAlreadyExistsException extends Exception {
        private String message;

        public UserAlreadyExistsException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    class TooLimitPasswordLengthException extends Exception {
        private String message;

        public TooLimitPasswordLengthException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    class InvalidUserRoleException extends Exception {
        private String message;

        public InvalidUserRoleException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    class InvalidUniversityDegreeException extends Exception {
        private String message;

        public InvalidUniversityDegreeException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    class InvalidGPAException extends Exception {
        private String message;

        public InvalidGPAException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
