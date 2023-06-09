package com.kovetstech.candlestickpatterns.ui.simulator;

import android.util.Log;

import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;


import org.ojalgo.random.process.GeometricBrownianMotion;
import org.ojalgo.random.process.RandomProcess;

import java.util.ArrayList;
import java.util.Random;

public class SimulatorHelper {

    int CurrentIndex = 0;

    // Up
    public ArrayList<CandleEntry> getHammer(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(0)));
        result.add(GetDownEntry(result.get(result.size()-1)));

        int StartingPrice;
        if(result.get(result.size()-1).getClose() > result.get(result.size()-1).getOpen()){
            StartingPrice = rnd.nextInt((int) result.get(result.size()-1).getBodyRange()) + (int) result.get(result.size()-1).getOpen();
        }else{
            StartingPrice = rnd.nextInt((int) result.get(result.size()-1).getBodyRange()) + (int) result.get(result.size()-1).getClose();
        }

        int Open = StartingPrice;
        int Close = Open + (rnd.nextInt(50) + 70);
        int ShadowL = Open - ((Close - Open) * 2) - (rnd.nextInt(25) + 25);
        int ShadowH = Close + rnd.nextInt(25);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));

        result.add(GetUpEntry(result.get(result.size()-1)));


        return result;
    }
    public ArrayList<CandleEntry> getMorningStar(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(0)));


        int StartingPrice;

        if(LastEntry.getClose() > LastEntry.getOpen()){
            StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getOpen();
        }else{
            StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getClose();
        }

        int Open = StartingPrice;
        int Close = Open - (rnd.nextInt(200) + 200);
        int ShadowL = Close - (rnd.nextInt(30) + 50);
        int ShadowH = Open + rnd.nextInt(30) - 50;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down

        StartingPrice -= (Open - Close);

        Open = Close + rnd.nextInt(25) + 10;
        Close = Open + (rnd.nextInt(15) + 10);
        ShadowL =  Open - (rnd.nextInt(15) + 5);
        ShadowH = Close + rnd.nextInt(15) - 10;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down


        Open = StartingPrice;
        Close = Open + (rnd.nextInt(200) + 200);
        ShadowL = Close - (rnd.nextInt(30) + 50);
        ShadowH = Open + rnd.nextInt(30) - 50;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down
        return result;
    }
    public ArrayList<CandleEntry> getThreeWhiteSoldiers(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(0)));
        result.add(GetDownEntry(result.get(result.size()-1)));

        int ShadowH = (int) result.get(result.size()-1).getClose() + (rnd.nextInt(50) + 85);
        int Close = ShadowH - rnd.nextInt(50) - 15;
        int Open = Close - (rnd.nextInt(150) + 100);
        int ShadowL = Close - (rnd.nextInt(50) + 15);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        Open = rnd.nextInt(Close - Open) + (Open + 15) ;
        Close = Close + rnd.nextInt(150) + 100;
        ShadowL = Open - (rnd.nextInt(50));
        ShadowH = Close + (rnd.nextInt(50));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up



        Open = rnd.nextInt(Close - Open) + (Open + 15);
        Close = Close + rnd.nextInt(150) + 100;
        ShadowL = Open - (rnd.nextInt(50));
        ShadowH = Close + (rnd.nextInt(50));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        return result;
    }
    public ArrayList<CandleEntry> getBullishEngulfing(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(0)));
        result.add(GetDownEntry(result.get(result.size()-1)));


        int StartingPrice;
        if(result.get(result.size()-1).getClose() > result.get(result.size()-1).getOpen()){
            StartingPrice = rnd.nextInt((int) result.get(result.size()-1).getBodyRange()) + (int) result.get(result.size()-1).getOpen();
        }else{
            StartingPrice = rnd.nextInt((int) result.get(result.size()-1).getBodyRange()) + (int) result.get(result.size()-1).getClose();
        }

        int WickSize = rnd.nextInt(50) + 25;
        int LOpen = StartingPrice;
        int Close = LOpen - (rnd.nextInt(50) + 75);
        int ShadowL = Close - WickSize;
        int ShadowH = LOpen + WickSize;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, LOpen, Close));


        int Open = Close;
        Close = LOpen + (rnd.nextInt(25) + 50);
        ShadowL = Close + WickSize + 25;
        ShadowH = Open - WickSize - 25;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));


        return result;
    }
    public ArrayList<CandleEntry> getBullishThreeLineStrike(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(0)));

        int Top;

        int ShadowH = (int) result.get(result.size()-1).getClose() + (rnd.nextInt(50) + 85);
        int Open = ShadowH - rnd.nextInt(50) - 15;
        Top = Open;
        int LClose = Open - (rnd.nextInt(150) + 100);
        int ShadowL = LClose - (rnd.nextInt(50) + 15);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, LClose)); // Big Up

        Open = rnd.nextInt(Open - LClose) + (LClose - 15);
        int Close = LClose - (rnd.nextInt(150) + 100);
        ShadowL = Close - (rnd.nextInt(50) + 15);
        ShadowH = Open + (rnd.nextInt(15));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        int Radded = (rnd.nextInt(50) + 85);


        Open -= Radded;
        Close -= Radded;
        ShadowL -= Radded;
        ShadowH = Open + (rnd.nextInt(15));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        Open = ShadowL - rnd.nextInt(20) - 15;
        LClose = Top + (rnd.nextInt(100) + 50);
        ShadowL = LClose - (rnd.nextInt(50) + 15);
        ShadowH = (int) result.get(result.size()-1).getClose() + (rnd.nextInt(50) + 85);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, LClose)); // Big Up

        result.add(GetDownEntry(result.get(result.size()-1)));
        return result;
    }
    public ArrayList<CandleEntry> getThreeInsideUp(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(0)));



        int WickSize = rnd.nextInt(50) + 25;

        int Open = (int) result.get(result.size()-1).getClose();
        int Close = ((int)(result.get(result.size()-1)).getOpen()) - (rnd.nextInt(25) + 50);
        int ShadowL = Close - WickSize - 25;
        int ShadowH = Open + WickSize + 25;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));

        int LOpen = Close + rnd.nextInt((int) result.get(result.size()-1).getBodyRange()/3);
        Close = LOpen + (rnd.nextInt((int) result.get(result.size()-1).getBodyRange()/2)) + 35 ;
        ShadowL = LOpen - WickSize;
        ShadowH = Close + WickSize;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, LOpen, Close));

        LOpen = LOpen +  rnd.nextInt((int) result.get(result.size()-2).getBodyRange()/4) ;
        Close = LOpen + (rnd.nextInt(50) + (int) (result.get(result.size()-2).getBodyRange()));
        ShadowL = LOpen - WickSize;
        ShadowH = Close + WickSize;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, LOpen, Close));

        return result;
    }

    // Down
    public ArrayList<CandleEntry> getEveningStar(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(0)));
        int StartingPrice;

        if(result.get(result.size()-1).getClose() > result.get(result.size()-1).getOpen()){
            StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getOpen();
        }else{
            StartingPrice = rnd.nextInt((int) result.get(result.size()-1).getBodyRange()) + (int) result.get(result.size()-1).getClose();
        }

        int Open = StartingPrice;
        int Close = Open + (rnd.nextInt(200) + 200);
        int ShadowL = Open - (rnd.nextInt(30) + 50);
        int ShadowH = Close + rnd.nextInt(30) + 50;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down

        StartingPrice -= (Open - Close);

        Open = Close - rnd.nextInt(25) + 10;
        Close = Open - (rnd.nextInt(15) + 10);
        ShadowL =  Close - (rnd.nextInt(15) + 10);
        ShadowH = Open + rnd.nextInt(15) - 10;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down


        Open = StartingPrice;
        Close = Open - (rnd.nextInt(200) + 200);
        ShadowL = Close - (rnd.nextInt(30) + 50);
        ShadowH = Open + rnd.nextInt(30) + 50;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down


        return result;
    }
    public ArrayList<CandleEntry> getHangingMan(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(0)));
        result.add(GetUpEntry(result.get(result.size()-1)));


        if(rnd.nextBoolean()) {
            int ShadowH = (int) result.get(result.size() - 1).getClose() + (rnd.nextInt(15)+ 15);
            int Close = ShadowH - (rnd.nextInt(30) + 80);
            int ShadowL = Close - (rnd.nextInt(150) + 200);
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, ShadowH, Close));
        }else{
            int ShadowH = (int) result.get(result.size() - 1).getClose() + (rnd.nextInt(15)+ 15);
            int Open = ShadowH - (rnd.nextInt(30) + 80);
            int ShadowL = ShadowH - (rnd.nextInt(150) + 200);
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, ShadowH));
        }


        return result;
    }
    public ArrayList<CandleEntry> getShootingStar(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(0)));
        result.add(GetUpEntry(result.get(result.size()-1)));


        if(rnd.nextBoolean()) {
            int Open = (int) result.get(result.size() - 1).getClose() + (rnd.nextInt(15)+ 15);
            int Close = Open + (rnd.nextInt(30) + 80);
            int ShadowH = Close + ((Close - Open ) * 2) + (rnd.nextInt((Close - Open ) / 4));
            int ShadowL = Open - (rnd.nextInt((Close - Open ) / 4));
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));

            Log.w("SimulatorHelper", "Top");
        }else{
            int Open = (int) result.get(result.size() - 1).getClose() + (rnd.nextInt(15)+ 15);
            int Close = Open - (rnd.nextInt(30) + 80);
            int ShadowH = Open + ((Open - Close ) * 2) + (rnd.nextInt((Open - Close ) / 4));
            int ShadowL = Close - (rnd.nextInt((Open - Close ) / 4));
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));

            Log.w("SimulatorHelper", "Bottom");
        }


        return result;
    }
    public ArrayList<CandleEntry> getThreeBlackCrows(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(0)));
        result.add(GetUpEntry(result.get(result.size()-1)));

        int ShadowH = (int) result.get(result.size()-1).getClose() + (rnd.nextInt(50) + 85);
        int Open = ShadowH - rnd.nextInt(50) - 15;
        int LClose = Open - (rnd.nextInt(150) + 100);
        int ShadowL = LClose - (rnd.nextInt(50) + 15);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, LClose)); // Big Up

        Open = rnd.nextInt(Open - LClose) + (LClose - 15);
        int Close = LClose - (rnd.nextInt(150) + 100);
        ShadowL = Close - (rnd.nextInt(50) + 15);
        ShadowH = Open + (rnd.nextInt(15));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        int Radded = (rnd.nextInt(50) + 85);


        Open -= Radded;
        Close -= Radded;
        ShadowL -= Radded;
        ShadowH = Open + (rnd.nextInt(15));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        return result;
    }
    public ArrayList<CandleEntry> getBearishEngulfing(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(0)));
        result.add(GetUpEntry(result.get(result.size()-1)));


        int StartingPrice;
        if(result.get(result.size()-1).getClose() > result.get(result.size()-1).getOpen()){
            StartingPrice = rnd.nextInt((int) result.get(result.size()-1).getBodyRange()) + (int) result.get(result.size()-1).getOpen();
        }else{
            StartingPrice = rnd.nextInt((int) result.get(result.size()-1).getBodyRange()) + (int) result.get(result.size()-1).getClose();
        }

        int WickSize = rnd.nextInt(50) + 25;

        int LOpen = StartingPrice;
        int Close = LOpen + (rnd.nextInt(50) + 75);
        int ShadowL = LOpen - WickSize;
        int ShadowH = Close + WickSize;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, LOpen, Close));


        int Open = Close;
        Close = LOpen - (rnd.nextInt(25) + 50);
        ShadowL = Close - WickSize - 25;
        ShadowH = Open + WickSize + 25;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));


        return result;
    }
    public ArrayList<CandleEntry> getBearishThreeLineStrike(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(0)));

        int Top;

        int ShadowH = (int) result.get(result.size()-1).getClose() + (rnd.nextInt(50) + 85);
        int Close = ShadowH - rnd.nextInt(50) - 15;
        int Open = Close - (rnd.nextInt(150) + 100);
        Top = Open;
        int ShadowL = Close - (rnd.nextInt(50) + 15);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        Open = rnd.nextInt(Close - Open) + (Open + 15) ;
        Close = Close + rnd.nextInt(150) + 100;
        ShadowL = Open - (rnd.nextInt(50));
        ShadowH = Close + (rnd.nextInt(50));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        Open = rnd.nextInt(Close - Open) + (Open + 15);
        Close = Close + rnd.nextInt(150) + 100;
        ShadowL = Open - (rnd.nextInt(50));
        ShadowH = Close + (rnd.nextInt(50));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        Open = Close + (rnd.nextInt(75) + 30);
        Close = Top - rnd.nextInt(75) + 55;
        ShadowL = Close - (rnd.nextInt(50));
        ShadowH = Open + (rnd.nextInt(50));
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        result.add(GetUpEntry(result.get(result.size()-1)));

        return result;
    }
    public ArrayList<CandleEntry> getThreeInsideDown(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(0)));




        int WickSize = rnd.nextInt(50) + 25;

        int Open = (int) result.get(result.size()-1).getClose() - rnd.nextInt((int) result.get(result.size()-1).getBodyRange()/3);
        int Close = ((int)(result.get(result.size()-1)).getOpen()) + rnd.nextInt((int) result.get(result.size()-1).getBodyRange()/3);
        int ShadowL = Close - WickSize - 25;
        int ShadowH = Open + WickSize + 25;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));

        int LOpen = Close - rnd.nextInt(20);
        Close = (int) result.get(result.size()-2).getOpen() - rnd.nextInt((int) result.get(result.size()-1).getBodyRange()/3);
        ShadowL = LOpen - WickSize;
        ShadowH = Close + WickSize;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, LOpen, Close));

        return result;
    }

    public CandleEntry GetUpEntry(CandleEntry LastEntry){
        Random rnd = new Random();

        int StartingPrice;

        if(LastEntry.getClose() > LastEntry.getOpen()){
            StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getOpen();
        }else{
            StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getClose();
        }

        int Open = StartingPrice;
        int Close;
        int ShadowL;
        int ShadowH;

            Close = Open + (rnd.nextInt(200) + 25);
            ShadowL = Open - rnd.nextInt(75);
            ShadowH = Close + rnd.nextInt(75);


        return new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close);
    }
    public CandleEntry GetDownEntry(CandleEntry LastEntry){
        Random rnd = new Random();

        int StartingPrice;

        if(LastEntry.getClose() > LastEntry.getOpen()){
            StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getOpen();
        }else{
            StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getClose();
        }

        int Open = StartingPrice;
        int Close;
        int ShadowL;
        int ShadowH;

            Close = Open - (rnd.nextInt(200) + 25);
            ShadowL = Close - rnd.nextInt(75);
            ShadowH = Open + rnd.nextInt(75);


        return new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close);
    }
    public CandleEntry GetFirstEntry(int start){
        Random rnd = new Random();

        int ShadowL = start + rnd.nextInt(35);
        int Open = ShadowL + rnd.nextInt(100);
        int Close = Open + (rnd.nextInt(200) + 100);
        int ShadowH = Close + rnd.nextInt(35);

        if(rnd.nextBoolean()){
            return GetUpEntry(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }else{
            return GetDownEntry(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }

    }

    // New System
    public ArrayList<CandleEntry> getRandomCandleChart(ArrayList<Entry> BrownianChart){
        ArrayList<CandleEntry> result = new ArrayList<>();
        Random rnd = new Random();

        int StartingPrice = 5000;
        result.add(GetFirstEntry(StartingPrice));

        for(int i = 0; i<BrownianChart.size()-1; i += 1){
            CandleEntry LastEntry = result.get(result.size()-1);

            try{
                if (LastEntry.getClose() > LastEntry.getOpen()) {
                    StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getOpen();
                } else {
                    StartingPrice = rnd.nextInt((int) (LastEntry.getBodyRange())) + (int) LastEntry.getClose();
                }
            }catch (Exception ignored){

            }

            int Open = StartingPrice;
            int Close;
            int ShadowL;
            int ShadowH;

            if((i > 0) && (BrownianChart.get(i).getY() > BrownianChart.get(i-1).getY())) {
                Close = Open +  ((int) (BrownianChart.get(i).getY()));
                ShadowL = Open - rnd.nextInt(75);
                ShadowH = Close + rnd.nextInt(75);
            }else{
                Close = Open - ((int) (BrownianChart.get(i).getY()));
                ShadowL = Close - rnd.nextInt(75);
                ShadowH = Open + rnd.nextInt(75);
            }

            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }

        result.remove(0);
        return result;
    }

    public ArrayList<Entry> GetRandomChartWithBrownian(double drift, double diffusion, double stepSize){
        ArrayList<Entry> result = new ArrayList<>();

        GeometricBrownianMotion gbm = new GeometricBrownianMotion(drift, diffusion);
        gbm.setValue(200);
        RandomProcess.SimulationResults sr =  gbm.simulate(1,25, stepSize);

        for(int i = 0; i<=25; i++){
            Log.w("GBM", ""+ sr.getScenario(0).get(i));
            result.add(new Entry(i, sr.getScenario(0).get(i).floatValue()));
        }

        return result;
    }

    public ArrayList<CandleEntry> GetDownTrendWithBrownian(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();
        Random rnd = new Random();
        int StartingPrice = (int) LastEntry.getClose();

        // Line
        ArrayList<Entry> BrownianDownTrend = new ArrayList<>();

        double localDrift = -0.2;
        double diffusionFunction = 0.2;

        GeometricBrownianMotion gbm = new GeometricBrownianMotion(localDrift, diffusionFunction);
        if(LastEntry.getClose() <= 1000){
            gbm.setValue(50);
        }else gbm.setValue(200);

        RandomProcess.SimulationResults sr =  gbm.simulate(1,7, 1);

        for(int i = 0; i<=7; i++){
            BrownianDownTrend.add(new Entry(i, sr.getScenario(0).get(i).floatValue()));
        }


        result.add(LastEntry);
        for(int i = 0; i<BrownianDownTrend.size()-1; i += 1){
            LastEntry = result.get(result.size()-1);
            try{
                if (LastEntry.getClose() > LastEntry.getOpen()) {
                    StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getOpen();
                } else {
                    StartingPrice = rnd.nextInt((int) (LastEntry.getBodyRange())) + (int) LastEntry.getClose();
                }
            }catch (Exception ignored){

            }

            int Open = StartingPrice;
            int Close;
            int ShadowL;
            int ShadowH;

            if((i > 0) && (BrownianDownTrend.get(i).getY() > BrownianDownTrend.get(i-1).getY())) {
                Close = Open + ((int) (BrownianDownTrend.get(i).getY()));
                ShadowL = Open - rnd.nextInt(75);
                ShadowH = Close + rnd.nextInt(75);
            }else{
                Close = Open - ((int) (BrownianDownTrend.get(i).getY()));
                ShadowL = Close - rnd.nextInt(75);
                ShadowH = Open + rnd.nextInt(75);
            }

            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }

        return result;
    }
    public ArrayList<CandleEntry> GetUpTrendWithBrownian(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<>();
        Random rnd = new Random();
        int StartingPrice = (int) LastEntry.getClose();

        // Line
        ArrayList<Entry> BrownianDownTrend = new ArrayList<>();

        double localDrift = 0.15;
        double diffusionFunction = 0.2;

        GeometricBrownianMotion gbm = new GeometricBrownianMotion(localDrift, diffusionFunction);
        if(LastEntry.getClose() >= 9000){
            gbm.setValue(50);
        }else gbm.setValue(200);
        RandomProcess.SimulationResults sr =  gbm.simulate(1,7, 1);

        for(int i = 0; i<=7; i++){
            BrownianDownTrend.add(new Entry(i, sr.getScenario(0).get(i).floatValue()));
        }


        result.add(LastEntry);
        for(int i = 0; i<BrownianDownTrend.size()-1; i += 1){
            LastEntry = result.get(result.size()-1);
            try{
                if (LastEntry.getClose() > LastEntry.getOpen()) {
                    StartingPrice = rnd.nextInt((int) LastEntry.getBodyRange()) + (int) LastEntry.getOpen();
                } else {
                    StartingPrice = rnd.nextInt((int) (LastEntry.getBodyRange())) + (int) LastEntry.getClose();
                }
            }catch (Exception ignored){

            }

            int Open = StartingPrice;
            int Close;
            int ShadowL;
            int ShadowH;

            if((i > 0) && (BrownianDownTrend.get(i).getY() > BrownianDownTrend.get(i-1).getY())) {
                Close = Open + ((int) (BrownianDownTrend.get(i).getY()));
                ShadowL = Open - rnd.nextInt(75);
                ShadowH = Close + rnd.nextInt(75);
            }else{
                Close = Open - ((int) (BrownianDownTrend.get(i).getY()));
                ShadowL = Close - rnd.nextInt(75);
                ShadowH = Open + rnd.nextInt(75);
            }

            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }

        return result;
    }
}

