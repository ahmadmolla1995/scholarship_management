package DatabaseProject.features.scholarshipverification.usecases;

import DatabaseProject.Exceptions.InvalidScholarshipStatusException;
import DatabaseProject.core.annotations.UseCase;

import java.sql.SQLException;

@UseCase(code = 102)
public interface ScholarshipRejectionByManager {
    void reject(int scholarshipID) throws ScholarshipIDNotFoundException, SQLException, InvalidScholarshipStatusException;

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
