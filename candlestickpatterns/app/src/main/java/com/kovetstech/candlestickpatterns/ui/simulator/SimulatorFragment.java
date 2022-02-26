package com.kovetstech.candlestickpatterns.ui.simulator;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.kovetstech.candlestickpatterns.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimulatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimulatorFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    CandleStickChart candleStickChart;

    boolean clickable = true;

    public String last_pattern;
    public String last_answer = "UP";

    SimulatorHelper sh;

    Button next_button;

    public SimulatorFragment() {
        // Required empty public constructor
    }
    public static SimulatorFragment newInstance(String param1, String param2) {
        SimulatorFragment fragment = new SimulatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_simulator, container, false);

        sh = new SimulatorHelper();

        candleStickChart = v.findViewById(R.id.candle_stick_chart);

        SetCandleGraph();

        Button up_button = v.findViewById(R.id.up_button);
        up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CandleData newdata = candleStickChart.getData();
                //newdata.removeEntry(0,0);

                //newdata.addEntry(sh.GetUpEntry(newdata.getDataSets().get(0).getEntryForIndex(newdata.getDataSets().get(0).getEntryCount() -1)) , 0);

                //candleStickChart.setData(newdata);
                //candleStickChart.invalidate();

                UserInter("UP");
            }
        });

        Button down_button = v.findViewById(R.id.down_button);
        down_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CandleData newdata = candleStickChart.getData();
                //newdata.removeEntry(0,0);

                //newdata.addEntry(sh.GetDownEntry(newdata.getDataSets().get(0).getEntryForIndex(newdata.getDataSets().get(0).getEntryCount() -1)) , 0);

                //candleStickChart.setData(newdata);
                //candleStickChart.invalidate();

                UserInter("DOWN");
            }
        });

        next_button = v.findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_button.setVisibility(View.INVISIBLE);
                GetRandomPattern();
            }
        });
        return v;
    }
    // Set
    public void SetCandleGraph(){
        candleStickChart.setHighlightPerDragEnabled(true);

        candleStickChart.setDrawBorders(true);
        candleStickChart.setBorderColor(Color.LTGRAY);
        YAxis yAxis = candleStickChart.getAxisLeft();
        YAxis rightAxis = candleStickChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLines(false);

        XAxis xAxis = candleStickChart.getXAxis();

        xAxis.setDrawGridLines(false);// disable x axis grid lines
        xAxis.setDrawLabels(false);
        rightAxis.setTextColor(Color.WHITE);
        yAxis.setDrawLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAvoidFirstLastClipping(true);

        Legend l = candleStickChart.getLegend();
        l.setEnabled(false);

        ArrayList<CandleEntry> yValsCandleStick = sh.getRandomStartingChart() ;

        CandleDataSet set1 = new CandleDataSet(yValsCandleStick, "");
        set1.setShadowColor(Color.GRAY);
        set1.setShadowWidth(0.8f);
        set1.setDecreasingColor(getResources().getColor(R.color.downRed));
        set1.setIncreasingColor(getResources().getColor(R.color.upGreen));
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        set1.setNeutralColor(Color.LTGRAY);
        set1.setDrawValues(false);

        // create a data object with the datasets
        CandleData data = new CandleData(set1);

        candleStickChart.setData(data);
        candleStickChart.invalidate();
        candleStickChart.setVisibleXRangeMaximum(15);

        GetRandomPattern();
    }

    // Interaction
    public void UserInter(String result){
        if(clickable) {
            if (CheckIfUserIsRight(result)) {

            } else {
                // user was wrong
            }

            Go();
        }
    }
    public boolean CheckIfUserIsRight(String res){
        if(res.equals(last_answer)){
            return true;
        }else{
            return false;
        }

    }
    public void GetRandomPattern(){
        Random rnd = new Random();

        switch (rnd.nextInt(4)){
            case 0:
                AddHammer();

                last_pattern = "HAMMER";
                last_answer = "UP";

                break;
            case 1:
                AddMorningStar();

                last_pattern = "MORNINGSTAR";
                last_answer = "UP";
                break;
            case 2:
                AddEveningStar();

                last_pattern = "EVENINGSTAR";
                last_answer = "DOWN";
                break;
            case 3:
                AddHangingMan();

                last_pattern = "HANGINGMAN";
                last_answer = "DOWN";
                break;
        }

        Log.w("SimulatorFragment", "Got " + last_pattern + " Pattern");
    }

    // Up Patterns
    public void AddHammer(){
        ArrayList<CandleEntry> result = sh.getHammer(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        new CountDownTimer(3000, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                clickable = true;
            }
        }.start();
    }
    public void AddMorningStar(){
        ArrayList<CandleEntry> result = sh.getMorningStar(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        new CountDownTimer(2500, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                clickable = true;
            }
        }.start();
    }
    // Down Patterns
    public void AddEveningStar(){
        ArrayList<CandleEntry> result = sh.getEveningStar(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        new CountDownTimer(2500, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                clickable = true;
            }
        }.start();
    }
    public void AddHangingMan(){
        ArrayList<CandleEntry> result = sh.getHangingMan(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        new CountDownTimer(2000, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                clickable = true;
            }
        }.start();
    }

    // Up/Down
    public void Go(){
        clickable = false;
        if(last_answer.equals("UP")){
            GoUp();
        }else{
            GoDown();
        }
    }
    public void GoUp(){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();
        Random rnd = new Random();

        result.add(sh.GetUpEntry(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1)));
        result.add(sh.GetUpEntry(result.get(result.size()-1)));
        result.add(sh.GetUpEntry(result.get(result.size()-1)));
        result.add(sh.GetUpEntry(result.get(result.size()-1)));
        result.add(sh.GetUpEntry(result.get(result.size()-1)));
        result.add(sh.GetUpEntry(result.get(result.size()-1)));

        if(rnd.nextBoolean()) {
            int place = rnd.nextInt(5) + 1;
            int index = (int) result.get(place).getX();

            CandleEntry ce = sh.GetDownEntry(result.get(place - 1));
            ce.setX(index);

            result.set(place, ce);
            sh.CurrentIndex--;
        }else{
            int place = rnd.nextInt(5) + 1;
            int index = (int) result.get(place).getX();

            CandleEntry ce = sh.GetDownEntry(result.get(place - 1));
            ce.setX(index);

            result.set(place, ce);
            sh.CurrentIndex--;

            place = rnd.nextInt(5) + 1;
            index = (int) result.get(place).getX();

            ce = sh.GetDownEntry(result.get(place - 1));
            ce.setX(index);

            result.set(place, ce);
            sh.CurrentIndex--;
        }

        final int[] i = {0};
        new CountDownTimer(3000, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                next_button.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void GoDown(){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();
        Random rnd = new Random();

        result.add(sh.GetDownEntry(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1)));
        result.add(sh.GetDownEntry(result.get(result.size()-1)));
        result.add(sh.GetDownEntry(result.get(result.size()-1)));
        result.add(sh.GetDownEntry(result.get(result.size()-1)));
        result.add(sh.GetDownEntry(result.get(result.size()-1)));
        result.add(sh.GetDownEntry(result.get(result.size()-1)));

        if(rnd.nextBoolean()) {
            int place = rnd.nextInt(5) + 1;
            int index = (int) result.get(place).getX();

            CandleEntry ce = sh.GetUpEntry(result.get(place - 1));
            ce.setX(index);

            result.set(place, ce);
            sh.CurrentIndex--;
        }else{
            int place = rnd.nextInt(5) + 1;
            int index = (int) result.get(place).getX();

            CandleEntry ce = sh.GetUpEntry(result.get(place - 1));
            ce.setX(index);

            result.set(place, ce);
            sh.CurrentIndex--;

            place = rnd.nextInt(6) + 1;
            index = (int) result.get(place).getX();

            ce = sh.GetUpEntry(result.get(place - 1));
            ce.setX(index);

            result.set(place, ce);
            sh.CurrentIndex--;
        }

        final int[] i = {0};
        new CountDownTimer(3000, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                next_button.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    // Helpers
    public void AddCandle(CandleEntry ce){
        CandleData newdata = candleStickChart.getData();

        newdata.removeEntry(0,0);

        newdata.addEntry(ce, 0);

        candleStickChart.setData(newdata);
        candleStickChart.invalidate();
    }
}