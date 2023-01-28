package com.kovetstech.candlestickpatterns.ui.settings.quiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.kovetstech.candlestickpatterns.R;

public class quiz_settings extends Fragment {

    public quiz_settings() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_settings, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
        Switch display_by_order_switch = v.findViewById(R.id.display_by_order_switch);
        display_by_order_switch.setChecked(prefs.getBoolean("quizbyorder", false));

        display_by_order_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                prefs.edit().putBoolean("quizbyorder", b).commit();
            }
        });

        return v;
    }
}