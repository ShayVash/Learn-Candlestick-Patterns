package com.kovetstech.candlestickpatterns.ui.profile;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kovetstech.candlestickpatterns.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class progress_tab extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private String progress_tab_theme = "Lessons";
    private int progress_tab_amount = 56;
    private double progress_tests_done = 0;
    private double progress_tests_amount = 5;

    private ColorStateList background_color;
    private ColorStateList progress_background_color;
    private ColorStateList progress_foreground_color;
    private static final int UI_MODE_NIGHT_YES = Configuration.UI_MODE_NIGHT_YES;
    private static final int UI_MODE_NIGHT_NO = Configuration.UI_MODE_NIGHT_NO;

    private PieChart progress_bar;
    private TextView progress_bar_text;

    private TextView progress_tab_title;
    private TextView progress_tab_sub_title;

    public progress_tab() {
        // Required empty public constructor
    }
    public static progress_tab newInstance(String param1, Double param2, Double param3) {
        progress_tab fragment = new progress_tab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putDouble(ARG_PARAM2, param2);
        args.putDouble(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            progress_tab_theme = getArguments().getString(ARG_PARAM1);
            progress_tests_done = getArguments().getDouble(ARG_PARAM2);
            progress_tests_amount = getArguments().getDouble(ARG_PARAM3);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_progress_tab, container, false);
        setupColors();

        v.setBackgroundTintList(background_color);
        progress_tab_amount = calculateTabAmount();

        progress_bar = v.findViewById(R.id.profile_progress_bar);
        progress_bar_text = v.findViewById(R.id.profile_progress_bar_text);
        setup_progress();

        progress_tab_title = v.findViewById(R.id.progress_tab_title);
        progress_tab_sub_title = v.findViewById(R.id.progress_tab_sub_title);
        setupTitles();

        return v;
    }

    private void setupColors() {
        // Get the current UI mode
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (progress_tab_theme.equals("Lessons")) {
            setColorsForLessonsTab(uiMode);
        } else if (progress_tab_theme.equals("Tests")) {
            setColorsForTestsTab(uiMode);
        } else {
            Log.e("progress tab", "Invalid progress tab theme: " + progress_tab_theme);
        }
    }
    private void setColorsForLessonsTab(int uiMode) {
        switch (uiMode) {
            case UI_MODE_NIGHT_YES:
                // Dark mode
                setBackgroundColor(R.color.progress_tab_background_dark);
                setProgressBarColors(R.color.green_progress_background_dark, R.color.green_progress_foreground);
                break;
            case UI_MODE_NIGHT_NO:
                // Light mode
                setBackgroundColor(R.color.progress_tab_background_light);
                setProgressBarColors(R.color.green_progress_background_light, R.color.green_progress_foreground);
                break;
            default:
                Log.e("progress tab", "Invalid UI mode: " + uiMode);
                break;
        }
    }
    private void setColorsForTestsTab(int uiMode) {
        switch (uiMode) {
            case UI_MODE_NIGHT_YES:
                // Dark mode
                setBackgroundColor(R.color.progress_tab_background_dark);
                setProgressBarColors(R.color.red_progress_background_dark, R.color.red_progress_foreground);
                break;
            case UI_MODE_NIGHT_NO:
                // Light mode
                setBackgroundColor(R.color.progress_tab_background_light);
                setProgressBarColors(R.color.red_progress_background_light, R.color.red_progress_foreground);
                break;
            default:
                Log.e("progress tab", "Invalid UI mode: " + uiMode);
                break;
        }
    }
    private void setBackgroundColor(int colorResId) {
        if (colorResId > 0) {
            background_color = ColorStateList.valueOf(getResources().getColor(colorResId, null));
        } else {
            Log.e("progress tab", "Invalid color resource ID: " + colorResId);
        }
    }
    private void setProgressBarColors(int backgroundColorResId, int foregroundColorResId) {
        if (backgroundColorResId > 0) {
            progress_background_color = ColorStateList.valueOf(getResources().getColor(backgroundColorResId, null));
        } else {
            Log.e("progress tab", "Invalid color resource ID: " + backgroundColorResId);
        }

        if (foregroundColorResId > 0) {
            progress_foreground_color = ColorStateList.valueOf(getResources().getColor(foregroundColorResId, null));
        } else {
            Log.e("progress tab", "Invalid color resource ID: " + foregroundColorResId);
        }

    }

    private int calculateTabAmount(){
        Double prec = (progress_tests_done/progress_tests_amount)*100.0;
        Log.w("Sublesson", String.valueOf(prec));

        DecimalFormat df = new DecimalFormat("###");
        df.setRoundingMode(RoundingMode.DOWN);

        return Integer.valueOf(df.format(prec));
    }

    @SuppressLint("SetTextI18n")
    public void setup_progress(){
        progress_bar.clearChart();

        progress_bar.setInnerPaddingColor(background_color.getDefaultColor());
        progress_bar.setInnerValueColor(background_color.getDefaultColor());

        progress_bar.addPieSlice(new PieModel(
                "",
                progress_tab_amount, progress_foreground_color.getDefaultColor()));
        progress_bar.addPieSlice(new PieModel(
                "",
                100-progress_tab_amount, progress_background_color.getDefaultColor()));

        progress_bar_text.setText(progress_tab_amount+"%");
    }

    private void setupTitles(){
        progress_tab_title.setText(progress_tab_theme);

        if (progress_tab_theme.equals("Lessons")) {
            setupSubTitlesForLessons();
        } else if (progress_tab_theme.equals("Tests")) {
            setupSubTitlesForTests();
        } else {
            Log.e("progress tab", "Invalid progress tab theme: " + progress_tab_theme);
        }
    }
    @SuppressLint("SetTextI18n")
    private void setupSubTitlesForLessons(){
        progress_tab_sub_title.setText((int)progress_tests_done + " of " + (int)progress_tests_amount + " lessons read");
    }
    @SuppressLint("SetTextI18n")
    private void setupSubTitlesForTests(){
        progress_tab_sub_title.setText((int)progress_tests_done + " of " + (int)progress_tests_amount + " tests completed");
    }
}