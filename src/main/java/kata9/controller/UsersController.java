package kata9.controller;

import kata9.entity.User;
import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UsersController {
    private final UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users/save")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "saveUser";
    }

    @PostMapping(value = "/users/save")

    public String addUser(@ModelAttribute("user") @Valid User user) {
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "users/change/{id}")
    public String editUser(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", service.getUserById(id));
        return "changeUser";
    }

    @PatchMapping(value = "users/change")
    public String edit(@ModelAttribute("user") @Valid User user) {
        service.changeUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUserById(@PathVariable("id") long id) {
        service.removeUserById(id);
        return "redirect:/users";
    }
}
