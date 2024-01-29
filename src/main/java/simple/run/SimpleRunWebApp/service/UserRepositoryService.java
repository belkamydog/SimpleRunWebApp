package simple.run.SimpleRunWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.run.SimpleRunWebApp.models.User;
import simple.run.SimpleRunWebApp.repository.UserRepository;

import java.util.Optional;

@Service
public class UserRepositoryService {
    @Autowired
    private final UserRepository userRepository;

    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addAccount(User newUser){
        userRepository.save(newUser);
    }
    public Optional<User> findByLogin(String login){
        return Optional.ofNullable(userRepository.findByName(login));
    }
}
