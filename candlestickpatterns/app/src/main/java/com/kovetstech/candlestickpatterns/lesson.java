package com.kovetstech.candlestickpatterns;

import android.content.Context;
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

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lesson#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lesson extends Fragment {

    private static final String ARG_PAGE = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private InterstitialAd mInterstitialAd;

    public lesson() {
        // Required empty public constructor
    }
    public static lesson newInstance(String param1, String param2) {
        lesson fragment = new lesson();
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PAGE);
        }
    }
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

        InterstitialAd.load(getContext(),"ca-app-pub-1929848249759273/1613723827", adRequest,
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


        if(mParam1.equals("HOW")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/intro-to-candle-sticks");
        }
        if(mParam1.equals("HAMMER")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-hammer-pattern");
        }
        if(mParam1.equals("MORNING")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-morning-star-pattern");
        }
        if(mParam1.equals("EVENING")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-eveningstar-pattern");
        }
        if(mParam1.equals("SOLDIERS")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-three-white-soldiers-pattern");
        }
        if(mParam1.equals("CROWS")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-three-black-crows-pattern");
        }
        if(mParam1.equals("HANGING")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-hanging-man-pattern");
        }
        if(mParam1.equals("SHOOTING")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/the-shooting-star-pattern");
        }
        if(mParam1.equals("BULLISHENG")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bullish-engulfing");
        }
        if(mParam1.equals("BEARISHENG")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bearish-engulfing");
        }
        if(mParam1.equals("BULLISHTHREELINE")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bullish-three-line-strike");
        }
        if(mParam1.equals("BEARISHTHREELINE")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/bearish-three-line-strike");
        }
        if(mParam1.equals("THREEINSIDEDOWN")) {
            wv.loadUrl("https://sites.google.com/view/candle-pattern-lessons/three-inside-down");
        }
        if(mParam1.equals("THREEINSIDEUP")) {
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
                mInterstitialAd.show(getActivity());
                Log.w("MainActivity", "Banner adapter class name: " + mInterstitialAd.getResponseInfo().getMediationAdapterClassName());
                mInterstitialAd = null;
            } else {
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(getContext(), "ca-app-pub-1929848249759273/1613723827", adRequest,
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