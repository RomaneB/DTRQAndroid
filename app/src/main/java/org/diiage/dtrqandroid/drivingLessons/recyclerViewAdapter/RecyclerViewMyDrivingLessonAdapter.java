package org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMyDrivingLessonAdapter extends RecyclerView.Adapter<RecyclerViewMyDrivingLessonAdapter.MyDrivingLessonHolder> {
    private List<DrivingLesson> myDrivingLessonList = new ArrayList<>();
    public RecyclerViewMyDrivingLessonAdapter(){
    }

    @NonNull
    @Override
    public MyDrivingLessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_driving_lesson_list_item,parent, false);
        return new MyDrivingLessonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDrivingLessonHolder holder, int position) {
        DrivingLesson myDrivingLesson = myDrivingLessonList.get(position);
        holder.textViewMyDate.setText(holder.textViewMyDate.getText() + new SimpleDateFormat("dd MMMM yyyy 'à' hh'h'mm").format(myDrivingLesson.getDate())); // simpledate format
        holder.textViewMyText.setText(myDrivingLesson.getText());

    }

    public void setMyDrivingLessons(List<DrivingLesson> myDrivingLessons){
        this.myDrivingLessonList = myDrivingLessons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return myDrivingLessonList.size();
    }

    class MyDrivingLessonHolder extends RecyclerView.ViewHolder{
        private TextView textViewMyDate;
        private TextView textViewMyText;

        public MyDrivingLessonHolder(View itemView){
            super(itemView);
            textViewMyDate = itemView.findViewById(R.id.my_date_lesson);
            textViewMyText = itemView.findViewById(R.id.my_text_driving);
        }
    }
}
