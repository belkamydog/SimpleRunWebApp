package simple.run.SimpleRunWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.run.SimpleRunWebApp.repository.MessageRepository;
import simple.run.SimpleRunWebApp.models.Message;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message){
        messageRepository.save(message);
    }

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }
}
