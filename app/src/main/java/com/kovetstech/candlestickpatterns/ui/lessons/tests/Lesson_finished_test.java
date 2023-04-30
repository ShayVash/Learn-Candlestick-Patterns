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

import com.kovetstech.candlestickpatterns.R;

import java.util.Random;

public class Lesson_finished_test extends Fragment {

    TextView Score_Text;
    Button Try_Again;
    Button Return;

    private static final String ARG_PARAM1 = "param1";
    private String test_name;
    private int test_result;

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

}