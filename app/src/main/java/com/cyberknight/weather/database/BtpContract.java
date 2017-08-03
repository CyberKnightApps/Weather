package com.cyberknight.weather.database;

import android.provider.BaseColumns;

/**
 * Created by Parth on 04-03-2017.
 * Project btapp.
 */

public final class BtpContract {

    static final String SQL_CREATE_BTP_TABLE =
            "CREATE TABLE IF NOT EXISTS " + BtpEntry.TABLE_NAME
                    + " ("
                    + BtpEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                    + BtpEntry.COLUMN_TIME + " VARCHAR (30) , "
                    + BtpEntry.COLUMN_TEMPERATURE + " REAL , "
                    + BtpEntry.COLUMN_PRESSURE + " REAL , "
                    + BtpEntry.COLUMN_HUMIDITY + " REAL, "
                    + BtpEntry.COLUMN_LIGHT + " REAL, "
                    + BtpEntry.COLUMN_NO2 + " REAL, "
                    + BtpEntry.COLUMN_NH3 + " REAL, "
                    + BtpEntry.COLUMN_CO2 + " REAL, "
                    + BtpEntry.COLUMN_VOC + " REAL, "
                    + BtpEntry.COLUMN_CO + " REAL "
                    + ")";

    static final String SQL_DELETE_BTP_TABLE = "DROP TABLE IF EXISTS " + BtpEntry.TABLE_NAME;

    private BtpContract() {}

    public static class BtpEntry implements BaseColumns {
        public static final String TABLE_NAME = "BtpTable";
        public static final String COLUMN_TIME = "Time";
        public static final String COLUMN_TEMPERATURE = "Temperature";
        public static final String COLUMN_PRESSURE = "Pressure";
        public static final String COLUMN_HUMIDITY = "Humidity";
        public static final String COLUMN_LIGHT = "Light";
        public static final String COLUMN_NO2 = "NO2";
        public static final String COLUMN_NH3 = "NH3";
        public static final String COLUMN_CO2 = "CO2";
        public static final String COLUMN_VOC = "VOC";
        public static final String COLUMN_CO = "CO";
    }
}