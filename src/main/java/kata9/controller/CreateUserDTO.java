package kata9.controller;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
@Validated
public class CreateUserDTO {

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Size(min = 1, max = 500)
    private String username;
    @Pattern(regexp = "[^@]+@[^@]+\\.[^@]+")
    private String email;
    @Size(min = 0, max = 200)
    private int age;
    @NotBlank
    private String password;
    @Size(min = 1)
    private Set<String> roles;


}
