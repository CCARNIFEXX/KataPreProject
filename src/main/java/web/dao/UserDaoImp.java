package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Long add(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void update(Long id, String email) {
        User user = getById(id);
        user.setEmail(email);
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        entityManager.remove(user);
    }

}
