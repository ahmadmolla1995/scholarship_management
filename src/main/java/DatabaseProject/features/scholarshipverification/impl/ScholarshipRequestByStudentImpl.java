package DatabaseProject.features.scholarshipverification.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.Exceptions.UserNotLoggedInException;
import DatabaseProject.core.annotations.Service;
import DatabaseProject.features.scholarshipverification.usecases.ScholarshipRequestByStudent;
import DatabaseProject.models.ScholarshipStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ScholarshipRequestByStudentImpl implements ScholarshipRequestByStudent {
    public void insertRequestIntoDatabase(String userName, String destinationUniversity, String field, String startDate)
            throws SQLException, UserNotFoundException, UserNotLoggedInException {

        Statement statement = DatabaseConfig.getConnection().createStatement();
        String existenceQuery = "select * from user where user_name = " + "\"" + userName + "\"" + ";";
        ResultSet resultSet = statement.executeQuery(existenceQuery);

        if (!resultSet.next())
            throw new UserNotFoundException("user not found!");
        else if (!resultSet.getBoolean("loggedin"))
            throw new UserNotLoggedInException("User is not logged in!");
        else {
            String sqlQuery = "insert into scholarship_request values (" +
                    null + ", " +
                    "\"" + ScholarshipStatus.RequestedByStudent.toString() + "\"" + ", " +
                    "\"" + field + "\"" + ", " +
                    "\"" + destinationUniversity + "\"" + ", " +
                    "\"" + startDate + "\"" + ", " +
                    "\"" + resultSet.getInt("id") + "\"" + ");";

            statement.execute(sqlQuery);
        }
    }
}
