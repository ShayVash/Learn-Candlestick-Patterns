package com.kovetstech.candlestickpatterns.ui.lessons.tests;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kovetstech.candlestickpatterns.R;

import java.util.Random;

public class Lesson_finished_test extends Fragment {

    TextView Score_Text;
    Button Try_Again;
    Button Return;

    private static final String ARG_PARAM1 = "param1";
    private String test_name;
    private int test_result;

    private InterstitialAd mInterstitialAd;

    public Lesson_finished_test() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            test_name = getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lesson_finished_test, container, false);

        // !-- ADS --!
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(requireContext(),"ca-app-pub-1929848249759273/1613723827", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        displayInterstitial();
                        Log.i("SIMULATOR", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("SIMULATOR", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        test_name = test_name.toLowerCase().replace(" test", "").replace(" ", "_");

        Score_Text = v.findViewById(R.id.test_score);
        Score_Text.setText(getScore()+"%");

        Try_Again = v.findViewById(R.id.try_again_button);
        Try_Again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start_Test(view);
            }
        });

        Return = v.findViewById(R.id.return_button);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Return(view);
            }
        });

        Try_Again.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.upGreen, null)));
        Return.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.downRed, null)));


        return v;
    }

    public int getScore(){
        double result = 0;

        SharedPreferences prefs = getContext().getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
        result = Double.parseDouble(prefs.getString(test_name+"testscore", "0.0")); //0 is the default value


        test_result = (int) result;

        return test_result;
    }

    public void Start_Test(View view){
        Bundle bundle = new Bundle();
        bundle.putString("param1", getArguments().getString(ARG_PARAM1));
        Navigation.findNavController(Score_Text).navigate(R.id.action_lesson_finished_test_to_lesson_test, bundle);
    }
    public void Return(View view){
        Bundle bundle = new Bundle();
        bundle.putString("param1", getArguments().getString(ARG_PARAM1));
        Navigation.findNavController(Score_Text).navigate(R.id.action_lesson_finished_test_to_navigation_lessons, bundle);
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