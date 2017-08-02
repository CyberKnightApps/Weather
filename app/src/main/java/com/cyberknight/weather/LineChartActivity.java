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

public class LineChartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChartsAdapter adapter;
    private ArrayList<Charts>chartsArrayList;
    private ArrayList<Float> records[] = new ArrayList[8];
    private Context mContext;
    BtpDbSource bds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        mContext = getApplicationContext();

        bds = new BtpDbSource(mContext);
        bds.open();
        for(int i=0; i<8; i++)  records[i] = new ArrayList<>();
        loadOldRecords();
        pushContentsToRecyclerView();
        bds.close();
    }

    public void loadOldRecords(){

        ArrayList<BtpRecord>oldRecords = bds.getAllRecords();

        for(BtpRecord x: oldRecords) {

            try {
                records[0].add(Float.parseFloat(x.getTemperature()));
                records[1].add(Float.parseFloat(x.getPressure()));
                records[2].add(Float.parseFloat(x.getHumidity()));
                records[3].add(Float.parseFloat(x.getLight()));
                records[4].add(Float.parseFloat(x.getNO2()));
                records[5].add(Float.parseFloat(x.getCO2()));
                records[6].add(Float.parseFloat(x.getNH3()));
                records[7].add(Float.parseFloat(x.getVOC()));
                //records[8].add(Float.parseFloat(x.getCO()));
                Log.e("LineCharActivity", x.getTemperature() + " " + x.getPressure() + " " + x.getHumidity() + " " + x.getLight() + " -------------------------------------- ");

            } catch (Exception e) {

            }
        }
    }
    public void updateRecords(){


        for(BtpRecord x: RecordCollector.getBtpRecords()){

            try {
                records[0].add(Float.parseFloat(x.getTemperature()));
                records[1].add(Float.parseFloat(x.getPressure()));
                records[2].add(Float.parseFloat(x.getHumidity()));
                records[3].add(Float.parseFloat(x.getLight()));
                records[4].add(Float.parseFloat(x.getNO2()));
                records[5].add(Float.parseFloat(x.getCO2()));
                records[6].add(Float.parseFloat(x.getNH3()));
                records[7].add(Float.parseFloat(x.getVOC()));
                //records[8].add(Float.parseFloat(x.getCO()));
                Log.e("LineCharActivity", x.getTemperature() + " " + x.getPressure() + " " + x.getHumidity() + " " + x.getLight() + " -------------------------------------- ");

                bds.addRecord(x);


            }
            catch (Exception e){

            }
        }
        for(int i=0;i<8;i++){
            if(records[i].isEmpty()){
                records[i].add(5f);
            }
        }
    }

    void pushContentsToRecyclerView()
    {

        updateRecords();

        chartsArrayList=new ArrayList<>();

        chartsArrayList.add(new Charts("Temperature","Measure Temperature",records[0]));
        chartsArrayList.add(new Charts("Pressure","Measure Pressure",records[1]));
        chartsArrayList.add(new Charts("Humidity","Measure Humidity",records[2]));
        chartsArrayList.add(new Charts("Light","Measure Light",records[3]));
        chartsArrayList.add(new Charts("NO2","Measure NO2",records[4]));
        chartsArrayList.add(new Charts("CO2","Measure CO2",records[5]));
        chartsArrayList.add(new Charts("NH3","Measure NH3",records[6]));
        chartsArrayList.add(new Charts("VOC","Measure VOC",records[7]));
        //chartsArrayList.add(new Charts("CO","Measure CO",records[8]));

        recyclerView = (RecyclerView) findViewById(R.id.rvCharts);

        adapter = new ChartsAdapter(this,chartsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LineChartActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }
}