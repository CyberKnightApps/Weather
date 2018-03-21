package com.cyberknight.weather.Main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.cyberknight.weather.Chart.LineChartActivity;
import com.cyberknight.weather.Firebase.Firebase;
import com.cyberknight.weather.R;
import com.cyberknight.weather.bluetooth.Select;
import com.cyberknight.weather.database.AlarmReceiver;
import com.cyberknight.weather.database.BtpContract;
import com.cyberknight.weather.database.BtpDbHelper;
import com.cyberknight.weather.database.BtpDbSource;
import com.cyberknight.weather.database.BtpRecord;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MAIN_ACTIVITY";

    private RecyclerView gridView;
    private RecyclerViewAdapter adapter;
    private boolean flag = false;
    private OverviewValues overviewValues[];
    private Handler periodicUpdateHandler;
    private BtpDbSource database;

    private final int updateInterval = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String check = null;
        Intent i = getIntent();
        check = i.getStringExtra("Check");
        if (check != null)
            flag = true;

        overviewValues = new OverviewValues[14];

        gridView = (RecyclerView) findViewById(R.id.activity_main_grid_view);
        gridView.setLayoutManager(new GridLayoutManager(this, 2));

        database = new BtpDbSource(getApplicationContext());
        populateOverviewValues();
        periodicUpdateHandler = new Handler();
        periodicUpdateHandler.postDelayed(updateRecords, 0);

        adapter = new RecyclerViewAdapter(this, new ArrayList<>(Arrays.asList(overviewValues)));
        gridView.setAdapter(adapter);

        //scheduleAlarm();
    }

    private Runnable updateRecords = new Runnable() {
        @Override
        public void run() {
            populateOverviewValues();
            adapter.notifyDataSetChanged();

            periodicUpdateHandler.postDelayed(updateRecords, updateInterval);
        }
    };

    private synchronized void populateOverviewValues(){
        ArrayList<BtpRecord> oldRecords = database.getAllRecords();

        if(oldRecords.size()==0){
            for(int j=0; j<14; j++) {
                overviewValues[j] = new OverviewValues(BtpRecord.getNameOfParam(j), -25.5, -23.2, -35.4);
            }
        }

        for(int i=0; i<oldRecords.size(); i++){
            try{
                BtpRecord rec = oldRecords.get(i);
                if(i==0) {
                    for(int j=0; j<14; j++){
                        overviewValues[j] = new OverviewValues(rec.getName(j),Double.parseDouble(rec.getParam(j)),
                                Double.parseDouble(rec.getParam(j)),Double.parseDouble(rec.getParam(j)));
                    }
                }
                else{
                    for(int j=0; j<14; j++){
                        double max = (overviewValues[j].getMaxVal()<Double.parseDouble(rec.getParam(j)))?Double.parseDouble(rec.getParam(j)):overviewValues[0].getMaxVal();
                        double min = (overviewValues[j].getMinVal()>Double.parseDouble(rec.getParam(j)))?Double.parseDouble(rec.getParam(j)):overviewValues[0].getMinVal();
                        overviewValues[j] = new OverviewValues(rec.getName(j), Double.parseDouble(rec.getParam(j)), min, max);
                    }
                }
            }
            catch (Exception e){
                Log.e(TAG, e.getLocalizedMessage()+"--"+e.getMessage());
            }
        }
    }

    private void scheduleAlarm(){
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMills = System.currentTimeMillis();
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMills, AlarmManager.INTERVAL_HOUR*2, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_charts:
                Intent i = new Intent(MainActivity.this, LineChartActivity.class);
                startActivity(i);
                return true;
            case R.id.action_connect:
                if (!flag)
                    startActivity(new Intent(MainActivity.this, Select.class));
                else {
                    super.onBackPressed();
                }
                finish();
                return true;
            case R.id.action_save:
                try {

                    Log.e("Firebase***","Data started to store");
                    BtpDbSource database = new BtpDbSource(this);
                    ArrayList<BtpRecord> records = database.getAllRecords();
                    Firebase.putAllData(records);
                    Log.e("Firebase***","Data store completed");
                }
                catch (Exception e){
                    Log.e("Firebase***",e.toString());
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    //do your check here
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        saveCsv();
                    }
                }
                else
                    saveCsv();


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveCsv() {

        BtpDbHelper dbhelper = new BtpDbHelper(getApplicationContext());
        File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "weather.csv");
        if(file.exists())
            file.delete();
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM "+BtpContract.BtpEntry.TABLE_NAME,null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),
                        curCSV.getString(3),curCSV.getString(4), curCSV.getString(5),curCSV.getString(6),
                        curCSV.getString(7), curCSV.getString(8),curCSV.getString(9), curCSV.getString(10)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();

            Toast.makeText(MainActivity.this,"Saved Successfully to Downloads folder",Toast.LENGTH_SHORT).show();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }
}