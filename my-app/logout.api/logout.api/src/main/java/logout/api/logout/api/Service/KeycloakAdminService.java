package logout.api.logout.api.Service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Service
public class KeycloakAdminService {

    @Value("${keycloak.admin-url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public void revokeSessions(String userId) {
        String accessToken = getAdminAccessToken();

        String sessionUrl = String.format("%s/admin/realms/%s/users/%s/sessions", keycloakUrl, realm, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        new RestTemplate().exchange(sessionUrl, HttpMethod.DELETE, request, Void.class);
    }

    private String getAdminAccessToken() {
        String tokenUrl = keycloakUrl + "/realms/master/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map<String, String>> response = new RestTemplate().exchange(
    tokenUrl,
    HttpMethod.POST,
    request,
    new ParameterizedTypeReference<Map<String, String>>() {}
);
 Map<String, String> responseBody = response.getBody();
    if (responseBody != null && responseBody.containsKey("access_token")) {
        return responseBody.get("access_token");
    } 

    throw new RuntimeException("No se pudo obtener el token de acceso desde Keycloak");
   
}
}