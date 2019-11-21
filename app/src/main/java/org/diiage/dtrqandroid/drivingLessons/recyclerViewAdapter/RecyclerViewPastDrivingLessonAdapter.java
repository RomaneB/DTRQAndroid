package org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentPreviousDrivingLessonsListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewPastDrivingLessonAdapter extends RecyclerView.Adapter<RecyclerViewPastDrivingLessonAdapter.PastDrivingLessonHolder> {

    private List<DrivingLessonWithInstructor> drivingLessonWithInstructorList = new ArrayList<>();
    UserSessionManager session;
    private Fragment fragment;
    private Context context;
    private Consumer onClickButton;

    public RecyclerViewPastDrivingLessonAdapter(Consumer<DrivingLessonWithInstructor> onClickButton, Fragment fragment, Context context, List<DrivingLessonWithInstructor> drivingLessons){
        this.onClickButton = onClickButton;
        this.context = context;
        this.fragment = fragment;
        this.drivingLessonWithInstructorList = drivingLessons;
        this.setPastDrivingLessons(drivingLessons);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPastDrivingLessonAdapter.PastDrivingLessonHolder holder, int position) {
        DrivingLessonWithInstructor currentDrivingLesson = drivingLessonWithInstructorList.get(position);
        holder.bind(currentDrivingLesson);
    }

    @NonNull
    @Override
    public PastDrivingLessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentPreviousDrivingLessonsListItemBinding fragmentMyDrivingLessonItemBinding = FragmentPreviousDrivingLessonsListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PastDrivingLessonHolder(fragmentMyDrivingLessonItemBinding);
    }

    public void setPastDrivingLessons( List<DrivingLessonWithInstructor> myDrivingLessons){
        this.drivingLessonWithInstructorList = myDrivingLessons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return drivingLessonWithInstructorList.size();
    }

    public class PastDrivingLessonHolder extends RecyclerView.ViewHolder{
        private FragmentPreviousDrivingLessonsListItemBinding binding;

        public PastDrivingLessonHolder(FragmentPreviousDrivingLessonsListItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DrivingLessonWithInstructor drivingLesson) {
            binding.setDrivingLesson(drivingLesson);
            binding.setConsumer(onClickButton);
            binding.executePendingBindings();
        }
    }
}
