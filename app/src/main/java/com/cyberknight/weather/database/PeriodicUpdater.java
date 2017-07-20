package com.cyberknight.weather.database;

import android.app.IntentService;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

/**
 * Created by Parth on 21-07-2017.
 * Project Weather.
 */

public class PeriodicUpdater extends IntentService {

    private static final String SERVICE_NAME = "Periodic Updater Service";

    public PeriodicUpdater(){
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(isNetworkAvailable()){
            /*
             *
             * Upload code to firebase here....
             *
             */
        }
        else{

        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info!=null && info.isConnected();
    }
}
