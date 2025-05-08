package login.api.login.api.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GATEWAY.API", url = "http://localhost:8082")
public interface UserClient {

    @GetMapping("/api/user/validatePassword")
    String validatePassword(@RequestParam String email, @RequestParam String pwd);
}
