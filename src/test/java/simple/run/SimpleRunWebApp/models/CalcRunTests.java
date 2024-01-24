package simple.run.SimpleRunWebApp.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcRunTests {
    @Test
    public void simpleTest(){
        CalcRun.Distance distance = new CalcRun.Distance(10, 0);
        CalcRun.Time time = new CalcRun.Time(1, 0,0 );
        CalcRun calcRun = new CalcRun(time, distance);
        Assertions.assertEquals(10.0f, calcRun.getSpeed());
        Assertions.assertEquals(0, calcRun.getPace().hour);
        Assertions.assertEquals(6, calcRun.getPace().minute);
        Assertions.assertEquals(0, calcRun.getPace().seconds);
    }

    @Test
    public void nonCeilTest(){
        CalcRun.Distance distance = new CalcRun.Distance(10, 0);
        CalcRun.Time time = new CalcRun.Time(0, 45,0 );
        CalcRun calcRun = new CalcRun(time, distance);
        Assertions.assertEquals(13.3f, calcRun.getSpeed());
        Assertions.assertEquals(0, calcRun.getPace().hour);
        Assertions.assertEquals(4, calcRun.getPace().minute);
        Assertions.assertEquals(30, calcRun.getPace().seconds);
    }
}
