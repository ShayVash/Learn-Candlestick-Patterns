package com.kovetstech.candlestickpatterns.ui.lessons;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kovetstech.candlestickpatterns.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class lesson extends Fragment {

    private static final String ARG_PAGE = "param1";

    // TODO: Rename and change types of parameters
    Map<String, String> urls = new HashMap<>();
    private String mParam1;

    private InterstitialAd mInterstitialAd;

    public lesson() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PAGE);
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lesson, container, false);

        WebView wv = v.findViewById(R.id.web);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // !-- ADS --!
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(requireContext(),"ca-app-pub-1929848249759273/1613723827", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("SIMULATOR", "onAdLoaded");
                        displayInterstitial();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("SIMULATOR", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        // Basics
        urls.put("What Is Technical Analysis", "https://sites.google.com/view/candle-pattern-lessons/what-is-technical-analysis");
        urls.put("Key Terms Used In Technical Analysis", "https://sites.google.com/view/candle-pattern-lessons/key-terms-used-in-technical-analysis");
        urls.put("The Limitations Of Technical Analysis", "https://sites.google.com/view/candle-pattern-lessons/the-limitations-of-technical-analysis");
        urls.put("How To Read Candlestick Patterns", "https://sites.google.com/view/candle-pattern-lessons/intro-to-candle-sticks");
        urls.put("Charting On Different Time Frames", "https://sites.google.com/view/candle-pattern-lessons/charting-on-different-time-frames");
        urls.put("How To Identify Up & Down Trends", "https://sites.google.com/view/candle-pattern-lessons/how-to-identify-up-and-down-trends");
        urls.put("Support & Resistance", "https://sites.google.com/view/candle-pattern-lessons/support-resistance");

        // Indicators
        urls.put("Moving Averages", "https://sites.google.com/view/candle-pattern-lessons/moving-averages");
        urls.put("On Balance Volume (OBV)", "https://sites.google.com/view/candle-pattern-lessons/obv");
        urls.put("Relative Strength Index (RSI)", "https://sites.google.com/view/candle-pattern-lessons/rsi");
        urls.put("Stochastic Oscillator", "https://sites.google.com/view/candle-pattern-lessons/stochastic-oscillator");
        urls.put("Bollinger Bands", "https://sites.google.com/view/candle-pattern-lessons/bollinger-bands");
        urls.put("MACD", "https://sites.google.com/view/candle-pattern-lessons/macd");
        urls.put("Fibonacci Retracement", "https://sites.google.com/view/candle-pattern-lessons/fibonacci-retracement");
        urls.put("Ichimoku Cloud", "https://sites.google.com/view/candle-pattern-lessons/ichimoku-cloud");

        // Patterns
        urls.put("Hammer", "https://sites.google.com/view/candle-pattern-lessons/the-hammer-pattern");
        urls.put("Morning Star", "https://sites.google.com/view/candle-pattern-lessons/the-morning-star-pattern");
        urls.put("Evening Star", "https://sites.google.com/view/candle-pattern-lessons/the-eveningstar-pattern");
        urls.put("Three White Soldiers", "https://sites.google.com/view/candle-pattern-lessons/the-three-white-soldiers-pattern");
        urls.put("Three Black Crows", "https://sites.google.com/view/candle-pattern-lessons/the-three-black-crows-pattern");
        urls.put("Hanging Man", "https://sites.google.com/view/candle-pattern-lessons/the-hanging-man-pattern");
        urls.put("Shooting Star", "https://sites.google.com/view/candle-pattern-lessons/the-shooting-star-pattern");
        urls.put("Bullish Engulfing", "https://sites.google.com/view/candle-pattern-lessons/bullish-engulfing");
        urls.put("Bearish Engulfing", "https://sites.google.com/view/candle-pattern-lessons/bearish-engulfing");
        urls.put("Bullish Three Line Strike", "https://sites.google.com/view/candle-pattern-lessons/bullish-three-line-strike");
        urls.put("Bearish Three Line Strike", "https://sites.google.com/view/candle-pattern-lessons/bearish-three-line-strike");
        urls.put("Three Inside Up", "https://sites.google.com/view/candle-pattern-lessons/three-inside-down");
        urls.put("Three Inside Down", "https://sites.google.com/view/candle-pattern-lessons/three-inside-up");

        // Fundamental
        urls.put("Fundamental Analysis Basics", "https://sites.google.com/view/candle-pattern-lessons/fundamental-analysis-basics");
        urls.put("Financial Statements", "https://sites.google.com/view/candle-pattern-lessons/financial-statements");
        urls.put("Company Fundamentals", "https://sites.google.com/view/candle-pattern-lessons/company-fundamentals");

        // Load the lesson using the map searching for mParam1
        wv.loadUrl(urls.get(mParam1));



        return v;
    }

    // Ads
    public void displayInterstitial() {
        Random rnd = new Random();

        Log.w("Lesson", "Tried Displaying Ad");
        int ac = rnd.nextInt(5);
        // If Ads are loaded, show Interstitial else show nothing.

        if(ac == 2) {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(requireActivity());
                Log.w("MainActivity", "Banner adapter class name: " + mInterstitialAd.getResponseInfo().getMediationAdapterClassName());
                mInterstitialAd = null;
            } else {
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(requireContext(), "ca-app-pub-1929848249759273/1613723827", adRequest,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                // The mInterstitialAd reference will be null until
                                // an ad is loaded.
                                mInterstitialAd = interstitialAd;
                                displayInterstitial();
                                Log.i("LOAD AD", "onAdLoaded");
                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error
                                Log.d("LOAD AD", loadAdError.toString());
                                mInterstitialAd = null;
                            }
                        });
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
        }
    }

}