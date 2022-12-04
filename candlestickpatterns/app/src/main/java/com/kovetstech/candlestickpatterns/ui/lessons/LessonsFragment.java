package com.kovetstech.candlestickpatterns.ui.lessons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kovetstech.candlestickpatterns.R;
import com.kovetstech.candlestickpatterns.ui.lessons.parts.lessonList;

public class LessonsFragment extends Fragment{



    View v;

    public LessonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_lessons, container, false);

        lessonList basics = lessonList.newInstance("Basics");
        getChildFragmentManager().beginTransaction().replace(R.id.basics, basics).commit();

        lessonList bullishPatterns = lessonList.newInstance("Bullish Candlestick Patterns");
        getChildFragmentManager().beginTransaction().replace(R.id.patterns, bullishPatterns).commit();

        lessonList bearishPatterns = lessonList.newInstance("Bearish Candlestick Patterns");
        getChildFragmentManager().beginTransaction().replace(R.id.patterns2, bearishPatterns).commit();
        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
    }
}