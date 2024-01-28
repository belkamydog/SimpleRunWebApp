package simple.run.SimpleRunWebApp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simple.run.SimpleRunWebApp.models.Weather;

@Configuration
public class Configurations {
    @Bean
    public Weather initWeather() {
        return new Weather("095013fbfdec555a78dfffb6689c49e8", 55.788f, 49.1221f, "metric", "ru");
    }
}
