package org.diiage.dtrqandroid.data.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DrivingLessonAdapter extends RecyclerView.Adapter<DrivingLessonAdapter.DrivingLessonHolder> {
    private List<DrivingLesson> drivingLessonList = new ArrayList<>();

    public DrivingLessonAdapter(){
    }

    @NonNull
    @Override
    public DrivingLessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.driving_lesson_item ,parent, false);
        return new DrivingLessonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrivingLessonHolder holder, int position) {
        DrivingLesson currentDrivingLesson = drivingLessonList.get(position);
        holder.textViewDate.setText(holder.textViewDate.getText() + new SimpleDateFormat("dd MMMM yyyy 'à' hh'h'mm").format(currentDrivingLesson.getDate())); // simpledate format
        holder.textViewText.setText(currentDrivingLesson.getText());
    }

    public void setDrivingLessons(List<DrivingLesson> drivingLessons){
        this.drivingLessonList = drivingLessons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return drivingLessonList.size();
    }

    class DrivingLessonHolder extends RecyclerView.ViewHolder{
        private TextView textViewDate;
        private TextView textViewText;

        public DrivingLessonHolder(View itemView){
            super(itemView);
            textViewDate = itemView.findViewById(R.id.date_lesson);
            textViewText = itemView.findViewById(R.id.text_driving);
        }
    }
}
