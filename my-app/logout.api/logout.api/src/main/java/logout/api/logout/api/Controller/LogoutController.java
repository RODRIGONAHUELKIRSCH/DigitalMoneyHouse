package logout.api.logout.api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import logout.api.logout.api.Service.LogoutService;
@RestController
@RequestMapping("/api")
public class LogoutController {
     private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer", "").trim();
        logoutService.processLogout(token);
        return ResponseEntity.ok("Logout exitoso");
    }
}
