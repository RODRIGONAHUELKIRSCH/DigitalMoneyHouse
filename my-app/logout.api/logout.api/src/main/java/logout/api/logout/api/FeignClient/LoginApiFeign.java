package logout.api.logout.api.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "GATEWAY.API", url = "http://localhost:8082")
public interface LoginApiFeign {

    @GetMapping("/api/getuserid")
    ResponseEntity<String> getUserIdByToken(@RequestParam("token") String token);

    @DeleteMapping("/api/delete")
    ResponseEntity<String> deleteToken(@RequestParam("token") String token);

}


