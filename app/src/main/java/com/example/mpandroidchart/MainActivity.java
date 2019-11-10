package com.example.mpandroidchart;

/*
Copyright 2018 Philipp Jahoda

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific language governing permissions
and limitations under the License.
 */

//  https://github.com/PhilJay/MPAndroidChart/wiki/Setting-Data

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = findViewById(R.id.chart);

        // Grid Background and line
        mChart.setDrawGridBackground(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);

        // no description text and legend
//        mChart.getDescription().setEnabled(false);
        mChart.setDescription(null);
        mChart.getLegend().setEnabled(false);

        final String[] quarters = new String[]{"ABC", "Spring", "Zsystem", "QSD"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
//            @Override
//            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(formatter);

        YAxis leftAxis = mChart.getAxisLeft();
        // Y axis
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        // Grid DashedLine
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);

        // Scale
        mChart.getAxisRight().setEnabled(false);

        // add data
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));


        BarDataSet set = new BarDataSet(entries, "");

//        customer bar chart color (#1982)
        int[] colorArray = {Color.rgb(220, 20, 60),
                Color.rgb(34, 139, 34),
                Color.rgb(220, 20, 60),
                Color.rgb(34, 139, 34)};
        set.setColors(ColorTemplate.createColors(colorArray));

        BarData data = new BarData(set);
        data.setBarWidth(0.5f); // set custom bar width
        mChart.setData(data);

//        mChart.setFitBars(true); // make the x-axis fit exactly all bars
        mChart.invalidate(); // refresh

        mChart.animateX(500);
    }
}
