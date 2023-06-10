package kata9.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))

public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(nullable = false, length = 500)
    @Length(min = 1, max = 500)
    private String username;


    @Column(nullable = false, length = 500)
    @Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
    private String email;

    @Column(nullable = false, length = 200)
    @Range(min = 0, max = 200)
    private int age;
    @Column(nullable = false)
    private String password;


    @ManyToMany
    private Set<Role> roles;


    public void setId(long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public boolean checkPassword(String password) {
        return this.password.equals(DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    public User() {
    }

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public User(long id, String username, String email, int age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
