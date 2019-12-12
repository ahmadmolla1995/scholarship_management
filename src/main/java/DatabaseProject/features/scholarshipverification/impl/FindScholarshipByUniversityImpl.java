package DatabaseProject.features.scholarshipverification.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.features.scholarshipverification.usecases.FindScholarshipByUniversity;
import DatabaseProject.models.ScholarshipStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindScholarshipByUniversityImpl implements FindScholarshipByUniversity {
    public void find() throws SQLException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String sqlQuery = "select * from scholarship_request where status = " + "\"" + ScholarshipStatus.AcceptedByManager.toString() + "\"" + ";";

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            System.out.printf("user_id:%d\t", resultSet.getInt("user_id"));
            System.out.printf("field:%s\t", resultSet.getString("field"));
            System.out.printf("university:%s\t", resultSet.getString("university"));
            System.out.printf("start_date:%s\n", resultSet.getString("start_date"));
        }
    }
}

