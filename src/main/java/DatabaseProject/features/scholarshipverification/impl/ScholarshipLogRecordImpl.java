package DatabaseProject.features.scholarshipverification.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DatabaseProject.DatabaseConfig;
import DatabaseProject.core.annotations.Entity;
import DatabaseProject.features.scholarshipverification.usecases.ScholarshipLogRecord;
import DatabaseProject.models.ScholarshipLog;
import DatabaseProject.models.ScholarshipStatus;


@Entity
public class ScholarshipLogRecordImpl implements ScholarshipLogRecord {
    public void recordScholarshipLog(String action, String date, Integer scholarshipID) throws SQLException, ScholarshipLogRecord.ScholarshipIDNotFoundException {
        ScholarshipLog log = new ScholarshipLog(null, ScholarshipStatus.valueOf(action), date, scholarshipID);
        insertLogIntoDatabase(log);
    }

    private static void insertLogIntoDatabase(ScholarshipLog log) throws SQLException, ScholarshipLogRecord.ScholarshipIDNotFoundException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String scholarship_id_existence = "select * from scholarship_request where scholarship_id = " + log.getScholarshipID() + ";";
        ResultSet resultSet = statement.executeQuery(scholarship_id_existence);
        if (!resultSet.next())
            throw new ScholarshipLogRecord.ScholarshipIDNotFoundException("scholarship id not found!");

        String sqlQuery = "insert into scholarship_log values(" +
                null + ", " +
                "\"" + log.getAction() + "\"" + ", " +
                "\"" + log.getDate() + "\"" + ", " +
                log.getScholarshipID() + ");";
        statement.execute(sqlQuery);
    }
}
