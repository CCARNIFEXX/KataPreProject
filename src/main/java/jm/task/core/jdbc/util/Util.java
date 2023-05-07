package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    //jdbc:mysql://localhost:3306/test

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "test";
        String userName = "test";
        String password = "test";

       Class.forName("com.mysql.jdbc.Driver");

       String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

       return DriverManager.getConnection(connectionURL, userName, password);
    }
}
