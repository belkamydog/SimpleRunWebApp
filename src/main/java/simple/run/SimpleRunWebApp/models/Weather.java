package simple.run.SimpleRunWebApp.models;

import weather.app.controller.GetWeather;

public class Weather extends GetWeather {
    public Weather(String appid, float latitude, float longitude, String units, String language) {
        super(appid, latitude, longitude, units, language);
    }
    public int getTemperature(){
        return (int) Math.round(this.getRootWeather().getMain().getTemp());
    }
}
