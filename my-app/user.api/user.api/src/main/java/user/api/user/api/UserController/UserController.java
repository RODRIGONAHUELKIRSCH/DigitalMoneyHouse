package user.api.user.api.UserController;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import user.api.user.api.UserDTO.UserDTO;
import user.api.user.api.UserMapper.UserMapper;
import user.api.user.api.UserRepository.UserRepository;
import user.api.user.api.UserService.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository urep;

    @Autowired
    UserMapper umap;

    @Autowired 
    UserService uservice;

    @Autowired
    private Validator validator;

   @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = uservice.getUsers();
        return ResponseEntity.ok(users); 
    }
    
@PostMapping
public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
    try {

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        if (!violations.isEmpty()) {
            Map<String, String> errors = violations.stream()
                .collect(Collectors.toMap(
                    violation -> violation.getPropertyPath().toString(),
                    ConstraintViolation::getMessage
                ));

            return ResponseEntity.badRequest().body("Errores de validación: " + errors);
        }

        UserDTO createdUser = uservice.saveUser(userDTO);

        return ResponseEntity.ok(createdUser);

    } catch (IllegalArgumentException ex) {
  
        return ResponseEntity.badRequest().body("Error de validación: " + ex.getMessage());

    } catch (Exception ex) {

        String errorMessage = "Error 500: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        uservice.DeleteUser(id);
        return ResponseEntity.noContent().build(); 
    }

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/validatePassword")
    public ResponseEntity<String> validatePassword(@RequestParam String email, @RequestParam String pwd) {
        String userId = userService.validatePassword(email, pwd);
        return ResponseEntity.ok(userId);
    }

}
