package kata9.service;

import kata9.controller.ChangeUserDTO;
import kata9.controller.CreateUserDTO;
import kata9.entity.Role;
import kata9.entity.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {
    List<User> getAllUsers() ;

    void saveUser(@Valid CreateUserDTO user) ;

    void removeUserById(Long id) ;

    void changeUser(ChangeUserDTO userDTO) ;


    User getUserById(Long id);

    User getUserByName(String name);
    List<String> getAllRoleNames();
    List<Role> getAllRoles();
}
