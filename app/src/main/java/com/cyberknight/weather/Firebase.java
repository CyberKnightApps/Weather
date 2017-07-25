package com.cyberknight.weather;

import android.app.ProgressDialog;
import android.content.Context;

import com.cyberknight.weather.database.BtpRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Nirbhay on 25-07-2017.
 */

public class Firebase {

    private static FirebaseDatabase database;
    private static DatabaseReference mRef;
    private static ProgressDialog progressDialog;
    public static void putAllData(ArrayList<BtpRecord> btpRecord){
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference().child("Data");
        for(int i = 0;i<btpRecord.size();i++){
            BtpRecord record = btpRecord.get(i);
            mRef.child(record.getDate()).child(record.getTime()).child("Category 1").setValue(record.getTemperature());
            mRef.child(record.getDate()).child(record.getTime()).child("Category 2").setValue(record.getHumidity());
            mRef.child(record.getDate()).child(record.getTime()).child("Category 3").setValue(record.getPressure());
            mRef.child(record.getDate()).child(record.getTime()).child("Category 4").setValue(record.getCO2());
            mRef.child(record.getDate()).child(record.getTime()).child("Category 5").setValue(record.getLight());
            mRef.child(record.getDate()).child(record.getTime()).child("Category 6").setValue(record.getNH3());
            mRef.child(record.getDate()).child(record.getTime()).child("Category 7").setValue(record.getNO2());
            mRef.child(record.getDate()).child(record.getTime()).child("Category 8").setValue(record.getVOC());
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
