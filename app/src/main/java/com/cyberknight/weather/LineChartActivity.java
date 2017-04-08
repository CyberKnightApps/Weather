package com.cyberknight.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChartsAdapter adapter;
    private ArrayList<Charts>chartsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        pushContentsToRecyclerView();
    }

    void pushContentsToRecyclerView()
    {
        ArrayList<Integer> randomData;
        int count;
        chartsArrayList=new ArrayList<>();
        randomData=new ArrayList<>();
        count=(int)(Math.random()*10);
        for (int i = 0; i < count; i++) {
            int val = (int) (Math.random() * 50) + 3;
            randomData.add(val);
        }
        chartsArrayList.add(new Charts("Temperature","Measure Temp",randomData));
        count=(int)(Math.random()*25);
        for (int i = 0; i < count; i++) {
            int val = (int) (Math.random() * 50) + 3;
            randomData.add(val);
        }
        chartsArrayList.add(new Charts("Pressure","Measure Pressure",randomData));
        count=(int)(Math.random()*25);
        for (int i = 0; i < count; i++) {
            int val = (int) (Math.random() * 50) + 3;
            randomData.add(val);
        }
        chartsArrayList.add(new Charts("Humidity","Measure Humid",randomData));
        count=(int)(Math.random()*25);
        for (int i = 0; i < count; i++) {
            int val = (int) (Math.random() * 50) + 3;
            randomData.add(val);
        }
        chartsArrayList.add(new Charts("Temperataurae","Measauraea Temp",randomData));
        count=(int)(Math.random()*25);
        for (int i = 0; i < count; i++) {
            int val = (int) (Math.random() * 50) + 3;
            randomData.add(val);
        }
        chartsArrayList.add(new Charts("Temperaturea","aaMeasure Temp",randomData));

        recyclerView = (RecyclerView) findViewById(R.id.rvCharts);

        adapter = new ChartsAdapter(this,chartsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LineChartActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }
}