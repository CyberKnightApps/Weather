package com.cyberknight.weather.database;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Parth on 21-07-2017.
 * Project Weather.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 201501211;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, PeriodicUpdater.class);
        context.startService(i);
    }
}
