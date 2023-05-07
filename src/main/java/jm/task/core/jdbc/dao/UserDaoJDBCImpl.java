package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    UserDaoJDBCImpl() {

    }

    private void execUp(String sql) {
        try (Connection connection = Util.getMySQLConnection()) {
            connection.prepareStatement(sql).execute();
        } catch (SQLException | ClassNotFoundException e) {
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
        try (Connection connection = Util.getMySQLConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                insert into test.user (name, lastName, age)
                values (?,?,?);""");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    delete
                    from test.user
                    where id = ?;
                                        
                    """);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = Util.getMySQLConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(""" 
                            select *
                            from test.user;
                    """);
            ResultSet rs = preparedStatement.executeQuery();
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        execUp("""
                    truncate table user;
                """);
    }
}
