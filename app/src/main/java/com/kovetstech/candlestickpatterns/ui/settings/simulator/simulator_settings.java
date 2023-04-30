package com.kovetstech.candlestickpatterns.ui.settings.simulator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kovetstech.candlestickpatterns.R;
import com.kovetstech.candlestickpatterns.ui.settings.sub_settings_button;

public class simulator_settings extends Fragment {

    View v;

    public simulator_settings() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_simulator_settings, container, false);

        simulator_settings_color_picker positive_colorpicker = simulator_settings_color_picker.newInstance("Bullish Candle Color");
        getChildFragmentManager().beginTransaction().replace(R.id.pos_candle_colorpicker, positive_colorpicker).commit();

        simulator_settings_color_picker negative_colorpicker = simulator_settings_color_picker.newInstance("Bearish Candle Color");
        getChildFragmentManager().beginTransaction().replace(R.id.neg_candle_colorpicker, negative_colorpicker).commit();

        simulator_settings_color_picker chart_background_colorpicker = simulator_settings_color_picker.newInstance("Chart Border Color");
        getChildFragmentManager().beginTransaction().replace(R.id.background_colorpicker, chart_background_colorpicker).commit();

        simulator_settings_color_picker shadow_colorpicker = simulator_settings_color_picker.newInstance("Shadow Color");
        getChildFragmentManager().beginTransaction().replace(R.id.shadow_colorpicker, shadow_colorpicker).commit();

        return v;
    }
}