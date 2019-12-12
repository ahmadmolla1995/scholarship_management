package DatabaseProject.features.scholarshipverification.usecases;

import DatabaseProject.Exceptions.InvalidScholarshipStatusException;
import DatabaseProject.core.annotations.UseCase;
import java.sql.SQLException;

@UseCase(code = 105)
public interface ScholarshipVerificationBySupervisor {
    void accept(int scholarshipID) throws SQLException, ScholarshipIDNotFoundException, InvalidScholarshipStatusException;

    class ScholarshipIDNotFoundException extends Exception {
        private String message;

        public ScholarshipIDNotFoundException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}

