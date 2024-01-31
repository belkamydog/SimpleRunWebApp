package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.run.SimpleRunWebApp.configurations.WebSecurityConfig;
import simple.run.SimpleRunWebApp.models.Training;
import simple.run.SimpleRunWebApp.models.User;
import simple.run.SimpleRunWebApp.models.Weather;
import simple.run.SimpleRunWebApp.service.TrainingRepositoryService;
import simple.run.SimpleRunWebApp.service.UserRepositoryService;
import weather.app.model.weather.Root;

import java.util.List;

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
        return "user_info/about_user";
    }

    @PostMapping("/delete_training/{id}")
    public String deleteTraining(@PathVariable Long id){
        trainingRepositoryService.deleteTraining(id);
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
        User tmp = userRepositoryService.findById(user_id).get();
        tmp.setDistanceTotal(tmp.getDistanceTotal() + distance_km);
        userRepositoryService.saveUser(tmp);
        return "user_info/about_user";
    }


    @ModelAttribute("weather")
    public Root weatherObject(){
        return weather.getRootWeather();
    }
    @ModelAttribute("trainings")
    public List<Training> trainingObject() {
        return trainingRepositoryService.getUserTrainings();
    }

    @ModelAttribute("aboutUser")
    public User userObject() {
        return repositoryService.findByLogin(WebSecurityConfig.getCurrentUsername()).get();
    }

    @ModelAttribute("temperature")
    public int temperatureObject() {
        return weather.getTemperature();
    }
}
