package simple.run.SimpleRunWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import simple.run.SimpleRunWebApp.configurations.WebSecurityConfig;
import simple.run.SimpleRunWebApp.models.Message;
import simple.run.SimpleRunWebApp.models.User;
import simple.run.SimpleRunWebApp.models.Weather;
import simple.run.SimpleRunWebApp.service.MessageService;
import simple.run.SimpleRunWebApp.service.UserRepositoryService;
import weather.app.model.weather.Root;

import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private Weather weather;
    @Autowired
    private final UserRepositoryService userRepositoryService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MessageService messageService;

    public MainPageController(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("weather", weather.getRootWeather());
        model.addAttribute("temperature", weather.getTemperature());
        model.addAttribute("userTop", userRepositoryService.getTopUsers()); // not safe!!!!
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
            userRepositoryService.addAccount(new User(login, passwordEncoder.encode(password)));
        }
        return "auth/createUser";
    }
    @PostMapping("/send_message")
    public String sendMessage(@RequestParam("text_message") String text, Model model){
        Message message = new Message(WebSecurityConfig.getCurrentUsername(), text);
        messageService.saveMessage(message);
        model.addAttribute("messages", messageService.getMessages());
        return "index";
    }

    @ModelAttribute("userLogin")
    public String getUserLogin(){
        if (userRepositoryService.findByLogin(WebSecurityConfig.getCurrentUsername()).isPresent()){
            return WebSecurityConfig.getCurrentUsername();
        }
        return "Unauthorized";
    }

    @ModelAttribute("weather")
    public Root getWeather(){
        return weather.getRootWeather();
    }
    @ModelAttribute("temperature")
    public int getTemperature(){
        return weather.getTemperature();
    }
    @ModelAttribute("userTop")
    public List<User> getTopUsers(){
        return userRepositoryService.getTopUsers();
    }

}
