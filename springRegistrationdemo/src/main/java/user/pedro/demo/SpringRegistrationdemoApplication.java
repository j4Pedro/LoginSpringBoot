package user.pedro.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath:kaptchaConfig.xml"})
public class SpringRegistrationdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRegistrationdemoApplication.class, args);
	}

}
