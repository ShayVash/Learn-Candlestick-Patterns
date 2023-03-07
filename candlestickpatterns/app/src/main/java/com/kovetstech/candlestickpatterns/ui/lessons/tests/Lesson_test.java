package com.kovetstech.candlestickpatterns.ui.lessons.tests;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.kovetstech.candlestickpatterns.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lesson_test extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    Context context;

    // Views
    private View v;
    private ImageView QuestionImage;
    private TextView Question;
    private TextView Question_number;
    private Button AnswerOne;
    private Button AnswerTwo;
    private Button AnswerThree;
    private Button AnswerFour;

    // Utils
    private Button ButtonClicked;
    private Drawable QuestionImageDrawable;
    private String QuestionText;
    private String TrueAnswer;
    private String[] OtherAnswers;

    ColorStateList DefultButtonColor;

    CountDownTimer cdt;
    MediaPlayer mp;

    private String test_name;
    private double test_result;
    private int current_question = 0;
    private double question_amount;
    private double correct_answers;

    AdView adView;


    public Lesson_test() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            test_name = getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_lesson_test, container, false);
        context = v.getContext();

        AnswerOne = v.findViewById(R.id.quiz_answer_1);
        AnswerTwo = v.findViewById(R.id.quiz_answer_2);
        AnswerThree = v.findViewById(R.id.quiz_answer_3);
        AnswerFour = v.findViewById(R.id.quiz_answer_4);

        AnswerOne.setOnClickListener(view -> {
            UserAnswered(AnswerOne);
            ButtonClicked = AnswerOne;
        });
        AnswerTwo.setOnClickListener(view -> {
            UserAnswered(AnswerTwo);
            ButtonClicked = AnswerTwo;
        });
        AnswerThree.setOnClickListener(view -> {
            UserAnswered(AnswerThree);
            ButtonClicked = AnswerThree;
        });
        AnswerFour.setOnClickListener(view -> {
            UserAnswered(AnswerFour);
            ButtonClicked = AnswerFour;
        });

        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                DefultButtonColor = ColorStateList.valueOf(getResources().getColor(R.color.buttonColorDark, null));
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                DefultButtonColor = ColorStateList.valueOf(getResources().getColor(R.color.buttonColorLight, null));
                break;
        }

        QuestionImage = v.findViewById(R.id.quiz_image);
        Question = v.findViewById(R.id.quiz_question);
        Question_number = v.findViewById(R.id.question_amount);

        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        test_name = test_name.toLowerCase().replace(" test", "").replace(" ", "_");

        question_amount = GetQuestionAmount();
        GetNextQuestion();
    }

    private int GetQuestionAmount(){
        int result = 0;

        for (int i = 1; i < 15; i ++){
            try {
                String resource = test_name + "_" + i;
                int ID = this.getResources().getIdentifier(resource, "array", (requireContext()).getPackageName());
                String[] Qdata = getResources().getStringArray(ID);
            }catch (Exception e){
                result = i-1;
                break;
            }
        }

        return result;
    }
    public void GetNextQuestion(){
        QuestionImage.setVisibility(View.VISIBLE);
        AnswerThree.setVisibility(View.VISIBLE);
        AnswerFour.setVisibility(View.VISIBLE);


        if(current_question != question_amount){
             current_question++;
        }else{
            test_result = calculate_score();

            SharedPreferences prefs = this.getContext().getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(test_name+"testscore", test_result+"");
            editor.commit();

            Bundle bundle = new Bundle();
            bundle.putString("param1", getArguments().getString(ARG_PARAM1));
            Navigation.findNavController(AnswerOne).navigate(R.id.action_lesson_test_to_lesson_finished_test, bundle);
            return;
        }


        String resource = test_name + "_" + current_question;
        int ID = this.getResources().getIdentifier(resource, "array", (requireContext()).getPackageName());
        String[] Qdata = getResources().getStringArray(ID);

        if(!Qdata[0].equals("NONE")) {
            String DrawableResource = Qdata[0];
            int DrawableID = getResources().getIdentifier(DrawableResource, "drawable", getContext().getPackageName());

            QuestionImageDrawable = ContextCompat.getDrawable(getContext(), DrawableID);
        }else{
            QuestionImageDrawable = null;
        }

        QuestionText = Qdata[1];
        TrueAnswer = Qdata[2];
        if(Qdata.length == 6){
            OtherAnswers = new String[]{Qdata[2], Qdata[3], Qdata[4], Qdata[5]};
        }else if (Qdata.length == 5){
            OtherAnswers = new String[]{Qdata[2], Qdata[3], Qdata[4]};
        }else{
            OtherAnswers = new String[]{Qdata[2], Qdata[3]};
        }


        SetNewQuestion(QuestionImageDrawable, QuestionText, OtherAnswers);
    }
    public void SetNewQuestion(Drawable image, String QuestionText, String[] Answers){
        if(image != null) {
            QuestionImage.setImageDrawable(image);
        }else QuestionImage.setVisibility(View.INVISIBLE);

        Question_number.setText(current_question+"/"+(int) question_amount);

        Question.setText(QuestionText);

        List<String> List = Arrays.asList(Answers);
        Collections.shuffle(List);

        AnswerOne.setText(List.get(0));
        AnswerTwo.setText(List.get(1));

        try {
            AnswerThree.setText(List.get(2));
        }catch (IndexOutOfBoundsException ex){
            AnswerThree.setVisibility(View.INVISIBLE);
        }

        try {
            AnswerFour.setText(List.get(3));
        }catch (IndexOutOfBoundsException ex){
            AnswerFour.setVisibility(View.INVISIBLE);
        }

    }

    public void UserAnswered(View view){
            if(((Button) view).getText().equals(TrueAnswer)){
                Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
                correct_answers++;
                CorrectAnim();
            }else{
                Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
                WrongAnim();
            }
    }

    public void CorrectAnim(){
        mp = MediaPlayer.create(getContext(), R.raw.correct);
        mp.start();
        cdt = new CountDownTimer(1000, 500) {

            public void onTick(long millisUntilFinished) {
                ButtonClicked.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.upGreen, null)));
            }

            public void onFinish() {
                ButtonClicked.setBackgroundTintList(DefultButtonColor);
                GetNextQuestion();
            }
        }.start();
    }
    public void WrongAnim(){
        mp = MediaPlayer.create(getContext(), R.raw.wrong);
        mp.start();
        cdt = new CountDownTimer(1000, 500) {

            public void onTick(long millisUntilFinished) {
                ButtonClicked.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.downRed, null)));
            }

            public void onFinish() {
                ButtonClicked.setBackgroundTintList(DefultButtonColor);
                GetNextQuestion();
            }
        }.start();
    }

    public double calculate_score(){
        double score = (correct_answers/question_amount) * 100;
        test_result = score;
        Log.w("Lesson Test", correct_answers + "/" +question_amount + " * 100 = " + score);

        return score;
    }

    @Override
    public void onPause() {
        if(cdt != null){
            cdt.cancel();
        }
        super.onPause();
    }
    @Override
    public void onDetach() {
        if(cdt != null){
            cdt.cancel();
        }
        super.onDetach();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}