package simple.run.SimpleRunWebApp.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.run.SimpleRunWebApp.configurations.WebSecurityConfig;
import simple.run.SimpleRunWebApp.models.Training;
import simple.run.SimpleRunWebApp.repository.TraningRepository;

import java.util.List;

@Service
@Getter
public class TrainingRepositoryService {
    @Autowired
    private TraningRepository traningRepository;
    @Autowired
    UserRepositoryService userRepositoryService;

    public List<Training> getUserTrainings(){
        return traningRepository.findAllByUserId(userRepositoryService.findByLogin(WebSecurityConfig.getCurrentUsername()).get().getId());
    }

    public void saveTraining(Training training){
        traningRepository.save(training);
    }

}
