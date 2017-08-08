package com.cyberknight.weather.Firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.cyberknight.weather.Interface.OnGetDataListener;
import com.cyberknight.weather.database.BtpRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.*;

import java.util.ArrayList;

/**
 * Created by Nirbhay on 25-07-2017.
 */

public class Firebase {

    private static FirebaseDatabase database;
    private static DatabaseReference mRef;
    private static ProgressDialog progressDialog;
    public static void putAllData(ArrayList<BtpRecord> btpRecord){
        String name = Build.SERIAL.toString();
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference().child(name);
        for(int i = 0;i<btpRecord.size();i++){
            BtpRecord record = btpRecord.get(i);
            StringBuilder str = new StringBuilder();
            String temp[] = record.getTime().split(" ");
            str.append(temp[0]+" "+temp[1]+" "+temp[2]);
            Log.e("Firebase***",str.toString());
            mRef.child(str.toString()).child(record.getTime()).child("Temprature").setValue(record.getTemperature());
            mRef.child(str.toString()).child(record.getTime()).child("Humidity").setValue(record.getHumidity());
            mRef.child(str.toString()).child(record.getTime()).child("Presure").setValue(record.getPressure());
            mRef.child(str.toString()).child(record.getTime()).child("CO2").setValue(record.getCO2());
            mRef.child(str.toString()).child(record.getTime()).child("Light").setValue(record.getLight());
            mRef.child(str.toString()).child(record.getTime()).child("NH3").setValue(record.getNH3());
            mRef.child(str.toString()).child(record.getTime()).child("NO2").setValue(record.getNO2());
            mRef.child(str.toString()).child(record.getTime()).child("VOC").setValue(record.getVOC());
            mRef.child(str.toString()).child(record.getTime()).child("CO").setValue(record.getCO());
        }
    }

    public static BtpRecord getValue(Context c, String date, String time){
        final BtpRecord record = new BtpRecord();
        progressDialog = new ProgressDialog(c);
        get(date, time, new OnGetDataListener() {
            @Override
            public void onStart() {
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                progressDialog.dismiss();
                if (data.getValue()!=null){
                    record.setTemperature(data.child("Category 1").getValue().toString());
                    record.setHumidity(data.child("Category 2").getValue().toString());
                    record.setPressure(data.child("Category 3").getValue().toString());
                    record.setCO2(data.child("Category 4").getValue().toString());
                    record.setLight(data.child("Category 5").getValue().toString());
                    record.setNH3(data.child("Category 6").getValue().toString());
                    record.setNO2(data.child("Category 7").getValue().toString());
                    record.setVOC(data.child("Category 8").getValue().toString());
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });
        return record;
    }

    public static void get(String date, String time, final OnGetDataListener onGetDataListener){
        onGetDataListener.onStart();
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference().child("Data");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onGetDataListener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetDataListener.onFailed(databaseError);
            }
        });
    }
}
