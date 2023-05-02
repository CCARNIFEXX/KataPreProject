package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        try {
            this.connection = Util.getMySQLConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet execQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int execUp(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        execUp("""
                        create table if not exists user
                        (
                            id       bigint auto_increment
                                primary key,
                            name     varchar(32) null,
                            lastName varchar(32) null,
                            age      int         null,
                            constraint User_pk2
                                unique (id)
                        );
                """);
    }

    public void dropUsersTable() {
        execUp("drop table if exists user;");
    }

    public void saveUser(String name, String lastName, byte age) {
        execUp("""
                insert into test.user (name, lastName, age)
                values ('%s','%s',%s);"""
                .formatted(name, lastName, age));
    }

    public void removeUserById(long id) {
        execUp("""
                delete
                from test.user
                where id = %s;
                """.formatted(id));
    }

    public List<User> getAllUsers() {
        try (ResultSet rs = execQuery("""
                select *
                from test.user;
                """
        )) {
            List<User> result = new ArrayList<>();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                result.add(user);
            }
            return result;
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    public void cleanUsersTable() {
        execUp("""
                delete
                from test.user
                where true;
                """);
    }
}
