package kata9.controller;

import kata9.entity.Role;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService service;


    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getAdminPage(ModelMap model, Authentication authentication) {
        model.addAttribute("allUsers", service.getAllUsers());
        model.addAttribute("allRoles", service.getAllRoles());
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "admin";
    }


    @GetMapping(value = "/users/save")
    public String addUserPage(Model model,Authentication authentication) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", service.getAllRoles());
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "saveUser";
    }

    @GetMapping(value = "/users/change/{id}")
    public String editUserPage(ModelMap model,Authentication authentication, @PathVariable("id") long id) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        model.addAttribute("roles", service.getAllRoleNames());
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "changeUser";
    }

    @PostMapping(value = "/users/save")
    public String add(@ModelAttribute("user") @Valid CreateUserDTO userDTO) {
        service.saveUser(userDTO);
        return "redirect:/admin";
    }


    @PatchMapping(value = "/users/change")
    public String edit(@ModelAttribute("user") @Valid ChangeUserDTO userDTO) {
        service.changeUser(userDTO);
        return "redirect:/admin";
    }

    @ExceptionHandler
    private ResponseEntity<String> Handler(SQLIntegrityConstraintViolationException violationException) {
        return new ResponseEntity<>("""
                {"message": "%s"}
                """.formatted(violationException.getMessage()), HttpStatusCode.valueOf(400));
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") long id) {
        service.removeUserById(id);
        return "redirect:/admin";
    }

}
