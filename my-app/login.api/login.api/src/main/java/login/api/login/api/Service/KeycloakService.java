package login.api.login.api.Service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakService {
    @Value("${keycloak.server-url}")
    private String keycloakUrl;

    @Value("${keycloak.realmName}")
    private String realmName;

    @Value("${keycloak.client-id}")
    private String clientId;


    public String generateToken(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();

        String tokenUrl = keycloakUrl + "/realms/" + realmName + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", clientId);
        body.add("username", email);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

    try {

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST, 
                request, 
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
    
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("access_token")) {
                return responseBody.get("access_token").toString();
            } else {
                throw new RuntimeException("No se encontró el access_token en la respuesta de Keycloak.");
            }
        } else {
            throw new RuntimeException("Error al obtener el token de Keycloak. Código de estado: " + response.getStatusCode());
        }
        } catch (Exception e) {

        System.err.println("Error al obtener el token de Keycloak: " + e.getMessage());
        throw new RuntimeException("Error al obtener el token de acceso de Keycloak.", e);
        }
    }   
}