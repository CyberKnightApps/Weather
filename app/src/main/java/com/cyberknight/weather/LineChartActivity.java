package com.cyberknight.weather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cyberknight.weather.database.BtpDbSource;
import com.cyberknight.weather.database.BtpRecord;

import java.util.ArrayList;
import java.util.Queue;

public class LineChartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChartsAdapter adapter;
    private ArrayList<Charts>chartsArrayList;
    private ArrayList<Float> values[] = new ArrayList[9];
    private Context mContext;
    BtpDbSource bds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        mContext = getApplicationContext();

        bds = new BtpDbSource(mContext);
        bds.open();
        for(int i=0; i<9; i++)  values[i] = new ArrayList<>();
        loadOldRecords();
        pushContentsToRecyclerView();
        bds.close();
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
                Log.e("LineCharActivity", x.getTemperature() + " " + x.getPressure() + " " + x.getHumidity() + " " + x.getLight() + " -------------------------------------- ");

                bds.addRecord(x);


            }
            catch (Exception e){

            }
        }
        /*for(BtpRecord x: RecordCollector.getBtpRecords()){

            try {
                values[0].add(Float.parseFloat(x.getTemperature()));
                values[1].add(Float.parseFloat(x.getPressure()));
                values[2].add(Float.parseFloat(x.getHumidity()));
                values[3].add(Float.parseFloat(x.getLight()));
                values[4].add(Float.parseFloat(x.getNO2()));
                values[5].add(Float.parseFloat(x.getCO2()));
                values[6].add(Float.parseFloat(x.getNH3()));
                values[7].add(Float.parseFloat(x.getVOC()));
                //values[8].add(Float.parseFloat(x.getCO()));
                Log.e("LineCharActivity", x.getTemperature() + " " + x.getPressure() + " " + x.getHumidity() + " " + x.getLight() + " -------------------------------------- ");

                bds.addRecord(x);


            }
            catch (Exception e){

            }
        }*/
        for(int i=0;i<9;i++){
            if(values[i].isEmpty()){
                values[i].add(5f);
            }
        }
    }

    void pushContentsToRecyclerView()
    {

        updateRecords();

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

        recyclerView = (RecyclerView) findViewById(R.id.rvCharts);

        adapter = new ChartsAdapter(this,chartsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LineChartActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }


}