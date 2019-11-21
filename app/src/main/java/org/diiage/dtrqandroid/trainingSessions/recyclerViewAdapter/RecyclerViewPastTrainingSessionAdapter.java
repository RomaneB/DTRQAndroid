package org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;
import org.diiage.dtrqandroid.data.db.viewmodel.TrainingSessionViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentPastTrainingSessionsListBinding;
import org.diiage.dtrqandroid.databinding.FragmentPastTrainingSessionsListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewPastTrainingSessionAdapter extends RecyclerView.Adapter<RecyclerViewPastTrainingSessionAdapter.TrainingSessionHolder> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<TrainingSessionWithUser> trainingSessionList = new ArrayList<>();
    private TrainingSessionViewModel trainingSessionViewModel;
    UserSessionManager session;
    private Fragment fragment;
    private Context context;
    private Long userId;
    private FragmentPastTrainingSessionsListBinding binding;

    public RecyclerViewPastTrainingSessionAdapter(Fragment fragment, Context context, List<TrainingSessionWithUser> trainingSessions){
        this.fragment = fragment;
        this.context = context;
        this.trainingSessionList = trainingSessions;
        this.setTrainingSessions(trainingSessions);
    }

    @NonNull
    @Override
    public TrainingSessionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        FragmentPastTrainingSessionsListItemBinding fragmentPastTrainingSessionsListItemBinding = FragmentPastTrainingSessionsListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TrainingSessionHolder(fragmentPastTrainingSessionsListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingSessionHolder holder, int position){
        TrainingSessionWithUser currentTrainingSession = trainingSessionList.get(position);
        holder.bind(currentTrainingSession);
    }

    public void setTrainingSessions(List<TrainingSessionWithUser> trainingSessions){
        this.trainingSessionList = trainingSessions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {return trainingSessionList.size();}

    class TrainingSessionHolder extends RecyclerView.ViewHolder {
        public TrainingSessionHolder(@NonNull View itemView) { super(itemView); }

        private FragmentPastTrainingSessionsListItemBinding fragmentPastTrainingSessionsListItemBinding;

        public TrainingSessionHolder(FragmentPastTrainingSessionsListItemBinding fragmentPastTrainingSessionsListItemBinding){
            super(fragmentPastTrainingSessionsListItemBinding.getRoot());
            this.fragmentPastTrainingSessionsListItemBinding = fragmentPastTrainingSessionsListItemBinding;
        }

        public void bind(TrainingSessionWithUser trainingSession){
            fragmentPastTrainingSessionsListItemBinding.setTrainingSession(trainingSession);
            fragmentPastTrainingSessionsListItemBinding.executePendingBindings();
        }
    }
}
