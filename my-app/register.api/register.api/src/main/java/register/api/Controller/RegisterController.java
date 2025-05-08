package register.api.Controller;



import register.api.DTO.RegisterDTO;
import register.api.Service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterDTO userDTO) {
        return registerService.registerUser(userDTO);
    }
}