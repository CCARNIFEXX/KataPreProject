package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    Long add(String name, String email);
    List<User> listUsers();
    User getById(Long id);
    void update(Long id, String email);
    void delete(Long id);

}
