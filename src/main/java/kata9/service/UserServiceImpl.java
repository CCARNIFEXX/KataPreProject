package kata9.service;

import kata9.dao.RoleRepository;
import kata9.dao.UserRepository;
import kata9.entity.Role;
import kata9.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword("$2a$10$PvlEeIEbB8QrBmMrWTcWCu67g.PmWKtNDvJr7yFSakeqGHeWypmEa");
        roleRepository.findById("ROLE_USER").ifPresent(role -> user.setRoles(Set.of(role)));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changeUser(User newField) {
        userRepository.findById(newField.getId()).ifPresent(userFromDB -> {
            userFromDB.setUsername(newField.getUsername());
            userFromDB.setAge(newField.getAge());
            userFromDB.setEmail(newField.getEmail());
            userRepository.save(userFromDB);
        });

    }
    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String name) {
        return userRepository.getUserByUsername(name);
    }
    @Override
    @Transactional(readOnly = true)
    public Set<String> getRolesByName(String name) {
        return  userRepository.getUserByUsername(name).getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    }
}
