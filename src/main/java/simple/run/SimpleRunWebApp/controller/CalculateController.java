package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.run.SimpleRunWebApp.models.Weather;

@Controller
@RequestMapping("/calculate")
public class CalculateController {
    @Autowired
    private Weather weather;
    @GetMapping
    public String calcMainPage(Model model){
        model.addAttribute("weather", weather.getRootWeather());
        model.addAttribute("temperature", weather.getTemperature());
        return "calc/calcIndex";
    }
}
