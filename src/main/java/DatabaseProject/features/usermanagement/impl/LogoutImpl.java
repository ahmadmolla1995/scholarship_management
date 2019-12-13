package DatabaseProject.features.usermanagement.impl;

import DatabaseProject.DatabaseConfig;
import DatabaseProject.core.annotations.Service;
import DatabaseProject.features.usermanagement.usecases.Logout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class LogoutImpl implements Logout {
    public void logout(String userName) throws UserNotFoundException, SQLException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String existenceQuery = "select * from user where user_name = " + "\"" + userName + "\"" + ";";
        ResultSet resultSet = statement.executeQuery(existenceQuery);

        if(!resultSet.next())
            throw new UserNotFoundException("User not found! you can not logout!");
        else if(!resultSet.getBoolean("loggedin"))
            System.out.println("user is logged out now!");
        else {
            String sqlQuery = "update user set loggedin = false where user_name = " + "\"" + userName + "\"" + ";";
            statement.execute(sqlQuery);
        }
    }
}
