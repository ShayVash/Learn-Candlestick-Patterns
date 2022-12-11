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

import java.util.Random;

public class lesson extends Fragment {

    private static final String ARG_PAGE = "param1";

    // TODO: Rename and change types of parameters
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
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("SIMULATOR", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });


        if(mParam1.equals("What Is Technical Analysis")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/what-is-technical-analysis");
        }
        if(mParam1.equals("How To Read Candlestick Patterns")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/intro-to-candle-sticks");
        }
        if(mParam1.equals("Charting On Different Time Frames")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/charting-on-different-time-frames");
        }
        if(mParam1.equals("How To Identify Up & Down Trends")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/how-to-identify-up-and-down-trends");
        }

        if(mParam1.equals("Moving Averages")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/moving-averages");
        }
        if(mParam1.equals("On Balance Volume (OBV)")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/obv");
        }
        if(mParam1.equals("Relative Strength Index (RSI)")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/rsi");
        }

        if(mParam1.equals("Hammer")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-hammer-pattern");
        }
        if(mParam1.equals("Morning Star")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-morning-star-pattern");
        }
        if(mParam1.equals("Evening Star")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-eveningstar-pattern");
        }
        if(mParam1.equals("Three White Soldiers")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-three-white-soldiers-pattern");
        }
        if(mParam1.equals("Three Black Crows")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-three-black-crows-pattern");
        }
        if(mParam1.equals("Hanging Man")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-hanging-man-pattern");
        }
        if(mParam1.equals("Shooting Star")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-shooting-star-pattern");
        }
        if(mParam1.equals("Bullish Engulfing")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bullish-engulfing");
        }
        if(mParam1.equals("Bearish Engulfing")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bearish-engulfing");
        }
        if(mParam1.equals("Bullish Three Line Strike")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bullish-three-line-strike");
        }
        if(mParam1.equals("Bearish Three Line Strike")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bearish-three-line-strike");
        }
        if(mParam1.equals("Three Inside Up")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/three-inside-down");
        }
        if(mParam1.equals("Three Inside Down")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/three-inside-up");
        }


        displayInterstitial();
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