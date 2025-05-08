package register.api.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import register.api.DTO.RegisterDTO;
import register.api.FeignClient.UserApiClient;

@Service
public class RegisterService {
    private final UserApiClient userClient;
    private final KeycloakService keycloakService;

    public RegisterService(UserApiClient userClient, KeycloakService keycloakService) {
        this.userClient = userClient;
        this.keycloakService = keycloakService;
    }

    public ResponseEntity<String> registerUser(RegisterDTO registerDTO) {
        try{
            
            ResponseEntity<String> response = userClient.createUser(registerDTO);
            if (!response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(response.getStatusCode()).body("Error al crear usuario en la base de datos");
            }
            String keycloakResponse = keycloakService.createUserInKeycloak(registerDTO);
            if (keycloakResponse == null) {
               
            return ResponseEntity.status(500).body("Error al registrar usuario en Keycloak");
            }
     
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (FeignException feignException) {
            return ResponseEntity.status(feignException.status()).body("Error en la API de usuarios: " + feignException.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error inesperado: " + e.getMessage());
        }     
    }
}