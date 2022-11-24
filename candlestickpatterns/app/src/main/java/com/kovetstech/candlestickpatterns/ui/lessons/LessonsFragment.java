package com.kovetstech.candlestickpatterns.ui.lessons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kovetstech.candlestickpatterns.R;

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

        Button how = v.findViewById(R.id.HowToLessonButton);
        how.setOnClickListener(view -> LoadLesson("HOW"));
        Button hammer = v.findViewById(R.id.HammerLessonButton);
        hammer.setOnClickListener(view -> LoadLesson("HAMMER"));
        Button evening = v.findViewById(R.id.EveningStarLessonButton);
        evening.setOnClickListener(view -> LoadLesson("EVENING"));
        Button morning = v.findViewById(R.id.MorningStarLessonButton);
        morning.setOnClickListener(view -> LoadLesson("MORNING"));
        Button crows = v.findViewById(R.id.ThreeBlackCrowslessonButton);
        crows.setOnClickListener(view -> LoadLesson("CROWS"));
        Button soldiers = v.findViewById(R.id.ThreeWhiteSoldiersLessonButton);
        soldiers.setOnClickListener(view -> LoadLesson("SOLDIERS"));
        Button hanging = v.findViewById(R.id.HangingManLessonButton);
        hanging.setOnClickListener(view -> LoadLesson("HANGING"));
        Button shooting = v.findViewById(R.id.ShootingStarLessonButton);
        shooting.setOnClickListener(view -> LoadLesson("SHOOTING"));
        Button bullishEng = v.findViewById(R.id.BullishEngulfingLessonButton);
        bullishEng.setOnClickListener(view -> LoadLesson("BULLISHENG"));
        Button bearishEng = v.findViewById(R.id.BearishEngulfingLessonButton);
        bearishEng.setOnClickListener(view -> LoadLesson("BEARISHENG"));
        Button bullishthree = v.findViewById(R.id.bullishThreeLineStrikeLessonButton);
        bullishthree.setOnClickListener(view -> LoadLesson("BULLISHTHREELINE"));
        Button bearishthree = v.findViewById(R.id.bearishThreeLineStrikeLessonButton);
        bearishthree.setOnClickListener(view -> LoadLesson("BEARISHTHREELINE"));
        Button threeinsidedown = v.findViewById(R.id.threeInsideDownLessonButton);
        threeinsidedown.setOnClickListener(view -> LoadLesson("THREEINSIDEDOWN"));
        Button threeinsideup = v.findViewById(R.id.threeInsideUpLessonButton);
        threeinsideup.setOnClickListener(view -> LoadLesson("THREEINSIDEUP"));
        return v;
    }
    public void LoadLesson(String lesson){
        Bundle bundle = new Bundle();
        bundle.putString("param1", lesson);
        Navigation.findNavController(v).navigate(R.id.action_navigation_lessons_to_lesson, bundle);
    }

}