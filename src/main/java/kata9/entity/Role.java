package kata9.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Id
    private String name;

    @Column
    private String authorities;

    @Override
    public String getAuthority() {
        return authorities;
    }
}
