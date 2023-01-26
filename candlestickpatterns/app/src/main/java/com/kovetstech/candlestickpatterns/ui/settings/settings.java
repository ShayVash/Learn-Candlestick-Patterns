package com.kovetstech.candlestickpatterns.ui.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kovetstech.candlestickpatterns.R;

public class settings extends Fragment {

    sub_settings_button simulator_sub;
    sub_settings_button quiz_sub;
    sub_settings_button info_sub;

    public settings() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        simulator_sub = sub_settings_button.newInstance("Simulator");
        getChildFragmentManager().beginTransaction().replace(R.id.simulator_sub, simulator_sub).commit();

        quiz_sub = sub_settings_button.newInstance("Quiz");
        getChildFragmentManager().beginTransaction().replace(R.id.quiz_sub, quiz_sub).commit();

        info_sub = sub_settings_button.newInstance("Info");
        getChildFragmentManager().beginTransaction().replace(R.id.info_sub, info_sub).commit();

        return v;
    }
}