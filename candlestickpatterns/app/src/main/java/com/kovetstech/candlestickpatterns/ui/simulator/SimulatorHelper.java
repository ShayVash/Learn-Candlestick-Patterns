package com.kovetstech.candlestickpatterns.ui.simulator;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;
import java.util.Random;

public class SimulatorHelper {

    int CurrentIndex = 0;

    public ArrayList<CandleEntry> getRandomStartingChart(){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();


        int StartingPrice = rnd.nextInt(200) + 100;
        int CandleStickAmount = rnd.nextInt(20) + 12;

        result.add(GetNewEntry(StartingPrice));

        for(int i = 0; i <= CandleStickAmount; i++) {

            result.add(GetNewEntry((int) result.get(result.size()-1).getClose()));

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



        int ShadowL = (int) result.get(result.size()-1).getClose() - (rnd.nextInt(50) + 70);
        int Open = ShadowL + rnd.nextInt(100) + 150;
        int Close = Open + (rnd.nextInt(100) + 35);
        int ShadowH = Close;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));

        result.add(GetUpEntry(result.get(result.size()-1)));
        result.add(GetUpEntry(result.get(result.size()-1)));

        return result;
    }
    public ArrayList<CandleEntry> getMorningStar(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(result.size()-1)));

        int ShadowH = (int) result.get(result.size()-1).getClose() + rnd.nextInt(15);
        int Open = ShadowH - rnd.nextInt(30) - 50;
        int Close = Open - (rnd.nextInt(200) + 200);
        int ShadowL = Close - (rnd.nextInt(30) + 50);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down

        ShadowH = Close + rnd.nextInt(15) + 5;
        Open = ShadowH - rnd.nextInt(30) - 50;
        Close = Open + (rnd.nextInt(15) + 10);
        ShadowL = Open - (rnd.nextInt(15) + 5);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Down

        ShadowL = Close + (rnd.nextInt(15) + 10);
        Open = ShadowL + rnd.nextInt(30) - 50;
        Close = Open + (rnd.nextInt(200) + 200);
        ShadowH = Close + rnd.nextInt(30) + 50;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        return result;
    }

    // Down
    public ArrayList<CandleEntry> getEveningStar(CandleEntry LastEntry){
        ArrayList<CandleEntry> result = new ArrayList<CandleEntry>();

        Random rnd = new Random();

        result.add(GetDownEntry(LastEntry));
        result.add(GetDownEntry(result.get(result.size()-1)));

        int ShadowL = (int) result.get(result.size()-1).getClose() + (rnd.nextInt(15) + 10);
        int Open = ShadowL + rnd.nextInt(30) - 50;
        int Close = Open + (rnd.nextInt(200) + 200);
        int ShadowH = Close + rnd.nextInt(30) + 50;
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close)); // Big Up

        ShadowH = Close + rnd.nextInt(15) + 5;
        Open = ShadowH - rnd.nextInt(30) - 50;
        Close = Open + (rnd.nextInt(15) + 10);
        ShadowL = Open - (rnd.nextInt(15) + 5);
        result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));

        ShadowH = Close + rnd.nextInt(15);
        Open = ShadowH - rnd.nextInt(30) - 50;
        Close = Open - (rnd.nextInt(200) + 200);
        ShadowL = Close - (rnd.nextInt(30) + 50);
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
            int ShadowL = Close - (rnd.nextInt(150) + 375);
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }else{
            int ShadowH = (int) result.get(result.size() - 1).getClose() + (rnd.nextInt(15)+ 15);
            int Close = ShadowH;
            int Open = Close - (rnd.nextInt(30) + 80);
            int ShadowL = Close - (rnd.nextInt(150) + 375);
            result.add(new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close));
        }


        return result;
    }

    public CandleEntry GetUpEntry(CandleEntry LastEntry){
        Random rnd = new Random();

        int ShadowH = 0;
        int ShadowL = 0;
        int Open = 0;
        int Close = 0;

        int StartingPrice = 0;

        if(rnd.nextBoolean() == true){
            StartingPrice = (int) LastEntry.getClose() + rnd.nextInt(15);
        }else{
            StartingPrice = (int) LastEntry.getClose() - rnd.nextInt(15);
        }

        ShadowL = StartingPrice + rnd.nextInt(35);
        Open = ShadowL + rnd.nextInt(100);
        Close = Open + (rnd.nextInt(200) + 100);
        ShadowH = Close + rnd.nextInt(35);

        return new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close);
    }
    public CandleEntry GetDownEntry(CandleEntry LastEntry){
        Random rnd = new Random();

        int ShadowH = 0;
        int ShadowL = 0;
        int Open = 0;
        int Close = 0;

        int StartingPrice = 0;

        if(rnd.nextBoolean() == true){
            StartingPrice = (int) LastEntry.getClose() + rnd.nextInt(15);
        }else{
            StartingPrice = (int) LastEntry.getClose() - rnd.nextInt(15);
        }

        ShadowH = StartingPrice + rnd.nextInt(50);
        Open = ShadowH - rnd.nextInt(100);
        Close = Open - (rnd.nextInt(200) + 100);
        ShadowL = Close - rnd.nextInt(50);

        return new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close);
    }
    public CandleEntry GetNewEntry(int start){
        Random rnd = new Random();

        int ShadowH = 0;
        int ShadowL = 0;
        int Open = 0;
        int Close = 0;

        int StartingPrice = start;

        if(rnd.nextBoolean() == true){
            StartingPrice = (int) start + rnd.nextInt(15);
        }else{
            StartingPrice = (int) start - rnd.nextInt(15);
        }

        if(rnd.nextBoolean() == true){ // Going Up
                ShadowL = StartingPrice + rnd.nextInt(35);
                Open = ShadowL + rnd.nextInt(100);
                Close = Open + (rnd.nextInt(200) + 100);
                ShadowH = Close + rnd.nextInt(35);
            }else{ // Going down
                ShadowH = StartingPrice + rnd.nextInt(50);
                Open = ShadowH - rnd.nextInt(100);
                Close = Open - (rnd.nextInt(200) + 100);
                ShadowL = Close - rnd.nextInt(50);
            }


        return new CandleEntry(CurrentIndex++, ShadowH, ShadowL, Open, Close);

    }
}

