package com.kovetstech.candlestickpatterns.ui.quiz;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kovetstech.candlestickpatterns.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Quiz extends Fragment {

    final int NumberOfQuestions = 11;

    ImageView QuestionImage;
    TextView Question;
    Button AnswerOne;
    Button AnswerTwo;
    Button AnswerThree;
    Button AnswerFour;

    Button ButtonClicked;

    Drawable QuestionImageDrawable;
    String QuestionText;
    String TrueAnswer;
    String[] OtherAnswers;

    Random rnd;

    ColorStateList DefultButtonColor;

    CountDownTimer cdt;
    MediaPlayer mp;

    public Quiz() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);

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

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetRandomQuestion();
    }

    public void UserAnswered(View view){
        if(((Button) view).getText().equals(TrueAnswer)){
            Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
            CorrectAnim();
        }else{
            Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
            WrongAnim();
        }
    }

    public void GetRandomQuestion(){
        QuestionImage.setVisibility(View.VISIBLE);
        AnswerThree.setVisibility(View.VISIBLE);
        AnswerFour.setVisibility(View.VISIBLE);

        rnd = new Random();
        int number = rnd.nextInt(NumberOfQuestions) + 1;

        String resource = "Q" + number;
        int ID = this.getResources().getIdentifier(resource, "array", (Objects.requireNonNull(getContext())).getPackageName());
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

    public void CorrectAnim(){
        mp = MediaPlayer.create(getContext(), R.raw.correct);
        mp.start();
        cdt = new CountDownTimer(1000, 500) {

            public void onTick(long millisUntilFinished) {
                ButtonClicked.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.upGreen, null)));
            }

            public void onFinish() {
                ButtonClicked.setBackgroundTintList(DefultButtonColor);
                GetRandomQuestion();
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
                GetRandomQuestion();
            }
        }.start();
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
        GetRandomQuestion();
    }
}