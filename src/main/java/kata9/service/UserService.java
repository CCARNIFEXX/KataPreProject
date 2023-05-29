package kata9.service;

import kata9.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() ;

    void saveUser(User user) ;

    void removeUserById(Long id) ;

    void changeUser(User user) ;

    User getUserById(Long id) ;
}
