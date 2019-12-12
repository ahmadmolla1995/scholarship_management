package DatabaseProject.features.scholarshipverification.usecases;

import DatabaseProject.Exceptions.UserNotLoggedInException;
import DatabaseProject.core.annotations.UseCase;
import java.sql.SQLException;

@UseCase(code = 112)
public interface ScholarshipRequestByStudent {
    void insertRequestIntoDatabase(String userName, String destinationUniversity, String field, String startDate)
            throws SQLException, UserNotFoundException, UserNotLoggedInException;

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
