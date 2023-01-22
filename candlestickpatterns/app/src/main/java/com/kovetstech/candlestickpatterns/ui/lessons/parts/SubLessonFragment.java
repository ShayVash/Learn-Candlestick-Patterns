package com.kovetstech.candlestickpatterns.ui.lessons.parts;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kovetstech.candlestickpatterns.R;
import com.kovetstech.candlestickpatterns.ui.lessons.parts.placeholder.PlaceholderContent;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * A fragment representing a list of Items.
 */
public class SubLessonFragment extends Fragment{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_CATEGORY = "arg-category";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String mCategory = "";
    public double prec = 0.0;
    MySubLessonRecyclerViewAdapter adapter;

    View view;

    public SubLessonFragment() {
    }
    public static SubLessonFragment newInstance(int columnCount, String category) {
        SubLessonFragment fragment = new SubLessonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mCategory = getArguments().getString(ARG_CATEGORY);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sub_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            switch (mCategory){
                case "Basics":
                    adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BASICS_ITEMS);
                    break;
                case "Bullish Candlestick Patterns":
                    adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BULLISH_PATTERNS_ITEMS);
                    break;
                case "Bearish Candlestick Patterns":
                    adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.BEARISH_PATTERNS_ITEMS);
                    break;
                case "Indicators":
                    adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.INDICATORS_ITEMS);
                    break;
                case "Fundamentals":
                    adapter = new MySubLessonRecyclerViewAdapter(PlaceholderContent.FUNDAMENTAL_ITEMS);
                    break;
            }

            recyclerView.setAdapter(adapter);
            CalculatePrec();

            adapter.setListener(() -> CalculatePrec());
        }

        return view;
    }


    public void CalculatePrec(){
        double learned = 0.0;
        double lessons = adapter.mValues.size();

        for(int i = 0; i< adapter.mValues.size(); i++){
            if(adapter.getItemLearned(i, requireContext())){
                learned++;
            }
        }


        prec = (learned/lessons)*100.0;
        Log.w("Sublesson", String.valueOf(prec));

        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.DOWN);


        Log.w("NewSublesson", String.valueOf(prec));
        ((lessonList) requireParentFragment()).SetPrec(df.format(prec)+"%");
    }

}