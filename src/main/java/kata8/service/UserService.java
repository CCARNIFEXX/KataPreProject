package kata8.service;

import kata8.dao.DaoException;
import kata8.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws DaoException;

    void saveUser(User user) throws DaoException;

    void removeUserById(long id) throws DaoException;

    void changeUser(User user) throws DaoException;

    User getUserById(long id) throws DaoException;
}
