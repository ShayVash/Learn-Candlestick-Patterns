package com.kovetstech.candlestickpatterns.ui.lessons;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.kovetstech.candlestickpatterns.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LessonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonsFragment extends Fragment{


    Button Quiz;
    View v;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LessonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LessonsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LessonsFragment newInstance(String param1, String param2) {
        LessonsFragment fragment = new LessonsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_lessons, container, false);

        Button how = v.findViewById(R.id.HowToLessonButton);
        how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("HOW");
            }
        });
        Button hammer = v.findViewById(R.id.HammerLessonButton);
        hammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("HAMMER");
            }
        });
        Button evening = v.findViewById(R.id.EveningStarLessonButton);
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("EVENING");
            }
        });
        Button morning = v.findViewById(R.id.MorningStarLessonButton);
        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("MORNING");
            }
        });
        Button crows = v.findViewById(R.id.ThreeBlackCrowslessonButton);
        crows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("CROWS");
            }
        });
        Button soldiers = v.findViewById(R.id.ThreeWhiteSoldiersLessonButton);
        soldiers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("SOLDIERS");
            }
        });
        Button hanging = v.findViewById(R.id.HangingManLessonButton);
        hanging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("HANGING");
            }
        });
        Button shooting = v.findViewById(R.id.ShootingStarLessonButton);
        shooting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("SHOOTING");
            }
        });
        Button bullishEng = v.findViewById(R.id.BullishEngulfingLessonButton);
        bullishEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("BULLISHENG");
            }
        });
        Button bearishEng = v.findViewById(R.id.BearishEngulfingLessonButton);
        bearishEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("BEARISHENG");
            }
        });
        Button bullishthree = v.findViewById(R.id.bullishThreeLineStrikeLessonButton);
        bullishthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("BULLISHTHREELINE");
            }
        });
        Button bearishthree = v.findViewById(R.id.bearishThreeLineStrikeLessonButton);
        bearishthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadLesson("BEARISHTHREELINE");
            }
        });
        return v;
    }
    public void LoadLesson(String lesson){
        Bundle bundle = new Bundle();
        bundle.putString("param1", lesson);
        Navigation.findNavController(v).navigate(R.id.action_navigation_lessons_to_lesson, bundle);
    }

}