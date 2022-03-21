package com.kovetstech.candlestickpatterns.ui.simulator;

import android.util.Log;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;
import java.util.Random;

public class SimulatorHelper {

    int CurrentIndex = 0;

    public ArrayList<CandleEntry> getRandomStartingChart(){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();


        int StartingPrice = 1000;
        int CandleStickAmount = 25;

        result.add(GetFirstEntry(StartingPrice));

        for(int i = 0; i <= CandleStickAmount; i++) {

            result.add(GetNewEntry(result.get(result.size()-1)));

            CurrentIndex = i;
        }

        result.remove(1);
        result.remove(1);
        return result;
    }

    // Up
    public ArrayList<CandleEntry> getHammer(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(result.size()-1)));
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
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(result.size()-1)));


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
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(result.size()-1)));
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
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(result.size()-1)));
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


    // Down
    public ArrayList<CandleEntry> getEveningStar(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(result.size()-1)));
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
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(result.size()-1)));
        result.add(GetUpEntry(result.get(result.size()-1)));


        if(rnd.nextBoolean()) {
            int ShadowH = (int) result.get(result.size() - 1).getClose() + (rnd.nextInt(15)+ 15);
            int Open = ShadowH;
            int Close = Open - (rnd.nextInt(30) + 80);
            int ShadowL = Close - (rnd.nextInt(150) + 200);
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }else{
            int ShadowH = (int) result.get(result.size() - 1).getClose() + (rnd.nextInt(15)+ 15);
            int Close = ShadowH;
            int Open = Close - (rnd.nextInt(30) + 80);
            int ShadowL = Close - (rnd.nextInt(150) + 200);
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }


        return result;
    }
    public ArrayList<CandleEntry> getShootingStar(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(result.size()-1)));
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
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(result.size()-1)));
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
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetUpEntry(LastEntry));
        result.add(GetUpEntry(result.get(result.size()-1)));
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
    public CandleEntry GetNewEntry(CandleEntry LastEntry){
        Random rnd = new Random();
        CandleEntry result;

        if(rnd.nextBoolean()){
            return GetUpEntry(LastEntry);
        }else{
            return GetDownEntry(LastEntry);
        }
    }
    public CandleEntry GetFirstEntry(int start){
        Random rnd = new Random();

        int StartingPrice = start;

        int ShadowL = StartingPrice + rnd.nextInt(35);
        int Open = ShadowL + rnd.nextInt(100);
        int Close = Open + (rnd.nextInt(200) + 100);
        int ShadowH = Close + rnd.nextInt(35);

        if(rnd.nextBoolean()){
            return GetUpEntry(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }else{
            return GetDownEntry(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }

    }

    public ArrayList<CandleEntry> GetDownTrend(CandleEntry LastEntry){
        Random rnd = new Random();
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        result.add(GetDownEntry(LastEntry));

        int UpCounter = 3;
        for(int i = 0; i<10; i++){
            if((rnd.nextInt(100) <= 30) && UpCounter >= 0){
                UpCounter--;
                result.add(GetUpEntry(result.get(result.size()-1)));
            }else{
                result.add(GetDownEntry(result.get(result.size()-1)));
            }
        }

        return result;
    }
    public ArrayList<CandleEntry> GetUpTrend(CandleEntry LastEntry){
        Random rnd = new Random();
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        result.add(GetUpEntry(LastEntry));

        int DownCounter = 3;
        for(int i = 0; i<10; i++){
            if((rnd.nextInt(100) <= 30) && DownCounter >= 0){
                DownCounter--;
                result.add(GetDownEntry(result.get(result.size()-1)));
            }else{
                result.add(GetUpEntry(result.get(result.size()-1)));
            }
        }

        return result;
    }
}

