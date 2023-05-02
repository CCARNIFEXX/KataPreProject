package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "test";
        String userName = "test";
        String password = "test";

        return getMySQLConnection(hostName, dbName, userName, password);
    } //jdbc:mysql://localhost:3306/test

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

       Class.forName("com.mysql.jdbc.Driver");

       String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

       return DriverManager.getConnection(connectionURL, userName, password);
    }
}
