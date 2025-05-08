package login.api.login.api.Controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import login.api.login.api.Service.KeycloakService;
import login.api.login.api.Service.UserTokenService;
import login.api.login.api.DTO.UserTokenDTO;
import login.api.login.api.FeignClient.UserClient;

@RestController
@RequestMapping("/api")
public class UserTokenController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String pwd) {
        try {
            // Ahora validamos y obtenemos el userId como String (UUID)
            String userId = userClient.validatePassword(email, pwd);

            // Si la validación es correcta, generamos el token en Keycloak
            String token = keycloakService.generateToken(email, pwd);

            // Guardar el token en la base de datos con el userId asociado
            UserTokenDTO userTokenDTO = new UserTokenDTO();
            userTokenDTO.setUserid(userId); // UUID en String
            userTokenDTO.setToken(token);

            userTokenService.saveUser(userTokenDTO);

            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

