package kata9.controller;

import kata9.entity.User;
import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class UsersController {
    private final UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping("/admin")

    public String getAdminPage(ModelMap model) {
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

    @GetMapping("/user")
    public String getUserPage(ModelMap model, Authentication authentication) {
        String authenticationName = authentication.getName();
        model.addAttribute("user", service.getUserByName(authenticationName));
        return "user";
    }

    @GetMapping(value = "/admin/users/save")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "saveUser";
    }

    @GetMapping(value = "/admin/users/change/{id}")
    public String editUserPage(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", service.getUserById(id));
        return "changeUser";
    }

    @PostMapping(value = "/api/users/save")
    public String add(@ModelAttribute("user") @Valid User user) {
        service.saveUser(user);
        return "redirect:/admin";
    }


    @PatchMapping(value = "api/users/change")
    public String edit(@ModelAttribute("user") @Valid User user) {
        service.changeUser(user);
        return "redirect:/admin";
    }

    @ExceptionHandler
    private ResponseEntity<String> Handler(SQLIntegrityConstraintViolationException violationException) {

        return new ResponseEntity<>("""
                {"message": "%s"}""".formatted(violationException.getMessage()), HttpStatusCode.valueOf(400));
    }


    @DeleteMapping("api/users/{id}")
    public String delete(@PathVariable("id") long id) {
        service.removeUserById(id);
        return "redirect:/admin";
    }
}
