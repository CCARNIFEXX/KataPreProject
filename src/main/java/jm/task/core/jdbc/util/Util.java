package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    //jdbc:mysql://localhost:3306/test

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        Properties properties = getDBProperties();
        String userName = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String connectionURL = properties.getProperty("hibernate.connection.url");
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    private static SessionFactory sessionFactory = buildSessionFactory();


    private static Properties getDBProperties() {
        Properties properties = new Properties();
        String appConfigPath = new File("src/main/resources/mySql.properties").getAbsolutePath();
        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (IOException ignored) {}
        return properties;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                sessionFactory = new Configuration()
                        .addProperties(getDBProperties())
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            }
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
