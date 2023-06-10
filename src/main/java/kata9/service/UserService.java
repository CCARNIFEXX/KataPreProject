package kata9.service;

import kata9.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers() ;

    void saveUser(User user) ;

    void removeUserById(Long id) ;

    void changeUser(User user) ;


    User getUserById(Long id);

    User getUserByName(String name);

    @Transactional(readOnly = true)
    Set<String> getRolesByName(String name);
}
