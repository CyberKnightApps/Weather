package com.cyberknight.weather.database;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cyberknight.weather.Firebase;

import java.util.ArrayList;

/**
 * Created by Parth on 21-07-2017.
 * Project Weather.
 */

public class PeriodicUpdater extends IntentService {

    private static final String SERVICE_NAME = "Periodic Update Service";
    private Context context;

    public PeriodicUpdater(){
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
       /*if(isNetworkAvailable()){

            BtpDbSource database = new BtpDbSource(context);
            ArrayList<BtpRecord> records = database.getAllRecordsToUpload();

            Firebase.putAllData(records);

        }
        else{
            Log.e(SERVICE_NAME, "Internet not available");
        }*/
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info!=null && info.isConnected();
    }
}
