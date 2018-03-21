package com.cyberknight.weather.database;

import android.content.Context;

import com.cyberknight.weather.database.BtpDbSource;
import com.cyberknight.weather.database.BtpRecord;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Nirbhay on 10-04-2017.
 */

public class RecordCollector {

    private static Queue<BtpRecord> btpRecords = new LinkedList<BtpRecord>();
    //private static ArrayList<BtpRecord> btpOldRecords = new ArrayList<BtpRecord>();
    //Context context =this;
    public static void addRecord(BtpRecord record){
        btpRecords.add(record);
    }

    public static Queue<BtpRecord> getBtpRecords(){
        return btpRecords;
    }
}
