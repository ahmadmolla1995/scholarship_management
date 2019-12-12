package DatabaseProject.features.scholarshipverification.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.Exceptions.InvalidScholarshipStatusException;
import DatabaseProject.core.annotations.Service;
import DatabaseProject.features.scholarshipverification.usecases.ScholarshipRejectionByManager;
import DatabaseProject.features.scholarshipverification.usecases.ScholarshipVerificationByManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ScholarshipVerificationByManagerImpl implements ScholarshipVerificationByManager {
    public void accept(int scholarshipID) throws ScholarshipVerificationByManager.ScholarshipIDNotFoundException,
                                                SQLException, InvalidScholarshipStatusException {

        Statement statement = DatabaseConfig.getConnection().createStatement();
        String existenceCheck = "select * from scholarship_request where scholarship_id = " + scholarshipID + ";";
        ResultSet resultSet = statement.executeQuery(existenceCheck);

        if (!resultSet.next())
            throw new ScholarshipVerificationByManager.ScholarshipIDNotFoundException("scholarship id not found");
        else if (!resultSet.getString("status").equals("AcceptedBySupervisor"))
            throw new InvalidScholarshipStatusException("scholarship status should be \"AcceptedBySupervisor\"");
        else {
            String sqlQuery = "update scholarship_request set status = \"AcceptedByManager\""
                    + "where scholarship_id = " + scholarshipID + ";";
            statement.execute(sqlQuery);
        }
    }
}
