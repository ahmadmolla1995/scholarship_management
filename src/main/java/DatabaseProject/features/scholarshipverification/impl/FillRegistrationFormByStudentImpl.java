package DatabaseProject.features.scholarshipverification.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.core.annotations.Service;
import DatabaseProject.features.scholarshipverification.usecases.FillRegistrationFormByStudent;
import DatabaseProject.models.Role;
import DatabaseProject.models.UniversityDegree;
import DatabaseProject.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Service
public class FillRegistrationFormByStudentImpl implements FillRegistrationFormByStudent {
    public void submitPersonalInfo(String username, String password, String firstName, String lastName,
                                   String personalID, String role, String degree, double gpa)
                                   throws TooLimitPasswordLengthException, UserAlreadyExistsException,
                                   InvalidUniversityDegreeException, InvalidUserRoleException, InvalidGPAException, SQLException {

        verifyUserName(username);
        verifyPassword(password);
        verifyUserRole(role);
        verifyDegree(degree);
        verifyGPA(gpa);

        User user = new User(
                null,
                firstName,
                lastName,
                username,
                password,
                personalID,
                Role.valueOf(role),
                gpa,
                UniversityDegree.valueOf(degree)
        );

        insertIntoDatabase(user);
    }

    private static void verifyUserName(String userName) throws SQLException, UserAlreadyExistsException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String uniqueUsernameCheckQuery = "select * from user where user_name = " + "\"" + userName + "\"" + ";";
        ResultSet resultSet = statement.executeQuery(uniqueUsernameCheckQuery);

        if (resultSet.next())
            throw new UserAlreadyExistsException("Username found in database! UserID should be unique");
    }

    private static void verifyPassword(String password) throws TooLimitPasswordLengthException {
        if(password.length() < 5)
            throw new TooLimitPasswordLengthException("Password should have more than 5 letters");
    }

    private static void verifyUserRole(String role) throws InvalidUserRoleException{
        if(!role.equals(Role.Student.toString()) && !role.equals(Role.Supervisor.toString()) && !role.equals(Role.Manager.toString()))
            throw new InvalidUserRoleException("User role is not valid!");
    }

    private static void verifyDegree(String degree) throws InvalidUniversityDegreeException{
        if(!degree.equals(UniversityDegree.Bachelor.toString()) && !degree.equals(UniversityDegree.Master.toString())
            && !degree.equals(UniversityDegree.Doctoral.toString()) && !degree.equals(UniversityDegree.Postdoctoral.toString()))
            throw new InvalidUniversityDegreeException("Degree is not valid!");
    }

    private static void verifyGPA(double gpa) throws InvalidGPAException{
        if(gpa < 0 || gpa > 20)
            throw new InvalidGPAException("GPA should be between 0 , 20");
    }

    private static void insertIntoDatabase(User user) throws SQLException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String sqlQuery = "insert into user values (" +
                null + ", " +
                "\"" + user.getFirstName() + "\"" + ", " +
                "\"" + user.getLastName() + "\"" + ", " +
                "\"" + user.getUserName() + "\"" + ", " +
                "\"" + user.getPassword() + "\"" + ", " +
                "\"" + user.getPersonalID() + "\"" + ", " +
                "\"" + user.getRole() + "\"" + ", " +
                user.getGPA() + ", " +
                "\"" + user.getDegree() + "\", " + "false);";

        statement.execute(sqlQuery);
    }
}

