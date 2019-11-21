package org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.databinding.FragmentMyTrainingSessionsListBinding;
import org.diiage.dtrqandroid.databinding.FragmentMyTrainingSessionsListItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMyTrainingSessionsAdapter extends RecyclerView.Adapter<RecyclerViewMyTrainingSessionsAdapter.MyTrainingSessionsViewHolder> {

    private List<TrainingSession> myTrainingSessionsList = new ArrayList<>();
    private Consumer onClickButton;
    private Fragment fragment;
    private Context context;

    public RecyclerViewMyTrainingSessionsAdapter(Consumer<TrainingSession> onClickButton,Context context, Fragment fragment, List<TrainingSession> trainingsSessionsList){
        this.context = context;
        this.fragment = fragment;
        this.myTrainingSessionsList = trainingsSessionsList;
        this.onClickButton = onClickButton;
        this.setTrainingSessions(trainingsSessionsList);
    }

    public void setTrainingSessions(List<TrainingSession> myTrainingSessionsList) {
        this.myTrainingSessionsList = myTrainingSessionsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyTrainingSessionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentMyTrainingSessionsListItemBinding fragmentMyTrainingSessionsListItemBinding = FragmentMyTrainingSessionsListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyTrainingSessionsViewHolder(fragmentMyTrainingSessionsListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTrainingSessionsViewHolder holder, int position) {
        TrainingSession currentTrainingSession = myTrainingSessionsList.get(position);
        holder.bind(currentTrainingSession);
    }

    @Override
    public int getItemCount() {
        return myTrainingSessionsList.size();
    }

    public class MyTrainingSessionsViewHolder extends RecyclerView.ViewHolder {

        FragmentMyTrainingSessionsListItemBinding fragmentMyTrainingSessionsListItemBinding;

        public MyTrainingSessionsViewHolder(FragmentMyTrainingSessionsListItemBinding fragmentMyTrainingSessionsListItemBinding) {
            super(fragmentMyTrainingSessionsListItemBinding.getRoot());
            this.fragmentMyTrainingSessionsListItemBinding = fragmentMyTrainingSessionsListItemBinding;
        }

        public void bind(TrainingSession trainingSession){
            fragmentMyTrainingSessionsListItemBinding.setTrainingSession(trainingSession);
            fragmentMyTrainingSessionsListItemBinding.setConsumer(onClickButton);
            fragmentMyTrainingSessionsListItemBinding.executePendingBindings();
        }
    }
}
