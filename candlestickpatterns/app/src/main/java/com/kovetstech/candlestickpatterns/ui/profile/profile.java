package com.kovetstech.candlestickpatterns.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kovetstech.candlestickpatterns.R;
import com.kovetstech.candlestickpatterns.ui.lessons.parts.MySubLessonRecyclerViewAdapter;
import com.kovetstech.candlestickpatterns.ui.lessons.parts.placeholder.PlaceholderContent;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.kovetstech.candlestickpatterns.R.*;


public class profile extends Fragment {

    private String skill_level = "BEGINNER"; // BEGINNER, PRO, EXPERT.
    private TextView skill_title;

    private int progress_percentage = 35;
    private PieChart progress_bar;
    private TextView progress_bar_text;

    private ColorStateList background_color;
    private ColorStateList progress_background_color;
    private ColorStateList progress_foreground_color;
    private static final int UI_MODE_NIGHT_YES = Configuration.UI_MODE_NIGHT_YES;
    private static final int UI_MODE_NIGHT_NO = Configuration.UI_MODE_NIGHT_NO;

    public profile() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(layout.fragment_profile, container, false);

        setupColors();

        progress_bar = v.findViewById(id.profile_progress_bar);
        progress_bar_text = v.findViewById(id.profile_progress_bar_text);
        progress_percentage = calculateProgress();
        setup_progress();

        skill_title = v.findViewById(id.progress_level);
        setupLevel();

        setupTabs();
        return v;
    }

    // Set Up Colors
    private void setupColors() {
        // Get the current UI mode
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        // Set the background and progress bar colors based on the UI mode
        switch (uiMode) {
            case UI_MODE_NIGHT_YES:
                // Dark mode
                background_color = ColorStateList.valueOf(getResources().getColor(color.profile_background_dark));
                setProgressBarColors(R.color.blue_progress_background_dark, R.color.blue_progress_foreground);
                break;
            case UI_MODE_NIGHT_NO:
                // Light mode
                background_color = ColorStateList.valueOf(getResources().getColor(color.profile_background_light));
                setProgressBarColors(R.color.blue_progress_background_light, R.color.blue_progress_foreground);
                break;
            default:
                Log.e("Profile", "Invalid UI mode: " + uiMode);
                break;
        }
    }
    private void setProgressBarColors(int backgroundColorResId, int foregroundColorResId) {
        if (backgroundColorResId > 0) {
            progress_background_color = ColorStateList.valueOf(getResources().getColor(backgroundColorResId, null));
        } else {
            Log.e("Profile", "Invalid color resource ID: " + backgroundColorResId);
        }

        if (foregroundColorResId > 0) {
            progress_foreground_color = ColorStateList.valueOf(getResources().getColor(foregroundColorResId, null));
        } else {
            Log.e("Profile", "Invalid color resource ID: " + foregroundColorResId);
        }
    }

    private void setupTabs(){
        progress_tab Lessons = progress_tab.newInstance("Lessons", calculateLessonsRead(), calculateLessonsAmount());
        progress_tab Tests = progress_tab.newInstance("Tests", calculateTestsTaken(), calculateTestsAmount());

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(id.lessonsTab, Lessons).commit();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(id.testsTab, Tests).commit();
    }
    private Double calculateLessonsRead(){
        Double lessonsRead = 0.0;

        MySubLessonRecyclerViewAdapter adapter;
        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BASICS_ITEMS);
        for(int i = 0; i< adapter.mValues.size(); i++){
            if(adapter.getItemLearned(i, requireContext())){
                lessonsRead++;
            }
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BULLISH_PATTERNS_ITEMS);
        for(int i = 0; i< adapter.mValues.size(); i++){
            if(adapter.getItemLearned(i, requireContext())){
                lessonsRead++;
            }
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BEARISH_PATTERNS_ITEMS);
        for(int i = 0; i< adapter.mValues.size(); i++){
            if(adapter.getItemLearned(i, requireContext())){
                lessonsRead++;
            }
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.INDICATORS_ITEMS);
        for(int i = 0; i< adapter.mValues.size(); i++){
            if(adapter.getItemLearned(i, requireContext())){
                lessonsRead++;
            }
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.FUNDAMENTAL_ITEMS);
        for(int i = 0; i< adapter.mValues.size(); i++){
            if(adapter.getItemLearned(i, requireContext())){
                lessonsRead++;
            }
        }

        return lessonsRead;
    }
    private Double calculateLessonsAmount(){
        double lessonsAmount = 0.0;

        MySubLessonRecyclerViewAdapter adapter;
        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BASICS_ITEMS);
        lessonsAmount += adapter.mValues.size() -1;

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BULLISH_PATTERNS_ITEMS);
        lessonsAmount += adapter.mValues.size() -1;

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BEARISH_PATTERNS_ITEMS);
        lessonsAmount += adapter.mValues.size() -1;

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.INDICATORS_ITEMS);
        lessonsAmount += adapter.mValues.size() -1;

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.FUNDAMENTAL_ITEMS);
        lessonsAmount += adapter.mValues.size() -1;

        return lessonsAmount;
    }
    private Double calculateTestsTaken(){
        double testsTaken = 0.0;

        MySubLessonRecyclerViewAdapter adapter;

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BASICS_ITEMS);
        if(adapter.getItemLearned(adapter.getItemCount()-1, requireContext())){
            Log.w("profile", "Basics Test Taken");
            testsTaken++;
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BULLISH_PATTERNS_ITEMS);
        if(adapter.getItemLearned(adapter.getItemCount()-1, requireContext())){
            Log.w("profile", "Bullish Test Taken");
            testsTaken++;
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BEARISH_PATTERNS_ITEMS);
        if(adapter.getItemLearned(adapter.getItemCount()-1, requireContext())){
            Log.w("profile", "Bearish Test Taken");
            testsTaken++;
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.INDICATORS_ITEMS);
        if(adapter.getItemLearned(adapter.getItemCount()-1, requireContext())){
            Log.w("profile", "Indicators Test Taken");
            testsTaken++;
        }

        adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.FUNDAMENTAL_ITEMS);
        if(adapter.getItemLearned(adapter.getItemCount()-1, requireContext())){
            Log.w("profile", "Fundamental Test Taken");
            testsTaken++;
        }

        return testsTaken;
    }
    private Double calculateTestsAmount(){
        return 5.0;
    }

    public void setup_progress(){
        progress_bar.clearChart();

        progress_bar.setInnerPaddingColor(background_color.getDefaultColor());
        progress_bar.setInnerValueColor(background_color.getDefaultColor());

        progress_bar.addPieSlice(new PieModel(
                "",
                progress_percentage, progress_foreground_color.getDefaultColor()));
        progress_bar.addPieSlice(new PieModel(
                "",
                100-progress_percentage, progress_background_color.getDefaultColor()));

        progress_bar_text.setText(progress_percentage+"%");
    }
    public int calculateProgress(){
        Double prec = (calculateLessonsRead() + calculateTestsTaken()) / (calculateLessonsAmount() + calculateTestsAmount()) * 100.0;

        DecimalFormat df = new DecimalFormat("###");
        df.setRoundingMode(RoundingMode.DOWN);

        return Integer.parseInt(df.format(prec));
    }

    private void setupLevel(){
        int prec = calculateProgress();

        if(prec > 75){
            skill_level = "EXPERT";
            skill_title.setText(skill_level);
            return;
        }
        if(prec > 50){
            skill_level = "PRO";
            skill_title.setText(skill_level);
            return;
        }

        skill_title.setText(skill_level);
    }
}