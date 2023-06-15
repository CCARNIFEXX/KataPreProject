package kata9.controller;

import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String getUserPage(ModelMap model, Authentication authentication) {
        String authenticationName = authentication.getName();
        model.addAttribute("user", service.getUserByName(authenticationName));
        return "user";
    }





}
