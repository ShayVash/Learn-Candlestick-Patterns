package com.kovetstech.candlestickpatterns.ui.simulator;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.kovetstech.candlestickpatterns.R;

import java.util.ArrayList;
import java.util.Random;

public class SimulatorFragment extends Fragment {
    CandleStickChart candleStickChart;

    boolean clickable = true;
    boolean TestRun = false;

    public String last_pattern;
    public String last_answer = "UP";

    SimulatorHelper sh;

    View v;
    Button up_button;
    Button down_button;
    Button next_button;

    TextView simu_title;
    Spinner DebugSpinnerPatterns;
    boolean debug = false;

    CountDownTimer cdt;
    MediaPlayer mp;

    int Inc_color;
    int Dec_color;

    int Shadow_color;
    int Border_color;

    public SimulatorFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_simulator, container, false);

        sh = new SimulatorHelper();

        if(candleStickChart == null) {
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
            //SetCandleGraph();
        }else {
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
            candleStickChart.invalidate();

            //SetCandleGraph();
        }

        up_button = v.findViewById(R.id.up_button);
        up_button.setOnClickListener(view -> UserInter("UP"));

        down_button = v.findViewById(R.id.down_button);
        down_button.setOnClickListener(view -> UserInter("DOWN"));

        next_button = v.findViewById(R.id.next_button);
        next_button.setOnClickListener(view -> {
            next_button.setVisibility(View.INVISIBLE);
            DebugSpinnerPatterns.setVisibility(View.INVISIBLE);

            if(debug){
                GetPattern(DebugSpinnerPatterns.getSelectedItem().toString());
            }else{
                GetRandomPattern();
            }

        });

        simu_title = v.findViewById(R.id.simulator_title);
        simu_title.setOnClickListener(view -> {
            debug = !debug;
            Toast.makeText(getContext(), "Debug Mode " + debug, Toast.LENGTH_LONG).show();
        });

        DebugSpinnerPatterns = v.findViewById(R.id.spinner);

        return v;
    }

    // Set
    public void get_colors_from_settings(){
        SharedPreferences prefs = getContext().getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);

        Inc_color = prefs.getInt("Bullish Candle Colorc", getResources().getColor(R.color.upGreen, null));
        Dec_color = prefs.getInt("Bearish Candle Colorc", getResources().getColor(R.color.downRed, null));
        Shadow_color = prefs.getInt("Shadow Colorc", Color.GRAY);
        Border_color = prefs.getInt("Chart Border Colorc", Color.LTGRAY);
    }
    public void SetCandleGraph(){
        get_colors_from_settings();
        candleStickChart.clear();

        candleStickChart.setHighlightPerDragEnabled(true);

        candleStickChart.setDrawBorders(true);
        candleStickChart.setBorderColor(Border_color);
        YAxis yAxis = candleStickChart.getAxisLeft();
        YAxis rightAxis = candleStickChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLines(false);

        XAxis xAxis = candleStickChart.getXAxis();

        xAxis.setDrawGridLines(false);// disable x axis grid lines
        xAxis.setDrawLabels(false);
        yAxis.setDrawLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAvoidFirstLastClipping(true);

        Legend l = candleStickChart.getLegend();
        l.setEnabled(false);

        ArrayList<Entry> LineyValsCandleStick = sh.GetRandomChartWithBrownian(0, 0.2, 1);
        ArrayList<CandleEntry> CandleyValsCandleStick = sh.getRandomCandleChart(LineyValsCandleStick) ;

        CandleDataSet set1 = new CandleDataSet(CandleyValsCandleStick, "");
        set1.setShadowColor(Shadow_color);
        set1.setShadowWidth(0.8f);
        set1.setDecreasingColor(Dec_color);
        set1.setIncreasingColor(Inc_color);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        set1.setNeutralColor(Color.LTGRAY);
        set1.setDrawValues(false);

        // Set Colors from settings



        // create a data object with the datasets
        CandleData data = new CandleData(set1);

        candleStickChart.setData(data);
        candleStickChart.invalidate();
        candleStickChart.setVisibleXRangeMaximum(20);



        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                rightAxis.setTextColor(Color.WHITE);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                rightAxis.setTextColor(Color.BLACK);
                break;
        }

        GetRandomPattern();
    }

    // Interaction
    public void UserInter(String result){
        if(clickable) {
            Go();
            if (CheckIfUserIsRight(result)) {
                mp = MediaPlayer.create(getContext(), R.raw.correct);
            } else {
                mp = MediaPlayer.create(getContext(), R.raw.wrong);
            }
            mp.start();
        }
    }
    public boolean CheckIfUserIsRight(String res){
        return res.equals(last_answer);

    }
    public void GetRandomPattern(){
        Random rnd = new Random();

        if(candleStickChart == null){
            SetCandleGraph();
        }
        switch (rnd.nextInt(12)){
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
            case 4:
                AddThreeWhiteSoldiers();

                last_pattern = "THREEWHITESOLDIERS";
                last_answer = "UP";
                break;
            case 5:
                AddThreeBlackCrows();

                last_pattern = "THREEBLACKCROWS";
                last_answer = "DOWN";
                break;
            case 6:
                AddShootingStar();

                last_pattern = "SHOOTINGSTAR";
                last_answer = "DOWN";
                break;
            case 7:
                AddBullishEngulfing();

                last_pattern = "BULLISHENGULFING";
                last_answer = "UP";
                break;
            case 8:
                AddBearishEngulfing();

                last_pattern = "BEARISHENGULFING";
                last_answer = "DOWN";
                break;
            case 9:
                AddBullishThreeLineStrike();

                last_pattern = "BULLISHTHREELINESTRIKE";
                last_answer = "UP";
                break;
            case 10:
                AddBearishThreeLineStrike();

                last_pattern = "BEARISHTHREELINESTRIKE";
                last_answer = "DOWN";
                break;
            case 11:
                AddThreeInsideUp();

                last_pattern = "THREEINSIDEUP";
                last_answer = "UP";
                break;
            case 12:
                AddThreeInsideDown();

                last_pattern = "THREEINSIDEUP";
                last_answer = "UP";
                break;
        }

        Log.w("SimulatorFragment", "Got " + last_pattern + " Pattern");
    }
    public void GetPattern(String name){
        switch (name){
            case "UPDOWN":
                TestRun = true;
                GoUp();
                Log.w("TESTRUN", "Testing UP");
                break;
            case "TESTRUN":
                TestRun = true;
                AddHammer();
                Log.w("TESTRUN", "Testing Hammer");
                break;
            case "HAMMER":
                AddHammer();

                last_pattern = "HAMMER";
                last_answer = "UP";

                break;
            case "MORNINGSTAR":
                AddMorningStar();

                last_pattern = "MORNINGSTAR";
                last_answer = "UP";
                break;
            case "EVENINGSTAR":
                AddEveningStar();

                last_pattern = "EVENINGSTAR";
                last_answer = "DOWN";
                break;
            case "HANGINGMAN":
                AddHangingMan();

                last_pattern = "HANGINGMAN";
                last_answer = "DOWN";
                break;
            case "THREEWHITESOLDIERS":
                AddThreeWhiteSoldiers();

                last_pattern = "THREEWHITESOLDIERS";
                last_answer = "UP";
                break;
            case "THREEBLACKCROWS":
                AddThreeBlackCrows();

                last_pattern = "THREEBLACKCROWS";
                last_answer = "DOWN";
                break;
            case "SHOOTINGSTAR":
                AddShootingStar();

                last_pattern = "SHOOTINGSTAR";
                last_answer = "DOWN";
                break;
            case "BULLISHENGULFING":
                AddBullishEngulfing();

                last_pattern = "BULLISHENGULFING";
                last_answer = "UP";
                break;
            case "BEARISHENGULFING":
                AddBearishEngulfing();

                last_pattern = "BEARISHENGULFING";
                last_answer = "DOWN";
                break;
            case "BULLISHTHREELINESTRIKE":
                AddBullishThreeLineStrike();

                last_pattern = "BULLISHTHREELINESTRIKE";
                last_answer = "UP";
                break;
            case "BEARISHTHREELINESTRIKE":
                AddBearishThreeLineStrike();

                last_pattern = "BEARISHTHREELINESTRIKE";
                last_answer = "DOWN";
                break;
            case "THREEINSIDEUP":
                AddThreeInsideUp();

                last_pattern = "THREEINSIDEUP";
                last_answer = "UP";
                break;
            case "THREEINSIDEDOWN":
                AddThreeInsideDown();

                last_pattern = "THREEINSIDEDOWN";
                last_answer = "DOWN";
                break;
        }

        Log.w("SimulatorFragment", "Got " + last_pattern + " Pattern");
    }


    // Up Patterns
    public void AddHammer(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getHammer(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();
        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddMorningStar();
                    Log.w("TESTRUN", "Testing MorningStar");
                }
            }
        }.start();
    }
    public void AddMorningStar(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getMorningStar(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddThreeWhiteSoldiers();
                    Log.w("TESTRUN", "Testing ThreeWhiteSoldiers");
                }
            }
        }.start();
    }
    public void AddThreeWhiteSoldiers(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getThreeWhiteSoldiers(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddBullishEngulfing();
                    Log.w("TESTRUN", "Testing BullishEngulfing");
                }
            }
        }.start();
    }
    public void AddBullishEngulfing(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getBullishEngulfing(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddBullishThreeLineStrike();
                    Log.w("TESTRUN", "Testing ThreeLineStrike");
                }
            }
        }.start();
    }
    public void AddBullishThreeLineStrike(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getBullishThreeLineStrike(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddThreeInsideUp();
                    Log.w("TESTRUN", "Testing ThreeInsideUp");
                }
            }
        }.start();
    }
    public void AddThreeInsideUp(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getThreeInsideUp(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddEveningStar();
                    Log.w("TESTRUN", "Testing EveningStar");
                }
            }
        }.start();
    }


    // Down Patterns
    public void AddEveningStar(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getEveningStar(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount()-1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddHangingMan();
                    Log.w("TESTRUN", "Testing HangingMan");
                }
            }
        }.start();
    }
    public void AddHangingMan(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getHangingMan(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddShootingStar();
                    Log.w("TESTRUN", "Testing ShootingStar");
                }
            }
        }.start();
    }
    public void AddShootingStar(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getShootingStar(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddThreeBlackCrows();
                    Log.w("TESTRUN", "Testing ThreeBlackCrows");
                }
            }
        }.start();
    }
    public void AddThreeBlackCrows(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getThreeBlackCrows(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddBearishEngulfing();
                    Log.w("TESTRUN", "Testing BearishEngulfing");
                }
            }
        }.start();
    }
    public void AddBearishEngulfing(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getBearishEngulfing(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

        public void onTick(long millisUntilFinished) {
            if((i[0] != result.size())) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }
        }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddBearishThreeLineStrike();
                    Log.w("TESTRUN", "Testing BearishThreeLineStrike");
                }
            }
        }.start();
    }
    public void AddBearishThreeLineStrike(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getBearishThreeLineStrike(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                if(TestRun){
                    AddThreeInsideDown();
                    Log.w("TESTRUN", "Testing ThreeInsideDown");
                }
            }
        }.start();
    }
    public void AddThreeInsideDown(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.getThreeInsideDown(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    i[0]++;
                    candleStickChart.moveViewToX(Integer.MAX_VALUE);
                }
            }

            public void onFinish() {
                clickable = true;
                TestRun = false;
                Log.w("TESTRUN", "TEST COMPLETE");
            }
        }.start();
    }


    // Up/Down
    public void Go(){
        if(candleStickChart == null){
            SetCandleGraph();
        }

        clickable = false;
        if(last_answer.equals("UP")){
            down_button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            GoUp();
        }else{
            up_button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            GoDown();
        }
    }
    public void GoUp(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.GetUpTrendWithBrownian(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if(i[0] != result.size()) {
                    AddCandle(result.get(i[0]));
                    Log.w("GoUP", i[0] + "");
                    Log.w("GoUP", result.get(i[0]).getBodyRange() + "");
                }
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                next_button.setVisibility(View.VISIBLE);
                down_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.downRed, null)));
                up_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.upGreen, null)));
                if(debug){
                    DebugSpinnerPatterns.setVisibility(View.VISIBLE);
                }else{
                    DebugSpinnerPatterns.setVisibility(View.INVISIBLE);
                }

                if(TestRun){
                    GoDown();
                    Log.w("TESTRUN", "Testing Down");
                }
            }
        }.start();


    }
    public void GoDown(){
        if(candleStickChart == null){
            candleStickChart = v.findViewById(R.id.candle_stick_chart);
        }
        ArrayList<CandleEntry> result = sh.GetDownTrendWithBrownian(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        cdt = new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                if((i[0] != result.size())) {
                    AddCandle(result.get(i[0]));
                    Log.w("GoDOWN", i[0] + "");
                    Log.w("GoDOWN", result.get(i[0]).getBodyRange() + "");
                }
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish()
            {
                down_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.downRed, null)));
                up_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.upGreen, null)));
                next_button.setVisibility(View.VISIBLE);
                if(debug){
                    DebugSpinnerPatterns.setVisibility(View.VISIBLE);
                }else{
                    DebugSpinnerPatterns.setVisibility(View.INVISIBLE);
                }

                if(TestRun){
                    TestRun = false;
                    Log.w("TESTRUN", "Testing Complete");
                }
            }
        }.start();


    }

    // Helpers
    public void AddCandle(CandleEntry ce){
        try {
            CandleData newdata = candleStickChart.getData();

            newdata.removeEntry(0, 0);

            newdata.addEntry(ce, 0);

            candleStickChart.setData(newdata);
            candleStickChart.invalidate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        if(candleStickChart != null) {
            candleStickChart.invalidate();
            candleStickChart.clear();
        }
        if(cdt != null){
            cdt.cancel();
        }
        super.onPause();
    }
    @Override
    public void onDetach() {
        if(candleStickChart != null) {
            candleStickChart.invalidate();
            candleStickChart.clear();
        }
        if(cdt != null){
            cdt.cancel();
        }
        super.onDetach();
    }
    @Override
    public void onResume() {
        super.onResume();
        SetCandleGraph();
        down_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.downRed, null)));
        up_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.upGreen, null)));
        next_button.setVisibility(View.INVISIBLE);
    }
}