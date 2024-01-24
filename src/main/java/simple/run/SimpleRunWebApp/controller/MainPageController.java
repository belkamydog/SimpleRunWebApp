package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import simple.run.SimpleRunWebApp.models.Weather;

@Controller
public class MainPageController {
    @Autowired
    private Weather weather;
    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("weather", weather.getRootWeather());
        model.addAttribute("temperature", weather.getTemperature());
        return "index";
    }
}
