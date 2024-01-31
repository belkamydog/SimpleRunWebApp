package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.run.SimpleRunWebApp.configurations.WebSecurityConfig;
import simple.run.SimpleRunWebApp.models.CalcRun;
import simple.run.SimpleRunWebApp.models.Weather;
import simple.run.SimpleRunWebApp.service.UserRepositoryService;
import weather.app.model.weather.Root;

@Controller
public class CalculateController {
    @Autowired
    private Weather weather;
    private CalcRun calcRun;
    @Autowired
    private UserRepositoryService userRepositoryService;

    @GetMapping("/calculate")
    public String calcMainPage(Model model){
        return "calc/calcIndex";
    }
    @PostMapping(path = "/calculate/calc")
    public String calculateRun(@RequestParam("time_hour") int time_hour,
                               @RequestParam("time_min") int time_min,
                               @RequestParam("time_sec") int time_sec,
                               @RequestParam("distance_km") int distance_km,
                               @RequestParam("distance_meters") int distance_meters,
                               Model model){
        calcRun = new CalcRun(time_hour, time_min, time_sec, distance_km, distance_meters);
        System.err.println(calcRun);
        model.addAttribute("resultPaceHour", calcRun.getPace().getHour());
        model.addAttribute("resultPaceMinute", calcRun.getPace().getMinute());
        model.addAttribute("resultPaceSeconds", calcRun.getPace().getSeconds());
        model.addAttribute("resultSpeed", calcRun.getSpeed());
        return "calc/calcIndex";
    }

    @ModelAttribute("calculateRunResult")
    public CalcRun calculateRun(){
        calcRun = new CalcRun();
        return calcRun;
    }

    @ModelAttribute("weather")
    public Root weatherObject(){
        return weather.getRootWeather();
    }
    @ModelAttribute("temperature")
    public int weatherTemp(){
        return weather.getTemperature();
    }

    @ModelAttribute("userLogin")
    public String getUserLogin(){
        if (userRepositoryService.findByLogin(WebSecurityConfig.getCurrentUsername()).isPresent()){
            return WebSecurityConfig.getCurrentUsername();
        }
        return "Unauthorized";
    }
}
