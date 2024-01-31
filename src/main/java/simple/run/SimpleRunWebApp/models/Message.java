package simple.run.SimpleRunWebApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sender;
    private String time;
    private String text;

    public Message(String sender, String text_message) {
        this.sender = sender;
        this.time = LocalTime.now().toString();
        this.text = text_message;
    }
}
