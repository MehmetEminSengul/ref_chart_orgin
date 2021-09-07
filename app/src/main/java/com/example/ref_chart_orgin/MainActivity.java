package com.example.ref_chart_orgin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LineChart mpLineChart;
    int colorArray[]={R.color.color1,R.color.color2,R.color.color3,R.color.color4};
    int[] colorClassArray= new int[]{Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA};
    String[] legentName={"Cow","Dog","Cat","Rat"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpLineChart=(LineChart) findViewById(R.id.line_chart);
        LineDataSet lineDataSet1=new LineDataSet(dataValues1(),"Data set 1");
        LineDataSet lineDataSet2=new LineDataSet(datavalues2(),"Data set 2");
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        mpLineChart.setNoDataText("No Data");
        mpLineChart.setNoDataTextColor(Color.BLUE);

        mpLineChart.setDrawGridBackground(true);
        mpLineChart.setDrawBorders(true);
        mpLineChart.setBorderColor(Color.RED);
        //mpLineChart.setBorderWidth(5);

        lineDataSet1.setLineWidth(4);
        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setDrawCircleHole(true);
        lineDataSet1.setCircleColor(Color.GRAY);
        lineDataSet1.setCircleHoleColor(Color.GREEN);
        lineDataSet1.setCircleRadius(10);
        lineDataSet1.setCircleHoleRadius(5);
        lineDataSet1.setValueTextSize(10);
        lineDataSet1.setValueTextColor(Color.BLUE);
        //lineDataSet1.enableDashedLine(5,10,0);
        lineDataSet1.setColors(colorArray,MainActivity.this);


        Legend legend =mpLineChart.getLegend();

        legend.setEnabled(true);
        legend.setTextColor(Color.RED);
        legend.setTextSize(15);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setFormSize(20);
        legend.setXEntrySpace(15);
        legend.setFormToTextSpace(10);


        LegendEntry[] legendEntries =new LegendEntry[4];

        for (int i=0; i<legendEntries.length;i++){
            LegendEntry entry =new LegendEntry();
            entry.formColor=colorClassArray[i];
            entry.label=String.valueOf(legentName[i]);
            legendEntries[i]=entry;
        }

        legend.setCustom(legendEntries);

        XAxis xAxis = mpLineChart.getXAxis();
        YAxis yAxisLeft = mpLineChart.getAxisLeft();
        YAxis yAxisRight = mpLineChart.getAxisRight();

        xAxis.setValueFormatter(new MyValueFormatter());

        Description description =new Description();
        description.setText("AgriX");
        description.setTextColor(Color.BLUE);
        description.setTextSize(20);
        mpLineChart.setDescription(description);

        LineData data =new LineData(dataSets);

        lineDataSet1.setValueFormatter(new MyValueFormatter());
        mpLineChart.setData(data);
        mpLineChart.invalidate();

    }

    private ArrayList<Entry> dataValues1()
    {
        ArrayList<Entry> datavals = new ArrayList<>();
        datavals.add(new Entry(0,20));
        datavals.add(new Entry(1,24));
        datavals.add(new Entry(2,2));
        datavals.add(new Entry(3,10));
        datavals.add(new Entry(4,28));

        return datavals;
    }

    private ArrayList<Entry> datavalues2() {
        ArrayList<Entry> datavals2 = new ArrayList<>();
        datavals2.add(new Entry(0, 2));
        datavals2.add(new Entry(1, 14));
        datavals2.add(new Entry(2, 22));
        datavals2.add(new Entry(3, 12));
        datavals2.add(new Entry(4, 14));
        return datavals2;
    }

    private class MyValueFormatter extends ValueFormatter implements IValueFormatter{

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return value + "";
        }
    }

    private class MyAxisValueFormatter implements IAxisValueFormatter{

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            axis.setLabelCount(3,true);
            return "Day"+value;
        }
    }

}