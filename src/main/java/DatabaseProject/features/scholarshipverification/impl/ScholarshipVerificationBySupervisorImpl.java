package DatabaseProject.features.scholarshipverification.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.Exceptions.InvalidScholarshipStatusException;
import DatabaseProject.core.annotations.Service;
import DatabaseProject.features.scholarshipverification.usecases.ScholarshipVerificationBySupervisor;
import DatabaseProject.models.ScholarshipStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ScholarshipVerificationBySupervisorImpl implements ScholarshipVerificationBySupervisor {
    public void accept(int scholarshipID) throws SQLException, ScholarshipIDNotFoundException, InvalidScholarshipStatusException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String existenceCheck = "select * from scholarship_request where scholarship_id = " + scholarshipID + ";";
        ResultSet resultSet = statement.executeQuery(existenceCheck);

        if (!resultSet.next())
            throw new ScholarshipIDNotFoundException("scholarship id not found");
        else if(!resultSet.getString("status").equals(ScholarshipStatus.RequestedByStudent.toString()))
            throw new InvalidScholarshipStatusException("scholarship status should be \"RequestedByStudent\"");
        else {
            String sqlQuery = "update scholarship_request set status = \"AcceptedBySupervisor\""
                    + "where scholarship_id = " + scholarshipID + ";";
            statement.execute(sqlQuery);
        }
    }
}
