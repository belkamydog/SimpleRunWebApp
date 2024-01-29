package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.run.SimpleRunWebApp.configurations.WebSecurityConfig;
import simple.run.SimpleRunWebApp.models.Training;
import simple.run.SimpleRunWebApp.models.Weather;
import simple.run.SimpleRunWebApp.service.TrainingRepositoryService;
import simple.run.SimpleRunWebApp.service.UserRepositoryService;
import weather.app.model.weather.Root;

import java.sql.Timestamp;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private Weather weather;
    @Autowired
    private UserRepositoryService repositoryService;
    @Autowired
    private TrainingRepositoryService trainingRepositoryService;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @GetMapping
    public String getIndexAccount(Model model){
        model.addAttribute("aboutUser", repositoryService.findByLogin(WebSecurityConfig.getCurrentUsername()));
        model.addAttribute("trainings", trainingRepositoryService.getUserTrainings());
        return "user_info/about_user";
    }

    @PostMapping("/add_training")
    public String addTraining(@RequestParam(name = "date") String date,
                              @RequestParam(name = "time_hour") int time_hour,
                              @RequestParam(name = "time_min") int time_min,
                              @RequestParam(name = "time_sec") int time_sec,
                              @RequestParam(name = "distance_km") int distance_km,
                              @RequestParam(name = "distance_meters") int distance_meters,
                              @RequestParam(name = "description") String description,
                              Model model){

        Long user_id = userRepositoryService.findByLogin(WebSecurityConfig.getCurrentUsername()).get().getId();
        trainingRepositoryService.saveTraining(new Training(user_id, date, time_hour, time_min, time_sec, distance_km, distance_meters, description));
        return "user_info/about_user";
    }


    @ModelAttribute("weather")
    public Root weatherObject(){
        return weather.getRootWeather();
    }
}
