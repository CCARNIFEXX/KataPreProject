package kata8.controller;

import kata8.dao.DaoException;
import kata8.entity.User;
import kata8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {
    private final UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap model) throws DaoException {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users/save")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "saveUser";
    }

    @PostMapping(value = "/users/save")
    public String addUser(@ModelAttribute("user") User user) throws DaoException {
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "users/change/{id}")
    public String editUser(ModelMap model, @PathVariable("id") long id) throws DaoException {
        model.addAttribute("user", service.getUserById(id));
        return "changeUser";
    }

    @PatchMapping(value = "users/change")
    public String edit(@ModelAttribute("user") User user) throws DaoException {
        service.changeUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUserById(@PathVariable("id") long id) throws DaoException {
        service.removeUserById(id);
        return "redirect:/users";
    }
}
