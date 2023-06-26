package kata9.controller;

import kata9.entity.User;
import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UsersRESTController {
    private final UserService service;


    @Autowired
    public UsersRESTController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<User>> userList(){
        return ResponseEntity.ok(service.getAllUsers());
    }
    @PostMapping(value = "/save")
    @ResponseBody
    public void add(@ModelAttribute("user") @Valid CreateUserDTO userDTO) {
        service.saveUser(userDTO);
    }


    @PatchMapping(value = "/change")
    @ResponseBody
    public void edit(@ModelAttribute("user") @Valid ChangeUserDTO userDTO) {
        service.changeUser(userDTO);

    }

    @DeleteMapping("/{id}")
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
