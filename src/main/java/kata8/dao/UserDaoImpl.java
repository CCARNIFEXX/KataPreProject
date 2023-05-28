package kata8.dao;

import kata8.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("From User order by 1").getResultList();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public void changeUser(User user) {
        em.merge(user);
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }
}
