package DatabaseProject.features.usermanagement.usecases;

import DatabaseProject.core.annotations.UseCase;

import java.sql.SQLException;

@UseCase(code = 106)
public interface Login {
    void login(String userName, String password) throws UserNotFoundException, SQLException;

    class UserNotFoundException extends Exception {
        private String message;

        public UserNotFoundException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
