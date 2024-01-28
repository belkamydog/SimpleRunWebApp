package simple.run.SimpleRunWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SimpleRunWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleRunWebAppApplication.class, args);
	}

}
