package com.palletech.weather_app;

import org.json.JSONObject;

/**
 * Created by user on 3/31/2017.
 */
public class Weather {
    Double SunRise, SunSet,temp, humidity, pressure,speed;

    public Weather(Double sunRise, Double sunSet, Double temp, Double humidity, Double pressure, Double speed) {
        SunRise = sunRise;
        SunSet = sunSet;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.speed = speed;
    }

    public Double getSunRise() {
        return SunRise;
    }

    public void setSunRise(Double sunRise) {
        SunRise = sunRise;
    }

    public Double getSunSet() {
        return SunSet;
    }

    public void setSunSet(Double sunSet) {
        SunSet = sunSet;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}


