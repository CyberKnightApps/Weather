package com.cyberknight.weather.Main;

import com.cyberknight.weather.Interface.DrawableIds;
import com.cyberknight.weather.R;

/**
 * Created by Parth on 30-03-2017.
 * Project btapp.
 */

public class OverviewValues {
    private int imageId;
    private int primaryColor;
    private int primaryDarkColor;
    private double currentVal;
    private double minVal;
    private double maxVal;
    private String type;

    public OverviewValues(){
        type = "";
    }

    public OverviewValues(String type, String curr, String max, String min) {
        this.currentVal = curr.length()==0?0:Double.parseDouble(curr);
        this.minVal = min.length()==0?0:Double.parseDouble(min);
        this.maxVal = max.length()==0?0:Double.parseDouble(max);
        this.type = type;
        applyColorAndIcon();
    }

    private void applyColorAndIcon(){
        switch(type){
            case "Temperature":
                double avgTemp = currentVal;
                if(avgTemp<10){
                    imageId = DrawableIds.COLD;
                    primaryColor = R.color.veryColdPrimary;
                    primaryDarkColor = R.color.veryColdPrimaryDark;
                }else if(avgTemp>=10 && avgTemp<20){
                    imageId = DrawableIds.CLOUDY;
                    primaryColor = R.color.coldPrimary;
                    primaryDarkColor = R.color.coldPrimaryDark;
                }else if(avgTemp>=20 && avgTemp<35){
                    imageId = DrawableIds.PARTLY_CLOUDY;
                    primaryColor = R.color.normalPrimary;
                    primaryDarkColor = R.color.normalPrimaryDark;
                }else if(avgTemp>=35 && avgTemp<42){
                    imageId = DrawableIds.CLEAR_SKY;
                    primaryColor = R.color.warmPrimary;
                    primaryDarkColor = R.color.warmPrimaryDark;
                }else{
                    imageId = DrawableIds.CLEAR_SKY;
                    primaryColor = R.color.hotPrimary;
                    primaryDarkColor = R.color.hotPrimaryDark;
                }
                break;
            case "Pressure":
                double avgPress = (maxVal+minVal)/2;
                imageId = DrawableIds.PRESSURE;
                primaryColor = R.color.green_300;
                primaryDarkColor = R.color.green_400;
                break;
            case "Humidity":
                double avgHumidity = (maxVal+minVal)/2;
                imageId = DrawableIds.HUMIDITY;
                primaryColor = R.color.light_blue_400;
                primaryDarkColor = R.color.light_blue_500;
                break;
            case "Light":
                double avgSpeed = (maxVal+minVal)/2;
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.warmPrimary;
                primaryDarkColor = R.color.warmPrimaryDark;
                break;
            case "NO2":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.teal_400;
                primaryDarkColor = R.color.teal_500;
                break;
            case "NH3":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.teal_400;
                primaryDarkColor = R.color.teal_500;
                break;
            case "CO2":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.normalPrimary;
                primaryDarkColor = R.color.normalPrimaryDark;
                break;
            case "VOC":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.light_blue_400;
                primaryDarkColor = R.color.light_blue_500;
                break;
            case "CO":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.hotPrimary;
                primaryDarkColor = R.color.hotPrimaryDark;
                break;
            case "H2":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.normalPrimary;
                primaryDarkColor = R.color.normalPrimaryDark;
                break;
            case "CH4":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.teal_400;
                primaryDarkColor = R.color.teal_500;
                break;
            case "C2H5OH":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.veryColdPrimary;
                primaryDarkColor = R.color.veryColdPrimaryDark;
                break;
            case "C3H8":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.light_blue_400;
                primaryDarkColor = R.color.light_blue_500;
                break;
            case "C4H10":
                imageId = DrawableIds.WIND_SPEED;
                primaryColor = R.color.teal_400;
                primaryDarkColor = R.color.teal_500;
                break;
            default:
                imageId = DrawableIds.NOT_APPLICABLE;
                primaryColor = R.color.grey_400;
                primaryDarkColor = R.color.grey_500;
        }
    }

    public int getImageId() {
        return imageId;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public int getPrimaryDarkColor() {
        return primaryDarkColor;
    }

    public double getCurrentVal() {
        return currentVal;
    }

    public double getMinVal() {
        return minVal;
    }

    public double getMaxVal() {
        return maxVal;
    }

    public String getType() {
        return type;
    }
}
