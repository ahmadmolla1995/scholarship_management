package DatabaseProject.features.scholarshipverification.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.features.scholarshipverification.usecases.FindScholarshipByManager;
import DatabaseProject.models.ScholarshipStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindScholarshipByManagerImpl implements FindScholarshipByManager {
    public void find() throws SQLException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String sqlQuery = "select * from scholarship_request where status = " + "\"" + ScholarshipStatus.AcceptedBySupervisor.toString() + "\"" + ";";

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while (resultSet.next()) {
            System.out.printf("user_id:%d", resultSet.getInt("user_id"));
            System.out.printf("field:%s", resultSet.getString("field"));
            System.out.printf("university:%s", resultSet.getString("university"));
            System.out.printf("start_date:%s", resultSet.getString("start_date"));
        }
    }
}
