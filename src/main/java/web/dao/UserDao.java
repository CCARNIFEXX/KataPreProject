package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
   Long add(User user);
   List<User> getAll();
   User getById(Long id);
   void update(Long id, String email);
   void delete(Long id);
}
