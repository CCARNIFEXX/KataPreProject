package kata9.service;

import kata9.controller.ChangeUserDTO;
import kata9.controller.CreateUserDTO;
import kata9.dao.RoleRepository;
import kata9.dao.UserRepository;
import kata9.entity.Role;
import kata9.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
    public void saveUser(@Valid CreateUserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> roles = roleRepository.getByNameIn(userDTO.getRoles());
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changeUser(ChangeUserDTO newField) {
        userRepository.findById(newField.getId()).ifPresent(userFromDB -> {
            userFromDB.setUsername(newField.getUsername());
            userFromDB.setAge(newField.getAge());
            userFromDB.setEmail(newField.getEmail());
            if (!userFromDB.getPassword().equals(newField.getPassword())) {
                userFromDB.setPassword(passwordEncoder.encode(newField.getPassword()));
            }
            Set<Role> roles = roleRepository.getByNameIn(newField.getRoles());
            userFromDB.setRoles(roles);
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
    public List<String> getAllRoleNames() {
        return roleRepository.getAllNames();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User userByName = getUserByName(username);
            if (userByName == null) {
                throw new UsernameNotFoundException(username);
            }
            return userByName;
        } catch (Exception e) {
            throw new UsernameNotFoundException(username);
        }
    }
}
