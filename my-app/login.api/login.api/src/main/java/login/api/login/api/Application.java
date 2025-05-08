package login.api.login.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "login.api.login.api")
@EnableFeignClients
@ComponentScan(basePackages = {"login.api.login.api","login.api.login.api.Service;", "login.api.login.api.Controller", "login.api.login.api.FeignClient"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
