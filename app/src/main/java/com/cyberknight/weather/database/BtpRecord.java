package com.cyberknight.weather.database;

/**
 * Created by Parth on 04-03-2017.
 * Project btapp.
 */

public class BtpRecord {
    private long id;
    private String time;
    private String temperature;
    private String pressure;
    private String humidity;
    private String light;
    private String NO2;
    private String NH3;
    private String CO2;
    private String VOC;
    private String Date;

    public BtpRecord(String to_store){
        to_store = to_store.trim();
        String s[] = to_store.split("  ");
        time = "0";
        temperature = s[0];
        pressure = s[1];
        humidity = s[2];
        light = s[3];
        NO2 = s[4];
        NH3 = s[5];
        CO2 = s[6];
        VOC = s[7];
    }

    public BtpRecord(){}

    public BtpRecord(long id, String time, String temperature, String pressure,
                     String humidity, String light, String NO2, String NH3, String CO2, String VOC) {
        this.id = id;
        this.time = time;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.light = light;
        this.NO2 = NO2;
        this.NH3 = NH3;
        this.CO2 = CO2;
        this.VOC = VOC;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getNO2() {
        return NO2;
    }

    public void setNO2(String NO2) {
        this.NO2 = NO2;
    }

    public String getNH3() {
        return NH3;
    }

    public void setNH3(String NH3) {
        this.NH3 = NH3;
    }

    public String getCO2() {
        return CO2;
    }

    public void setCO2(String CO2) {
        this.CO2 = CO2;
    }

    public String getVOC() {
        return VOC;
    }

    public void setVOC(String VOC) {
        this.VOC = VOC;
    }
}
