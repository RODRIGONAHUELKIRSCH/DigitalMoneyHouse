package logout.api.logout.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "logout.api.logout.api")
@EnableFeignClients
@ComponentScan(basePackages = {"logout.api.logout.api","logout.api.logout.api.Service;", "logout.api.logout.api.Controller"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
