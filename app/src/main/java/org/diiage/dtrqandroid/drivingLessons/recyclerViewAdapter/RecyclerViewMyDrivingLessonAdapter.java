package org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;
import org.diiage.dtrqandroid.databinding.FragmentDrivingLessonItemBinding;
import org.diiage.dtrqandroid.databinding.FragmentMyDrivingLessonItemBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMyDrivingLessonAdapter extends RecyclerView.Adapter<RecyclerViewMyDrivingLessonAdapter.MyDrivingLessonHolder> {
    private List<DrivingLessonWithInstructor> myDrivingLessonList = new ArrayList<>();
    private Consumer onClickButton;
    private Fragment fragment;
    private Context context;

    public RecyclerViewMyDrivingLessonAdapter(Consumer<DrivingLessonWithInstructor> onClickButton, Fragment fragment, Context context, List<DrivingLessonWithInstructor> drivingLessons){
        this.onClickButton = onClickButton;
        this.context = context;
        this.fragment = fragment;
        this.myDrivingLessonList = drivingLessons;
        this.setMyDrivingLessons(drivingLessons);
    }

    @NonNull
    @Override
    public MyDrivingLessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentMyDrivingLessonItemBinding fragmentMyDrivingLessonItemBinding = FragmentMyDrivingLessonItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyDrivingLessonHolder(fragmentMyDrivingLessonItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDrivingLessonHolder holder, int position) {
        DrivingLessonWithInstructor currentDrivingLesson = myDrivingLessonList.get(position);
        holder.bind(currentDrivingLesson);
    }

    public void setMyDrivingLessons( List<DrivingLessonWithInstructor> myDrivingLessons){
        this.myDrivingLessonList = myDrivingLessons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return myDrivingLessonList.size();
    }

    class MyDrivingLessonHolder extends RecyclerView.ViewHolder{
        private FragmentMyDrivingLessonItemBinding fragmentMyDrivingLessonItemBinding;

        public MyDrivingLessonHolder(FragmentMyDrivingLessonItemBinding fragmentMyDrivingLessonItemBinding){
            super(fragmentMyDrivingLessonItemBinding.getRoot());
            this.fragmentMyDrivingLessonItemBinding = fragmentMyDrivingLessonItemBinding;


        }
        public void bind(DrivingLessonWithInstructor drivingLesson){
            fragmentMyDrivingLessonItemBinding.setDrivingLesson(drivingLesson);
            fragmentMyDrivingLessonItemBinding.setConsumer(onClickButton);
            fragmentMyDrivingLessonItemBinding.executePendingBindings();
        }
    }
}
