package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import simple.run.SimpleRunWebApp.models.User;
import simple.run.SimpleRunWebApp.models.Weather;
import simple.run.SimpleRunWebApp.service.UserRepositoryService;

@Controller
public class MainPageController {
    @Autowired
    private Weather weather;
    private final UserRepositoryService userRepositoryService;

    public MainPageController(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("weather", weather.getRootWeather());
        model.addAttribute("temperature", weather.getTemperature());
        return "index";
    }

    @GetMapping("/registration")
    public String registrationForm(){
        return "auth/createUser";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("repeat_password") String reply_password){
        if (password.equals(reply_password)){
            userRepositoryService.addAccount(new User(login, password));
        }
        return "auth/createUser";
    }
}
