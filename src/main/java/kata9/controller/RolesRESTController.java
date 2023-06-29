package kata9.controller;

import kata9.entity.Role;
import kata9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/admin/roles")
public class RolesRESTController {
    private final UserService service;
    @Autowired
    public RolesRESTController(UserService service) {
        this.service = service;
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Role>> userRoles(){
        return ResponseEntity.ok(service.getAllRoles());
    }
    @ExceptionHandler
    private ResponseEntity<String> Handler(SQLIntegrityConstraintViolationException violationException) {
        return new ResponseEntity<>("""
                {"message": "%s"}
                """.formatted(violationException.getMessage()), HttpStatusCode.valueOf(400));
    }

}
