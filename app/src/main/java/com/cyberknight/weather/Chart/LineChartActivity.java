package com.cyberknight.weather.Chart;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cyberknight.weather.R;
import com.cyberknight.weather.database.RecordCollector;
import com.cyberknight.weather.database.BtpDbSource;
import com.cyberknight.weather.database.BtpRecord;

import java.util.ArrayList;
import java.util.Queue;

public class LineChartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChartsAdapter adapter;
    private ArrayList<Charts>chartsArrayList;
    private ArrayList<Float> values[] = new ArrayList[14];
    private Context mContext;

    BtpDbSource bds;

    private int mInterval = 30000; // 5 seconds by default, can be changed later
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        mContext = getApplicationContext();

        bds = new BtpDbSource(mContext);
        bds.open();
        for(int i=0; i<14; i++)  values[i] = new ArrayList<>();
        loadOldRecords();
        updateRecords();
        pushContentsToRecyclerView();


        mHandler = new Handler();
        startRepeatingTask();
    }

    public void loadOldRecords(){

        ArrayList<BtpRecord>oldRecords = bds.getAllRecords();

        for(BtpRecord x: oldRecords) {

            try {
                values[0].add(Float.parseFloat(x.getTemperature()));
                values[1].add(Float.parseFloat(x.getPressure()));
                values[2].add(Float.parseFloat(x.getHumidity()));
                values[3].add(Float.parseFloat(x.getLight()));
                values[4].add(Float.parseFloat(x.getNO2()));
                values[5].add(Float.parseFloat(x.getCO2()));
                values[6].add(Float.parseFloat(x.getNH3()));
                values[7].add(Float.parseFloat(x.getVOC()));
                values[8].add(Float.parseFloat(x.getCO()));
                values[9].add(Float.parseFloat(x.getH2()));
                values[10].add(Float.parseFloat(x.getCH4()));
                values[11].add(Float.parseFloat(x.getC2H5OH()));
                values[12].add(Float.parseFloat(x.getC4H10()));
                values[13].add(Float.parseFloat(x.getC3H8()));
                Log.e("LineCharActivity", x.getTemperature() + " " + x.getPressure() + " " + x.getHumidity() + " " + x.getLight() + " -------------------------------------- ");

            } catch (Exception e) {

            }
        }
    }
    public void updateRecords(){


        Queue<BtpRecord> q = RecordCollector.getBtpRecords();
        while(!q.isEmpty()){
            BtpRecord x = q.poll();
            try {
                values[0].add(Float.parseFloat(x.getTemperature()));
                values[1].add(Float.parseFloat(x.getPressure()));
                values[2].add(Float.parseFloat(x.getHumidity()));
                values[3].add(Float.parseFloat(x.getLight()));
                values[4].add(Float.parseFloat(x.getNO2()));
                values[5].add(Float.parseFloat(x.getCO2()));
                values[6].add(Float.parseFloat(x.getNH3()));
                values[7].add(Float.parseFloat(x.getVOC()));
                values[8].add(Float.parseFloat(x.getCO()));
                values[9].add(Float.parseFloat(x.getH2()));
                values[10].add(Float.parseFloat(x.getCH4()));
                values[11].add(Float.parseFloat(x.getC2H5OH()));
                values[12].add(Float.parseFloat(x.getC3H8()));
                values[13].add(Float.parseFloat(x.getC4H10()));

                Log.e("LineCharActivity", x.getTemperature() + " " + x.getPressure() + " " + x.getHumidity() + " " + x.getLight() + " -------------------------------------- ");


                bds.addRecord(x);


            }
            catch (Exception e){

            }
        }
        for(int i=0;i<14;i++){
            if(values[i].isEmpty()){
                values[i].add(-1f);
            }
        }
    }

    void pushContentsToRecyclerView()
    {



        chartsArrayList=new ArrayList<>();

        chartsArrayList.add(new Charts("Temperature","Measure Temperature",values[0]));
        chartsArrayList.add(new Charts("Pressure","Measure Pressure",values[1]));
        chartsArrayList.add(new Charts("Humidity","Measure Humidity",values[2]));
        chartsArrayList.add(new Charts("Light","Measure Light",values[3]));
        chartsArrayList.add(new Charts("NO2","Measure NO2",values[4]));
        chartsArrayList.add(new Charts("CO2","Measure CO2",values[5]));
        chartsArrayList.add(new Charts("NH3","Measure NH3",values[6]));
        chartsArrayList.add(new Charts("VOC","Measure VOC",values[7]));
        chartsArrayList.add(new Charts("CO","Measure CO",values[8]));
        chartsArrayList.add(new Charts("H2","Measure H2",values[9]));
        chartsArrayList.add(new Charts("CH4","Measure CH4",values[10]));
        chartsArrayList.add(new Charts("C2H5OH","Measure C2H5OH",values[11]));
        chartsArrayList.add(new Charts("C3H8","Measure C3H8",values[12]));
        chartsArrayList.add(new Charts("C4H10","Measure C4H10",values[13]));

        recyclerView = (RecyclerView) findViewById(R.id.rvCharts);

        adapter = new ChartsAdapter(this,chartsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LineChartActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
        bds.close();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {


                updateRecords();
                //pushContentsToRecyclerView(); //this function can change value of mInterval.
                adapter.notifyDataSetChanged();
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

}