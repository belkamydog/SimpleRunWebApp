package simple.run.SimpleRunWebApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String date;
    private int distanceKm;
    private int distanceMeters;
    private int timeHour;
    private int timeMinute;
    private int timeSeconds;
    private int paceMinute;
    private int paceSeconds;
    private float speed;
    private String description;


    public Training(Long userId, String date, int hour, int min, int sec, int km, int meters, String description){
        this.userId = userId;
        this.date = date;
        CalcRun calcRun = new CalcRun(hour, min, sec, km, meters);
        CalcRun.Time p = calcRun.getPace();
        paceMinute = p.getMinute();
        paceSeconds = p.seconds;
        speed = calcRun.getSpeed();
        distanceKm = km;
        distanceMeters = meters;
        timeHour = hour;
        timeMinute = min;
        timeSeconds = sec;
        this.description = description;
    }
}
