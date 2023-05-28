package kata8.service;

import kata8.dao.DaoException;
import kata8.dao.UserDao;
import kata8.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() throws DaoException {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) throws DaoException {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) throws DaoException {
        userDao.removeUserById(id);
    }

    @Override
    @Transactional
    public void changeUser(User user) throws DaoException {
        userDao.changeUser(user);
    }

    @Override
    public User getUserById(long id) throws DaoException {
        return userDao.getUserById(id);
    }
}
