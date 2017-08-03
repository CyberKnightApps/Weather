package com.cyberknight.weather.database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private String CO;
    private Date tmpDate;

    public BtpRecord(String to_store){
        to_store = to_store.trim();
        String s[] = to_store.split(";");
        tmpDate = Calendar.getInstance().getTime();
        time = tmpDate.toString();
        temperature = s[0];
        humidity = s[1];
        light = s[2];
        pressure = s[3];
        CO2 = s[4];
        VOC = s[5];
        NH3 = s[6];
        CO = s[7];
        NO2 = s[8];
    }

    public BtpRecord(){}

    public String getDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(tmpDate);
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

    public String getCO(){ return CO;}

    public void setCO(String CO){ this.CO = CO; }

}
