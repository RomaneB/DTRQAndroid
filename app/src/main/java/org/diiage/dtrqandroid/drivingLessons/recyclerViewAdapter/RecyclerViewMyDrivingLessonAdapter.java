package org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;
import org.diiage.dtrqandroid.databinding.FragmentMyDrivingLessonListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        FragmentMyDrivingLessonListItemBinding fragmentMyDrivingLessonItemBinding = FragmentMyDrivingLessonListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        private FragmentMyDrivingLessonListItemBinding fragmentMyDrivingLessonItemBinding;

        public MyDrivingLessonHolder(FragmentMyDrivingLessonListItemBinding fragmentMyDrivingLessonItemBinding){
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
