package kata9.controller;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
@Validated
public class ChangeUserDTO {

    private Long id;
    @Size(min = 1, max = 500)
    public void setUsername(String username) {
        this.username = username;
    }
    @Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
    public void setEmail(String email) {
        this.email = email;
    }
    @Size(min = 0, max = 200)
    public void setAge(int age) {
        this.age = age;
    }
    @NotBlank
    public void setPassword(String password) {
        this.password = password;
    }
    @Size(min = 1)
    @NotNull
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }


    private String username;

    private String email;

    private int age;

    private String password;

    private Set<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
