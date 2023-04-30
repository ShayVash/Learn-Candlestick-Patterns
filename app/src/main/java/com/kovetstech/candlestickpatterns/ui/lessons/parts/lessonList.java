package com.kovetstech.candlestickpatterns.ui.lessons.parts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kovetstech.candlestickpatterns.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lessonList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lessonList extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    static TextView category;
    TextView prec;
    SubLessonFragment slf;

    View v;

    public lessonList() {
        // Required empty public constructor
    }
    public static lessonList newInstance(String param1) {
        lessonList fragment = new lessonList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_lesson_list, container, false);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        category = v.findViewById(R.id.category_text);
        prec = v.findViewById(R.id.category_prec);
        SetCategory(mParam1);
        return v;
    }



    public void SetCategory(String s){
        category.setText(s);
        slf = SubLessonFragment.newInstance(1, s);
        getChildFragmentManager().beginTransaction().add(R.id.sublesson_list, slf).commit();

    }
    public void SetPrec(String s){
        prec.setText(s);
    }


}