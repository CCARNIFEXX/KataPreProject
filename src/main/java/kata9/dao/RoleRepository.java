package kata9.dao;

import kata9.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    //Optional<Role> getByName(String name);
    Set<Role> getByNameIn(Set<String> names);
    @Query("select distinct r.name from Role r")
    List<String> getAllNames();

}
