package kata8.service;

import kata8.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    void removeUserById(long id);

    void changeUser(User user);

    User getUserById(long id);
}
