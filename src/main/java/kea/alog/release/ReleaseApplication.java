package kea.alog.release;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReleaseApplication {

	public static void main(String[] args) {
		// SpringApplication.run(ReleaseApplication.class, args);
		SpringApplication app = new SpringApplication(ReleaseApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8085"));
		app.run(args);
	}

}
