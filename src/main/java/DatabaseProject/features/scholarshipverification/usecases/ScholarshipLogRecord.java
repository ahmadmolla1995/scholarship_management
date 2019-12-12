package DatabaseProject.features.scholarshipverification.usecases;

import java.sql.SQLException;

public interface ScholarshipLogRecord {
    void recordScholarshipLog(String action, String date, Integer scholarshipID) throws SQLException, ScholarshipIDNotFoundException;

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

