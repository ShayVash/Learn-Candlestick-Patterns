package com.kovetstech.candlestickpatterns.ui.settings;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kovetstech.candlestickpatterns.R;

public class sub_settings_button extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String subsetting_title = "Simulator";
    private Drawable subsetting_icon;


    View v;
    TextView titleView;
    ImageView iconView;
    ImageView arrowView;
    ColorStateList iconColor;

    private static final int UI_MODE_NIGHT_YES = Configuration.UI_MODE_NIGHT_YES;
    private static final int UI_MODE_NIGHT_NO = Configuration.UI_MODE_NIGHT_NO;

    int action_id;
    public sub_settings_button() {
        // Required empty public constructor
    }
    public static sub_settings_button newInstance(String param1) {
        sub_settings_button fragment = new sub_settings_button();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subsetting_title = getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_sub_settings_button, container, false);;

        titleView = v.findViewById(R.id.titleView);
        iconView = v.findViewById(R.id.iconView);
        arrowView = v.findViewById(R.id.arrowView);

        set(subsetting_title);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubSetting();
            }
        });
        return v;
    }

    private void set(String title){
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        // Set the background and progress bar colors based on the UI mode
        switch (uiMode) {
            case UI_MODE_NIGHT_YES:
                // Dark mode
                iconColor = ColorStateList.valueOf(getResources().getColor(R.color.BackgroundWhite, null));
                break;
            case UI_MODE_NIGHT_NO:
                // Light mode
                iconColor = ColorStateList.valueOf(getResources().getColor(R.color.black, null));
                break;
            default:
                Log.e("subsetting", "Invalid UI mode: " + uiMode);
                break;
        }

        switch (title){
            case "Simulator":
                setSimulator();
                break;
            case "Quiz":
                setQuiz();
                break;
            case "Info":
                setInfo();
                break;
            default:
                Log.w("subsettings", "Sub setting is not set");
                break;
        }
    }
    private void setSimulator(){
        subsetting_icon = getContext().getDrawable(R.drawable.ic_chart);
        subsetting_icon.setTint(iconColor.getDefaultColor());

        titleView.setText(subsetting_title);
        iconView.setImageDrawable(subsetting_icon);
        arrowView.setImageTintList(ColorStateList.valueOf(iconColor.getDefaultColor()));

        action_id = R.id.action_navigation_settings_to_simulator_settings;
    }
    private void setQuiz(){
        subsetting_icon = getContext().getDrawable(R.drawable.ic_nav_quiz);
        subsetting_icon.setTint(iconColor.getDefaultColor());

        titleView.setText(subsetting_title);
        iconView.setImageDrawable(subsetting_icon);
        arrowView.setImageTintList(ColorStateList.valueOf(iconColor.getDefaultColor()));

        action_id = R.id.action_navigation_settings_to_quiz_settings;
    }
    private void setInfo(){
        subsetting_icon = getResources().getDrawable(R.drawable.ic_info);
        subsetting_icon.setTint(iconColor.getDefaultColor());

        titleView.setText(subsetting_title);
        iconView.setImageDrawable(subsetting_icon);
        arrowView.setImageTintList(ColorStateList.valueOf(iconColor.getDefaultColor()));

        action_id = R.id.action_navigation_settings_to_info_settings;
    }
    private void openSubSetting(){
        Navigation.findNavController(v).navigate(action_id, null);
    }
}