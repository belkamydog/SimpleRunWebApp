package simple.run.SimpleRunWebApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class CalcRun {
    private Time time;
    private Distance distance;
    private Time pace;
    private float speed;

    public CalcRun(int hour, int min, int sec, int km, int meters){
        time = new Time(hour, min, sec);
        distance = new Distance(km, meters);
        pace = new Time();
        calculate();
    }

    public CalcRun(Time time, Distance distance) {
        this.time = time;
        this.distance = distance;
        pace = new Time();
        calculate();
    }

    private void calculate() {
        float val = distance.distanceToKmFloat() / time.timeToHourFloat();
        speed = (float) Math.round(val * 10) / 10;
        float pace = time.convertTimeToMinutes() / distance.distanceToKmFloat();
        Time.floatToTime(pace, this.pace);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Time {
        int hour;
        int minute;
        int seconds;

        private float timeToHourFloat() {
            return hour + minute / 60f + seconds / 3600f;
        }

        private float convertTimeToMinutes() {
            return minute + hour * 60 + seconds / 60f;
        }

        private static void floatToTime(float time, Time t) {
            t.setHour(0);
            t.setMinute((int) time);
            float sec = (time - (int) time) * 60;
            t.setSeconds(Math.round(sec));
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Distance {
        int kilometers;
        int meters;

        private float distanceToKmFloat() {
            return kilometers + meters / 1000f;
        }
    }
}
