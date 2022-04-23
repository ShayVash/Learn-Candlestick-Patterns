package com.kovetstech.candlestickpatterns.ui.simulator;

import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

public class TrendHelper {
    ArrayList<ArrayList<Integer>> UpTrendEntrys = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> DownTrendEntrys = new ArrayList<ArrayList<Integer>>();

    public TrendHelper(){
        SetUpTrendEntrys();
        SetDownTrendEntrys();
    }
    public void SetUpTrendEntrys(){

    }
    public void SetDownTrendEntrys(){
        ArrayList<Integer> E = new ArrayList<Integer>();


    }

    public ArrayList<Integer> GetDownTrend(){
        Random rnd = new Random();
        ArrayList<Integer> candles = DownTrendEntrys.get(rnd.nextInt(DownTrendEntrys.size()) + 1);

        return candles;
    }
    public ArrayList<CandleEntry> GetUpTrend(){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();


        return result;
    }
}
