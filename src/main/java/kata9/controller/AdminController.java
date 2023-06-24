package kata9.controller;

import kata9.entity.Role;
import kata9.entity.User;
import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService service;


    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getAdminPage(ModelMap model, @AuthenticationPrincipal User user, Authentication authentication) {
        model.addAttribute("allUsers", service.getAllUsers());
        model.addAttribute("allRoles", service.getAllRoles());
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "admin";
    }


    @GetMapping(value = "/users/save")
    public String addUserPage(Model model,@AuthenticationPrincipal User user,Authentication authentication) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", service.getAllRoles());
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "saveUser";
    }
    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<List<User>> userList(){
        return ResponseEntity.ok(service.getAllUsers());
    }
    @GetMapping(value = "/roles")
    @ResponseBody
    public ResponseEntity<List<Role>> userRoles(){
        return ResponseEntity.ok(service.getAllRoles());
    }

    @PostMapping(value = "/users/save")
    @ResponseBody
    public void add(@ModelAttribute("user") @Valid CreateUserDTO userDTO) {
        service.saveUser(userDTO);
    }


    @PatchMapping(value = "/users/change")
    @ResponseBody
    public void edit(@ModelAttribute("user") @Valid ChangeUserDTO userDTO) {
        service.changeUser(userDTO);

    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public void delete(@PathVariable("id") long id) {
        service.removeUserById(id);

    }


    @ExceptionHandler
    private ResponseEntity<String> Handler(SQLIntegrityConstraintViolationException violationException) {
        return new ResponseEntity<>("""
                {"message": "%s"}
                """.formatted(violationException.getMessage()), HttpStatusCode.valueOf(400));
    }

}
