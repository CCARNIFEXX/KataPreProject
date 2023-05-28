package kata8.dao;

import kata8.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws DaoException;

    void saveUser(User user) throws DaoException;

    void removeUserById(long id) throws DaoException;

    void changeUser(User user) throws DaoException;

    User getUserById(long id) throws DaoException;
}
