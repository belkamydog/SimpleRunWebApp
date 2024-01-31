package simple.run.SimpleRunWebApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private boolean signIn;
    private float distanceTotal;

    public User(String login, String password){
        this.name = login;
        this.password = password;
        this.signIn = true;
    }
}
