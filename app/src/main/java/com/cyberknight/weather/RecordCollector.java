package com.cyberknight.weather;

import com.cyberknight.weather.Database.BtpRecord;

import java.util.ArrayList;

/**
 * Created by Nirbhay on 10-04-2017.
 */

public class RecordCollector {

    private static ArrayList<BtpRecord> btpRecords = new ArrayList<BtpRecord>();

    public static void addRecord(BtpRecord record){
        btpRecords.add(record);
    }

    public static ArrayList<BtpRecord> getBtpRecords(){
        return btpRecords;
    }
}
