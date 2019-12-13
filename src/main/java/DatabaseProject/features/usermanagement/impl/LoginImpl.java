package DatabaseProject.features.usermanagement.impl;


import DatabaseProject.DatabaseConfig;
import DatabaseProject.features.usermanagement.usecases.Login;
import java.sql.*;

public class LoginImpl implements Login {
    public void login(String userName, String password) throws UserNotFoundException, SQLException {
        Statement statement = DatabaseConfig.getConnection().createStatement();
        String existsQuery = "select * from user where user_name = " + "\"" + userName + "\"" + ";";

        ResultSet resultSet = statement.executeQuery(existsQuery);

        if (!resultSet.next())
            throw new UserNotFoundException("user not found!");
        else if(resultSet.getBoolean("loggedin"))
            System.out.println("User is logged in now!");
        else if(!password.equals(resultSet.getString("password")))
            System.out.println("Password is wrong! Try again.");
        else {
            String sqlQuery = "update user set loggedin = true where user_name = " + "\"" + userName + "\"" + ";";
            statement.execute(sqlQuery);
        }
    }
}
