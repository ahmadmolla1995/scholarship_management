package DatabaseProject.features.scholarshipverification.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.Exceptions.InvalidScholarshipStatusException;
import DatabaseProject.core.annotations.Service;
import DatabaseProject.features.scholarshipverification.usecases.ScholarshipRejectionByManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ScholarshipRejectionByManagerImpl implements ScholarshipRejectionByManager {
    public void reject(int scholarshipID) throws ScholarshipIDNotFoundException, SQLException, InvalidScholarshipStatusException {

        Statement statement = DatabaseConfig.getConnection().createStatement();
        String existenceCheck = "select * from scholarship_request where scholarship_id = " + scholarshipID + ";";
        ResultSet resultSet = statement.executeQuery(existenceCheck);

        if (!resultSet.next())
            throw new ScholarshipRejectionByManager.ScholarshipIDNotFoundException("scholarship id not found");
        else if (!resultSet.getString("status").equals("AcceptedBySupervisor"))
            throw new InvalidScholarshipStatusException("scholarship status should be AcceptedBySupervisor");
        else {
            String sqlQuery = "update scholarship_request set status = \"RejectedByManager\""
                    + "where scholarship_id = " + scholarshipID + ";";
            statement.execute(sqlQuery);
        }
        
    }
}
