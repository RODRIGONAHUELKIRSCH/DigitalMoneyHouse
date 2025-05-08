package register.api.Service;

import java.util.Collections;
import java.util.HashMap;
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

import register.api.DTO.RegisterDTO;

@Service
public class KeycloakService {

    @Value("${keycloak.server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realmName}")
    private String keycloakRealm;

    @Value("${keycloak.client-id}")
    private String keycloakClientId;

    @Value("${keycloak.admin-user}")
    private String keycloakAdminUser;

    @Value("${keycloak.admin-password}")
    private String keycloakAdminPassword;

   private String getAdminToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = keycloakServerUrl + "/realms/DigitalMoneyHouse/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", keycloakClientId);
        body.add("realmName", keycloakRealm);
        body.add("username", keycloakAdminUser);
        body.add("password", keycloakAdminPassword);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        try {

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url, 
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

    public String createUserInKeycloak(RegisterDTO registerDTO) {
        RestTemplate restTemplate = new RestTemplate();
        String url = keycloakServerUrl + "/admin/realms/" + keycloakRealm + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getAdminToken());

        Map<String, Object> user = new HashMap<>();
        user.put("username", registerDTO.getEmail());
        user.put("email", registerDTO.getEmail());
        user.put("enabled", true);

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", registerDTO.getPwd());
        credentials.put("temporary", false);

        user.put("credentials", Collections.singletonList(credentials));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(user, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

        return response.getStatusCode().is2xxSuccessful() ? "Usuario creado en Keycloak" : null;
    }
}