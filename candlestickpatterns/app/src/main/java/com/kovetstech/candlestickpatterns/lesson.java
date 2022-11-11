package com.kovetstech.candlestickpatterns;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lesson#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lesson extends Fragment {

    private static final String ARG_PAGE = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

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
        return v;
    }


}