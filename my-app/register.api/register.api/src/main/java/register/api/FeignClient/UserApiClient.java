package register.api.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import register.api.DTO.RegisterDTO;

@FeignClient(name = "USER.API", url = "http://localhost:8082")
public interface UserApiClient {

    @PostMapping("/api/user")
    ResponseEntity<String> createUser(@RequestBody RegisterDTO userDTO);
}