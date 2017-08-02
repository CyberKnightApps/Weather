package com.cyberknight.weather;

import android.content.Context;

import com.cyberknight.weather.database.BtpDbSource;
import com.cyberknight.weather.database.BtpRecord;

import java.util.ArrayList;

/**
 * Created by Nirbhay on 10-04-2017.
 */

public class RecordCollector {

    private static ArrayList<BtpRecord> btpRecords = new ArrayList<BtpRecord>();
    private static ArrayList<BtpRecord> btpOldRecords = new ArrayList<BtpRecord>();
    //Context context =this;
    public static void addRecord(BtpRecord record){
        btpRecords.add(record);
    }



    public static ArrayList<BtpRecord> getBtpRecords(){
        return btpRecords;
    }



    public static void emptyList(){
        btpRecords = new ArrayList<>();
    }
}
