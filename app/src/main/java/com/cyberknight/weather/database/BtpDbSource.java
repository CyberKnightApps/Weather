package com.cyberknight.weather.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Parth on 21-07-2017.
 * Project Weather.
 */

public class BtpDbSource {

    private static final String FILE_NAME = "PrefFile";

    private SQLiteDatabase readDatabase, writeDatabase;
    private BtpDbHelper dbHelper;
    private Context context;
    private String columns[] = {
            BtpContract.BtpEntry._ID,
            BtpContract.BtpEntry.COLUMN_TIME,
            BtpContract.BtpEntry.COLUMN_TEMPERATURE,
            BtpContract.BtpEntry.COLUMN_PRESSURE,
            BtpContract.BtpEntry.COLUMN_HUMIDITY,
            BtpContract.BtpEntry.COLUMN_LIGHT,
            BtpContract.BtpEntry.COLUMN_NO2,
            BtpContract.BtpEntry.COLUMN_NH3,
            BtpContract.BtpEntry.COLUMN_CO2,
            BtpContract.BtpEntry.COLUMN_VOC,
            BtpContract.BtpEntry.COLUMN_CO
    };

    public BtpDbSource(Context context){
        this.context = context;
        dbHelper = new BtpDbHelper(context);
    }

    public void open() throws SQLException {
        writeDatabase = dbHelper.getWritableDatabase();
        readDatabase = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public BtpRecord addRecord(BtpRecord record){
        ContentValues values = new ContentValues();

        values.put(BtpContract.BtpEntry.COLUMN_TIME, record.getTime());
        values.put(BtpContract.BtpEntry.COLUMN_TEMPERATURE, record.getTemperature());
        values.put(BtpContract.BtpEntry.COLUMN_PRESSURE, record.getPressure());
        values.put(BtpContract.BtpEntry.COLUMN_HUMIDITY, record.getHumidity());
        values.put(BtpContract.BtpEntry.COLUMN_LIGHT, record.getLight());
        values.put(BtpContract.BtpEntry.COLUMN_NO2, record.getNO2());
        values.put(BtpContract.BtpEntry.COLUMN_NH3, record.getNH3());
        values.put(BtpContract.BtpEntry.COLUMN_CO2, record.getCO2());
        values.put(BtpContract.BtpEntry.COLUMN_VOC, record.getVOC());
        values.put(BtpContract.BtpEntry.COLUMN_CO, record.getCO());

        record.setId(writeDatabase.insert(BtpContract.BtpEntry.TABLE_NAME, null, values));
        return record;
    }

    public ArrayList<BtpRecord> getAllRecords(){
        ArrayList<BtpRecord> records = new ArrayList<>();

        Cursor cursor = writeDatabase.query(BtpContract.BtpEntry.TABLE_NAME, columns, null, null, null, null, null);
        //database.query()
        if(cursor.moveToFirst()){
            do{
                records.add(cursorToRecord(cursor));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return records;
    }

    private BtpRecord cursorToRecord(Cursor cursor){
        BtpRecord record = new BtpRecord();
        record.setId(cursor.getLong(cursor.getColumnIndex(columns[0])));
        record.setTime(cursor.getString(cursor.getColumnIndex(columns[1])));
        record.setTemperature(cursor.getString(cursor.getColumnIndex(columns[2])));
        record.setPressure(cursor.getString(cursor.getColumnIndex(columns[3])));
        record.setHumidity(cursor.getString(cursor.getColumnIndex(columns[4])));
        record.setLight(cursor.getString(cursor.getColumnIndex(columns[5])));
        record.setNO2(cursor.getString(cursor.getColumnIndex(columns[6])));
        record.setNH3(cursor.getString(cursor.getColumnIndex(columns[7])));
        record.setCO2(cursor.getString(cursor.getColumnIndex(columns[8])));
        record.setVOC(cursor.getString(cursor.getColumnIndex(columns[9])));
        record.setCO(cursor.getString(cursor.getColumnIndex(columns[10])));
        return record;
    }

    public ArrayList<BtpRecord> getAllRecordsToUpload(){
        ArrayList<BtpRecord> records = new ArrayList<>();

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        long id = pref.getLong("id",0);

        String query = "SELECT * FROM " + BtpContract.BtpEntry.TABLE_NAME +
                " WHERE " + BtpContract.BtpEntry._ID + " > " + id +
                " ORDER BY " + BtpContract.BtpEntry._ID + " ASC";

        Cursor c = readDatabase.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                records.add(cursorToRecord(c));
            }while(c.moveToNext());
        }

        if(records.size()>0) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putLong("id", records.get(records.size()-1).getId());
            editor.apply();
        }

        return records;
    }
}
