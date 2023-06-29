package kata9.controller;

import kata9.entity.User;
import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    private final UserService service;

    @Autowired
    public ViewController(UserService service) {
        this.service = service;
    }
    @GetMapping(value = "/admin")
    public String getAdminPage(ModelMap model, @AuthenticationPrincipal User user, Authentication authentication) {
        model.addAttribute("allUsers", service.getAllUsers());
        model.addAttribute("allRoles", service.getAllRoles());
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "admin";
    }
    @GetMapping(value = "/admin/users/save")
    public String addUserPage(Model model, @AuthenticationPrincipal User user, Authentication authentication) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", service.getAllRoles());
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "saveUser";
    }
    @GetMapping("/user")
    public String getUserPage(ModelMap model, @AuthenticationPrincipal User user, Authentication authentication) {
        model.addAttribute("user", user);
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "user";
    }
}
