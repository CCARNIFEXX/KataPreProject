package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public Long createUser(@RequestBody UserCreateDTO dto) {
        return userService.add(dto.getName(), dto.getEmail());
    }

    @GetMapping(value = "/users")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @PutMapping(value = "/user/{id}/email")
    public void updateUserEmail(@PathVariable(name = "id") Long id, @RequestBody String email) {
        userService.update(id, email);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }
}
