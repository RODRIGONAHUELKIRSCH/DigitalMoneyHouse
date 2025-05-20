package logout.api.logout.api.Service;

import org.springframework.http.ResponseEntity;

import logout.api.logout.api.FeignClient.LoginApiFeign;

public class LogoutService {
    
    private final LoginApiFeign feignClient;
    private final KeycloakAdminService keycloakAdminService;

    public LogoutService(LoginApiFeign feignClient, KeycloakAdminService keycloakAdminService) {
        this.feignClient = feignClient;
        this.keycloakAdminService = keycloakAdminService;
    }

    public void processLogout(String token) {
        // Paso 1: obtener el userId desde login.api
        ResponseEntity<String> response = feignClient.getUserIdByToken(token);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Token inválido o no encontrado");
        }

        String userId = response.getBody();

        // Paso 2: invalidar sesión en Keycloak
        keycloakAdminService.revokeSessions(userId);

        // Paso 3: eliminar token de la base de datos
        feignClient.deleteToken(token);
    }
}
