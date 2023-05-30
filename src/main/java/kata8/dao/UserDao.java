package kata8.dao;

import kata8.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user);

    void removeUserById(long id);

    void changeUser(User user);

    User getUserById(long id);
}
