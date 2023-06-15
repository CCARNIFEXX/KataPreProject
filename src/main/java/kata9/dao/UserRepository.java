package kata9.dao;

import kata9.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String name);
    @Query("select r.name from User u join Role r where u.username = :username")
    Set<String> getRolesByUsername(@Param("username") String name);

}
