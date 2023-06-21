package kata9.controller;

import kata9.entity.User;
import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UsersController {
    private final UserService service;

    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getUserPage(ModelMap model, @AuthenticationPrincipal User user, Authentication authentication) {
        model.addAttribute("user", user);
        model.addAttribute("currentUser", HeaderUtils.getUserName(authentication));
        model.addAttribute("currentRoles",HeaderUtils.getRoles(authentication));
        return "user";
    }





}
