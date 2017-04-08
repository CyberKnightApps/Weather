package com.cyberknight.weather;

import java.util.ArrayList;

/**
 * Created by Dhruv on 30-Mar-17.
 */

public class Charts {
    private String title1;
    private String title2;
    private ArrayList<Integer> bigDataList;

    public Charts(String title1, String title2, ArrayList<Integer> bigDataList) {
        this.title1 = title1;
        this.title2 = title2;
        this.bigDataList = bigDataList;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public ArrayList<Integer> getBigDataList() {
        return bigDataList;
    }

    public void setBigDataList(ArrayList<Integer> bigDataList) {
        this.bigDataList = bigDataList;
    }
}
