package com.kovetstech.candlestickpatterns.ui.lessons.parts;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kovetstech.candlestickpatterns.R;
import com.kovetstech.candlestickpatterns.databinding.FragmentSubBinding;
import com.kovetstech.candlestickpatterns.ui.lessons.parts.placeholder.PlaceholderContent.PlaceholderItem;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySubLessonRecyclerViewAdapter extends RecyclerView.Adapter<MySubLessonRecyclerViewAdapter.ViewHolder> {
    AdapterListener listener;

    public final List<PlaceholderItem> mValues;
    public MySubLessonRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
        listener = null;
    }
    public void setListener(AdapterListener listener){
        this.listener = listener;
    }


    public ViewHolder v;
    public Context context;
    SharedPreferences prefs;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = new ViewHolder(FragmentSubBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

        context = v.itemView.getContext();


        return v;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        context = holder.itemView.getContext();

        prefs = context.getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
        boolean is_learned = prefs.getBoolean(mValues.get(position).content, false); //0 is the default value

        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mCheckmark.setVisibility(View.INVISIBLE);

        if(is_learned){
            holder.mCheckmark.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(view -> {
            SharedPreferences prefs = context.getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(mValues.get(position).content, true);
            editor.commit();

            holder.mCheckmark.setVisibility(View.VISIBLE);
            Log.w("Recycler", "Content View " + holder.mContentView.getText().toString());
            if(holder.mContentView.getText().toString().contains("Test")){
                Bundle bundle = new Bundle();
                bundle.putString("param1", holder.mContentView.getText().toString());
                if (is_learned) {
                    try {
                        Navigation.findNavController(v.mContentView).navigate(R.id.action_navigation_lessons_to_lesson_finished_test, bundle);
                    }catch (Exception e){
                        Log.w("Recycler", e.getMessage());
                    }
                }else{
                    try {
                        Navigation.findNavController(v.mContentView).navigate(R.id.action_navigation_lessons_to_lesson_test, bundle);
                    }catch (Exception e){
                        Log.w("Recycler", e.getMessage());
                    }
                }
            }else{
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("param1", holder.mContentView.getText().toString());
                    Navigation.findNavController(v.mContentView).navigate(R.id.action_navigation_lessons_to_lesson, bundle);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        holder.mCheckmark.setOnClickListener(view -> {
            if(holder.mCheckmark.getVisibility() == View.VISIBLE){
                holder.mCheckmark.setVisibility(View.INVISIBLE);
                SharedPreferences prefs = context.getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(mValues.get(position).content, false);
                editor.commit();



            }
            if (listener != null)
                listener.refresh(); // <---- fire listener here
        });
    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public boolean getItemLearned(int id, Context c){
        prefs = c.getSharedPreferences("CSPPREFS", Context.MODE_PRIVATE);
        return prefs.getBoolean(mValues.get(id).content, false); //0 is the default value
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;
        public final ImageView mCheckmark;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentSubBinding binding) {
            super(binding.getRoot());
            mContentView = binding.content;
            mCheckmark = binding.checkmark;
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface AdapterListener {
        void refresh();
    }
}