package org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentDrivingLessonListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

public class RecyclerViewNextDrivingLessonAdapter extends RecyclerView.Adapter<RecyclerViewNextDrivingLessonAdapter.DrivingLessonHolder> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<DrivingLessonWithInstructor> drivingLessonList = new ArrayList<>();
    private DrivingLessonViewModel drivingLessonViewModel;
    UserSessionManager session;
    private Fragment fragment;
    private Context context;
    private Long userId;
    private Consumer onClickButton;
    private FragmentDrivingLessonListItemBinding binding;

    public RecyclerViewNextDrivingLessonAdapter(Consumer<DrivingLessonWithInstructor> onClickButton, Fragment fragment, Context context, List<DrivingLessonWithInstructor> drivingLessons){
        this.onClickButton = onClickButton;
        this.fragment = fragment;
        this.context = context;
        this.drivingLessonList = drivingLessons;
        this.setDrivingLessons(drivingLessons);


    }

    @NonNull
    @Override
    public DrivingLessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentDrivingLessonListItemBinding fragmentDrivingLessonItemBinding = FragmentDrivingLessonListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DrivingLessonHolder(fragmentDrivingLessonItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DrivingLessonHolder holder, int position) {
        DrivingLessonWithInstructor currentDrivingLesson = drivingLessonList.get(position);
        holder.bind(currentDrivingLesson);
    }

    public void setDrivingLessons(List<DrivingLessonWithInstructor> drivingLessons){
        this.drivingLessonList = drivingLessons;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return drivingLessonList.size();
    }

    class DrivingLessonHolder extends RecyclerView.ViewHolder{
        public DrivingLessonHolder(@NonNull View itemView) {
            super(itemView);
        }

        private FragmentDrivingLessonListItemBinding fragmentDrivingLessonItemBinding;

        public DrivingLessonHolder(FragmentDrivingLessonListItemBinding fragmentDrivingLessonItemBinding){
            super(fragmentDrivingLessonItemBinding.getRoot());
            this.fragmentDrivingLessonItemBinding = fragmentDrivingLessonItemBinding;
        }

        public void bind(DrivingLessonWithInstructor drivingLesson){
            fragmentDrivingLessonItemBinding.setDrivingLesson(drivingLesson);
            fragmentDrivingLessonItemBinding.setConsumer(onClickButton);
            fragmentDrivingLessonItemBinding.executePendingBindings();
        }
    }
}