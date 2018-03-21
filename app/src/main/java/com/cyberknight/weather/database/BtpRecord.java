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
    private String H2;
    private String CH4;
    private String C2H5OH;
    private String C3H8;
    private String C4H10;


    private Date tmpDate;

    public BtpRecord(String to_store){
        to_store = to_store.trim();
        String s[] = to_store.split(";");
        if (s.length==13) {
            tmpDate = Calendar.getInstance().getTime();
            time = tmpDate.toString();
            CO2 = "0";
            VOC = "0";
            CO = s[1];
            NO2 = s[2];
            NH3 = s[3];
            C3H8 =s[4] ;
            C4H10=s[5];
            CH4 = s[6];
            H2 = s[7];
            C2H5OH = s[8];
            temperature = s[9];
            humidity = s[10];
            light = s[11];
            pressure = s[12];

        }
        else{
            tmpDate = Calendar.getInstance().getTime();
            time = tmpDate.toString();
            CO2 = s[0];
            VOC = s[1];
            CO = s[2];
            NO2 = s[3];
            NH3 = s[4];
            C3H8 =s[5] ;
            C4H10=s[6];
            CH4 = s[7];
            H2 = s[8];
            C2H5OH = s[9];
            temperature = s[10];
            humidity = s[11];
            light = s[12];
            pressure = s[13];

        }
    }

    public String getH2() {
        return H2;
    }

    public void setH2(String h2) {
        H2 = h2;
    }

    public String getCH4() {
        return CH4;
    }

    public void setCH4(String CH4) {
        this.CH4 = CH4;
    }

    public String getC2H5OH() {
        return C2H5OH;
    }

    public void setC2H5OH(String c2H5OH) {
        C2H5OH = c2H5OH;
    }

    public String getC3H8() {
        return C3H8;
    }

    public void setC3H8(String c3H8) {
        C3H8 = c3H8;
    }

    public String getC4H10() {
        return C4H10;
    }

    public void setC4H10(String c4H10) {
        C4H10 = c4H10;
    }

    public BtpRecord(){}

    public String getDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return tmpDate.toString();
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

    public String getParam(int i){
        switch (i){
            case 0:
                return getTemperature();
            case 1:
                return getPressure();
            case 2:
                return getHumidity();
            case 3:
                return getLight();
            case 4:
                return getNO2();
            case 5:
                return getCO2();
            case 6:
                return getNH3();
            case 7:
                return getVOC();
            case 8:
                return getCO();
            case 9:
                return getH2();
            case 10:
                return getCH4();
            case 11:
                return getC2H5OH();
            case 12:
                return getC3H8();
            case 13:
                return getC4H10();
            default:
                return "";
        }
    }

    public String getName(int i){
        switch(i){
            case 0:
                return "Temperature";
            case 1:
                return "Pressure";
            case 2:
                return "Humidity";
            case 3:
                return "Light";
            case 4:
                return "NO2";
            case 5:
                return "CO2";
            case 6:
                return "NH3";
            case 7:
                return "VOC";
            case 8:
                return "CO";
            case 9:
                return "H2";
            case 10:
                return "CH4";
            case 11:
                return "C2H5OH";
            case 12:
                return "C3H8";
            case 13:
                return "C4H10";
            default:
                return "";
        }
    }

    public static String getNameOfParam(int i){
        switch(i) {
            case 0:
                return "Temperature";
            case 1:
                return "Pressure";
            case 2:
                return "Humidity";
            case 3:
                return "Light";
            case 4:
                return "NO2";
            case 5:
                return "CO2";
            case 6:
                return "NH3";
            case 7:
                return "VOC";
            case 8:
                return "CO";
            case 9:
                return "H2";
            case 10:
                return "CH4";
            case 11:
                return "C2H5OH";
            case 12:
                return "C3H8";
            case 13:
                return "C4H10";
            default:
                return "";
        }
    }
}
