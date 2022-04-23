package com.kovetstech.candlestickpatterns.ui.simulator;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.os.Handler;
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
import com.github.mikephil.charting.data.LineDataSet;
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

    Button up_button;
    Button down_button;
    Button next_button;

    TextView simu_title;
    Spinner DebugSpinnerPatterns;
    boolean debug = false;

    CountDownTimer cdt;
    MediaPlayer mp;
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

        up_button = v.findViewById(R.id.up_button);
        up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInter("UP");
            }
        });

        down_button = v.findViewById(R.id.down_button);
        down_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInter("DOWN");
            }
        });

        next_button = v.findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_button.setVisibility(View.INVISIBLE);
                DebugSpinnerPatterns.setVisibility(View.INVISIBLE);

                if(debug){
                    GetPattern(DebugSpinnerPatterns.getSelectedItem().toString());
                }else{
                    GetRandomPattern();
                }

            }
        });

        simu_title = v.findViewById(R.id.simulator_title);
        simu_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                debug = !debug;
                Toast.makeText(getContext(), "Debug Mode " + debug, Toast.LENGTH_LONG).show();
            }
        });

        DebugSpinnerPatterns = v.findViewById(R.id.spinner);
        return v;
    }
    // Set
    public void SetCandleGraph(){
        candleStickChart.clear();

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
        yAxis.setDrawLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAvoidFirstLastClipping(true);

        Legend l = candleStickChart.getLegend();
        l.setEnabled(false);

        ArrayList<Entry> LineyValsCandleStick = sh.GetRandomChartWithBrownian(0, 0.2, 1);
        ArrayList<CandleEntry> CandleyValsCandleStick = sh.getRandomCandleChart(LineyValsCandleStick) ;

        CandleDataSet set1 = new CandleDataSet(CandleyValsCandleStick, "");
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
                mp.start();
            } else {
                mp = MediaPlayer.create(getContext(), R.raw.wrong);
                mp.start();
            }


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

        switch (rnd.nextInt(9)){
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
        }

        Log.w("SimulatorFragment", "Got " + last_pattern + " Pattern");
    }
    public void GetPattern(String name){
        switch (name){
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
        }

        Log.w("SimulatorFragment", "Got " + last_pattern + " Pattern");
    }

    // Up Patterns
    public void AddHammer(){
        ArrayList<CandleEntry> result = sh.getHammer(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        cdt = new CountDownTimer(2500, 500) {

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
        cdt = new CountDownTimer(2500, 500) {

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
    public void AddThreeWhiteSoldiers(){
        ArrayList<CandleEntry> result = sh.getThreeWhiteSoldiers(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        cdt = new CountDownTimer(3000, 500) {

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
    public void AddBullishEngulfing(){
        ArrayList<CandleEntry> result = sh.getBullishEngulfing(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        cdt = new CountDownTimer(2500, 500) {

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
        ArrayList<CandleEntry> result = sh.getEveningStar(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount()-1));

        clickable = false;

        final int[] i = {0};
        cdt = new CountDownTimer(2500, 500) {

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
        cdt = new CountDownTimer(2000, 500) {

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
    public void AddShootingStar(){
        ArrayList<CandleEntry> result = sh.getShootingStar(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        cdt = new CountDownTimer(2000, 500) {

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
    public void AddThreeBlackCrows(){
        ArrayList<CandleEntry> result = sh.getThreeBlackCrows(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        cdt = new CountDownTimer(3000, 500) {

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
    public void AddBearishEngulfing(){
        ArrayList<CandleEntry> result = sh.getBearishEngulfing(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        final int[] i = {0};
        cdt = new CountDownTimer(2500, 500) {

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
            down_button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            GoUp();
        }else{
            up_button.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            GoDown();
        }
    }
    public void GoUp(){
        ArrayList<CandleEntry> result = sh.GetUpTrendWithBrownian(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish() {
                next_button.setVisibility(View.VISIBLE);
                down_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.downRed)));
                up_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.upGreen)));
                if(debug){
                    DebugSpinnerPatterns.setVisibility(View.VISIBLE);
                }else{
                    DebugSpinnerPatterns.setVisibility(View.INVISIBLE);
                }
            }
        }.start();
    }
    public void GoDown(){
        ArrayList<CandleEntry> result = sh.GetDownTrendWithBrownian(candleStickChart.getCandleData().getDataSets().get(0).getEntryForIndex(candleStickChart.getCandleData().getDataSets().get(0).getEntryCount() -1));

        clickable = false;

        int time = 500 * result.size();

        final int[] i = {0};
        new CountDownTimer(time, 500) {

            public void onTick(long millisUntilFinished) {
                AddCandle(result.get(i[0]));
                i[0]++;
                candleStickChart.moveViewToX(Integer.MAX_VALUE);
            }

            public void onFinish()
            {
                down_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.downRed)));
                up_button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.upGreen)));
                next_button.setVisibility(View.VISIBLE);
                if(debug){
                    DebugSpinnerPatterns.setVisibility(View.VISIBLE);
                }else{
                    DebugSpinnerPatterns.setVisibility(View.INVISIBLE);
                }
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

    @Override
    public void onPause() {
        candleStickChart.invalidate();
        candleStickChart.clear();
        if(cdt != null){
            cdt.cancel();
        }
        super.onPause();
    }

}