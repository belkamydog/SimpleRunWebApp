package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.run.SimpleRunWebApp.models.Weather;
import simple.run.SimpleRunWebApp.service.UserRepositoryService;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private Weather weather;

    @GetMapping
    public String getWeatherAboutPage(Model model) {
        model.addAttribute("weather", weather.getRootWeather());
        model.addAttribute("temperature", weather.getTemperature());
        return "weather/desc_weather";
    }

}
