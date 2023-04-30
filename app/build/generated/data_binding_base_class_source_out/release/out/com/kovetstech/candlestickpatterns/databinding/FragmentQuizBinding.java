// Generated by view binder compiler. Do not edit!
package com.kovetstech.candlestickpatterns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.kovetstech.candlestickpatterns.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentQuizBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout frameLayout3;

  @NonNull
  public final View horSep2;

  @NonNull
  public final Button quizAnswer1;

  @NonNull
  public final Button quizAnswer2;

  @NonNull
  public final Button quizAnswer3;

  @NonNull
  public final Button quizAnswer4;

  @NonNull
  public final ImageView quizImage;

  @NonNull
  public final TextView quizQuestion;

  private FragmentQuizBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout frameLayout3, @NonNull View horSep2, @NonNull Button quizAnswer1,
      @NonNull Button quizAnswer2, @NonNull Button quizAnswer3, @NonNull Button quizAnswer4,
      @NonNull ImageView quizImage, @NonNull TextView quizQuestion) {
    this.rootView = rootView;
    this.frameLayout3 = frameLayout3;
    this.horSep2 = horSep2;
    this.quizAnswer1 = quizAnswer1;
    this.quizAnswer2 = quizAnswer2;
    this.quizAnswer3 = quizAnswer3;
    this.quizAnswer4 = quizAnswer4;
    this.quizImage = quizImage;
    this.quizQuestion = quizQuestion;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentQuizBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentQuizBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_quiz, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentQuizBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout frameLayout3 = (ConstraintLayout) rootView;

      id = R.id.hor_sep2;
      View horSep2 = ViewBindings.findChildViewById(rootView, id);
      if (horSep2 == null) {
        break missingId;
      }

      id = R.id.quiz_answer_1;
      Button quizAnswer1 = ViewBindings.findChildViewById(rootView, id);
      if (quizAnswer1 == null) {
        break missingId;
      }

      id = R.id.quiz_answer_2;
      Button quizAnswer2 = ViewBindings.findChildViewById(rootView, id);
      if (quizAnswer2 == null) {
        break missingId;
      }

      id = R.id.quiz_answer_3;
      Button quizAnswer3 = ViewBindings.findChildViewById(rootView, id);
      if (quizAnswer3 == null) {
        break missingId;
      }

      id = R.id.quiz_answer_4;
      Button quizAnswer4 = ViewBindings.findChildViewById(rootView, id);
      if (quizAnswer4 == null) {
        break missingId;
      }

      id = R.id.quiz_image;
      ImageView quizImage = ViewBindings.findChildViewById(rootView, id);
      if (quizImage == null) {
        break missingId;
      }

      id = R.id.quiz_question;
      TextView quizQuestion = ViewBindings.findChildViewById(rootView, id);
      if (quizQuestion == null) {
        break missingId;
      }

      return new FragmentQuizBinding((ConstraintLayout) rootView, frameLayout3, horSep2,
          quizAnswer1, quizAnswer2, quizAnswer3, quizAnswer4, quizImage, quizQuestion);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
