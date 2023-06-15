package kata9.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;



@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Override
    public String getAuthority() {
        return name;
    }
}
