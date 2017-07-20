package com.cyberknight.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cyberknight.weather.database.BtpRecord;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChartsAdapter adapter;
    private ArrayList<Charts>chartsArrayList;
    private ArrayList<Integer> records[] = new ArrayList[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        pushContentsToRecyclerView();
    }

    public void updateRecords(){
        for(int i=0; i<8; i++)  records[i] = new ArrayList<>();

        for(BtpRecord x: RecordCollector.getBtpRecords()){
            records[0].add(Integer.parseInt(x.getTemperature()));
            records[1].add(Integer.parseInt(x.getPressure()));
            records[2].add(Integer.parseInt(x.getHumidity()));
            records[3].add(Integer.parseInt(x.getLight()));
            records[4].add(Integer.parseInt(x.getNO2()));
            records[5].add(Integer.parseInt(x.getCO2()));
            records[6].add(Integer.parseInt(x.getNH3()));
            records[7].add(Integer.parseInt(x.getVOC()));
            Log.e("LineCharActivity",x.getTemperature()+" "+x.getPressure()+" "+x.getHumidity()+" "+x.getLight()+" -------------------------------------- ");
        }
    }

    void pushContentsToRecyclerView()
    {

        updateRecords();
        ArrayList<Integer> randomData;
        int count;
        chartsArrayList=new ArrayList<>();

        chartsArrayList.add(new Charts("Temperature","Measure Temp",records[0]));
        chartsArrayList.add(new Charts("Pressure","Measure Temp",records[1]));
        chartsArrayList.add(new Charts("Humidity","Measure Temp",records[2]));
        chartsArrayList.add(new Charts("Light","Measure Temp",records[3]));
        chartsArrayList.add(new Charts("NO2","Measure Temp",records[4]));
        chartsArrayList.add(new Charts("CO2","Measure Temp",records[5]));
        chartsArrayList.add(new Charts("NH3","Measure Temp",records[6]));
        chartsArrayList.add(new Charts("VOC","Measure Temp",records[7]));

        recyclerView = (RecyclerView) findViewById(R.id.rvCharts);

        adapter = new ChartsAdapter(this,chartsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LineChartActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }
}