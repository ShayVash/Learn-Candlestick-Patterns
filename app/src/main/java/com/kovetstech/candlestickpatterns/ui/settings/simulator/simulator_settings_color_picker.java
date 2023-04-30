package com.kovetstech.candlestickpatterns.ui.settings.simulator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.kovetstech.candlestickpatterns.MainActivity;
import com.kovetstech.candlestickpatterns.R;

import static android.content.res.Configuration.UI_MODE_NIGHT_NO;
import static android.content.res.Configuration.UI_MODE_NIGHT_YES;
import static com.kovetstech.candlestickpatterns.MainActivity.context;

public class simulator_settings_color_picker extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    int chosen_color;
    int defult_color;
    private String colorpicker_title = "Bullish Candle Color";

    private TextView title;
    private ImageView color_circle;

    public simulator_settings_color_picker() {
        // Required empty public constructor
    }
    public static simulator_settings_color_picker newInstance(String param1) {
        simulator_settings_color_picker fragment = new simulator_settings_color_picker();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            colorpicker_title = getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_simulator_settings_color_picker, container, false);

        title = v.findViewById(R.id.colorpicker_title);
        color_circle = v.findViewById(R.id.colorpicker_circle);

        setDefultColors();
        set();

        return v;
    }

    public void set(){
        SharedPreferences prefs = getContext().getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
        int chosen_color = prefs.getInt(colorpicker_title+"c", defult_color); //0 is the default value

        title.setText(colorpicker_title);
        color_circle.setImageTintList(ColorStateList.valueOf(chosen_color));

        color_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_colorpicker();
            }
        });
    }
    public void setDefultColors(){
        switch (colorpicker_title){
            case "Bullish Candle Color":
                defult_color = getResources().getColor(R.color.upGreen, null);
                break;
            case "Bearish Candle Color":
                defult_color = getResources().getColor(R.color.downRed, null);
                break;
            case "Chart Border Color":
                defult_color = Color.LTGRAY;
                break;
            case "Shadow Color":
                defult_color = Color.GRAY;
                break;
        }
    }
    public void open_colorpicker(){
        ColorPickerDialogBuilder
                .with(context,R.style.Widget_Custom_AlertDialog )
                .setTitle("Choose color")
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        set_new_color(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }
    public void set_new_color(int color){
        chosen_color = color;

        color_circle.setImageTintList(ColorStateList.valueOf(chosen_color));

        SharedPreferences prefs = getContext().getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
        prefs.edit().putInt(colorpicker_title+"c", chosen_color).commit();
    }
}