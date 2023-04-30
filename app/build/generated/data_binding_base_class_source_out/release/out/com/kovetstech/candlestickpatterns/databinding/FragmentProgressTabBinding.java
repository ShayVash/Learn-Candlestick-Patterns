// Generated by view binder compiler. Do not edit!
package com.kovetstech.candlestickpatterns.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import org.eazegraph.lib.charts.PieChart;

public final class FragmentProgressTabBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final PieChart profileProgressBar;

  @NonNull
  public final TextView profileProgressBarText;

  @NonNull
  public final ConstraintLayout progressBarLayout;

  @NonNull
  public final ConstraintLayout progressTabLayout;

  @NonNull
  public final TextView progressTabSubTitle;

  @NonNull
  public final TextView progressTabTitle;

  private FragmentProgressTabBinding(@NonNull ConstraintLayout rootView,
      @NonNull PieChart profileProgressBar, @NonNull TextView profileProgressBarText,
      @NonNull ConstraintLayout progressBarLayout, @NonNull ConstraintLayout progressTabLayout,
      @NonNull TextView progressTabSubTitle, @NonNull TextView progressTabTitle) {
    this.rootView = rootView;
    this.profileProgressBar = profileProgressBar;
    this.profileProgressBarText = profileProgressBarText;
    this.progressBarLayout = progressBarLayout;
    this.progressTabLayout = progressTabLayout;
    this.progressTabSubTitle = progressTabSubTitle;
    this.progressTabTitle = progressTabTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProgressTabBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProgressTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_progress_tab, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProgressTabBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.profile_progress_bar;
      PieChart profileProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (profileProgressBar == null) {
        break missingId;
      }

      id = R.id.profile_progress_bar_text;
      TextView profileProgressBarText = ViewBindings.findChildViewById(rootView, id);
      if (profileProgressBarText == null) {
        break missingId;
      }

      id = R.id.progress_bar_layout;
      ConstraintLayout progressBarLayout = ViewBindings.findChildViewById(rootView, id);
      if (progressBarLayout == null) {
        break missingId;
      }

      ConstraintLayout progressTabLayout = (ConstraintLayout) rootView;

      id = R.id.progress_tab_sub_title;
      TextView progressTabSubTitle = ViewBindings.findChildViewById(rootView, id);
      if (progressTabSubTitle == null) {
        break missingId;
      }

      id = R.id.progress_tab_title;
      TextView progressTabTitle = ViewBindings.findChildViewById(rootView, id);
      if (progressTabTitle == null) {
        break missingId;
      }

      return new FragmentProgressTabBinding((ConstraintLayout) rootView, profileProgressBar,
          profileProgressBarText, progressBarLayout, progressTabLayout, progressTabSubTitle,
          progressTabTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}