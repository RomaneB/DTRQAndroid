package org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.db.viewmodel.TrainingSessionViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.TrainingSessionsListItemBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

public class RecyclerViewTrainingSessionsAdapter extends RecyclerView.Adapter<RecyclerViewTrainingSessionsAdapter.TrainingSessionHolder> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<TrainingSession> trainingSessionsList = new ArrayList<>();
    private TrainingSessionViewModel trainingSessionViewModel;
    UserSessionManager session;
    private Fragment fragment;
    private Context context;
    private Long userId;

    public RecyclerViewTrainingSessionsAdapter(Consumer<TrainingSession> onClockButton, Fragment fragment, Context context, List<TrainingSession> data){
        fragment = fragment;
        context = context;
        session = new UserSessionManager(context);
        userId = session.getUserId();
        this.setTrainingSessions(data);
    }

    @NonNull
    @Override
    public TrainingSessionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TrainingSessionsListItemBinding itemBinding = TrainingSessionsListItemBinding.inflate(layoutInflater, parent, false);

        return new TrainingSessionHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingSessionHolder holder, int position) {
        TrainingSession currentTrainingSession = trainingSessionsList.get(position);
        holder.bind(currentTrainingSession);
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions){
        this.trainingSessionsList = trainingSessions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return trainingSessionsList.size();
    }

    class TrainingSessionHolder extends RecyclerView.ViewHolder{

        private TrainingSessionsListItemBinding binding;

        /*private TextView textViewDate;
        private TextView textViewAvailableSeats;
        private ImageView imageViewStateIcon;
        private Button btnInscrire;*/

        public TrainingSessionHolder(TrainingSessionsListItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            /*textViewDate = itemView.findViewById(R.id.date_training_session);
            textViewAvailableSeats = itemView.findViewById(R.id.available_seats);
            imageViewStateIcon = itemView.findViewById(R.id.seat_state_icon);
            btnInscrire = itemView.findViewById(R.id.btnInscrire);*/
        }

        public void bind(TrainingSession trainingSession) {
            binding.setTrainingSession(trainingSession);
            binding.executePendingBindings();
        }
    }
}