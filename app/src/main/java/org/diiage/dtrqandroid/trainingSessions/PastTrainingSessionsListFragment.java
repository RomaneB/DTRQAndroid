package org.diiage.dtrqandroid.trainingSessions;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;
import org.diiage.dtrqandroid.data.db.viewmodel.TrainingSessionViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentPastTrainingSessionsListBinding;
import org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter.RecyclerViewPastTrainingSessionAdapter;
import org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter.RecyclerViewTrainingSessionsAdapter;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastTrainingSessionsListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private TrainingSessionViewModel trainingSessionViewModel;
    private FragmentPastTrainingSessionsListBinding binding;

    UserSessionManager session;
    private Long userId;

    private RecyclerView recyclerView;
    private RecyclerViewPastTrainingSessionAdapter adapter;
    private Button btnLogout;

    public PastTrainingSessionsListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        session = new UserSessionManager(getContext());
        //get id
        userId = session.getUserId();
        System.out.println("UserId : " + userId);

        ((RoomApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if(trainingSessionViewModel != null && trainingSessionViewModel.getPastTrainingSessions(userId).getValue() != null){
            adapter.setTrainingSessions(trainingSessionViewModel.getPastTrainingSessions(userId).getValue());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentPastTrainingSessionsListBinding.inflate(inflater, container, false);
        this.trainingSessionViewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainingSessionViewModel.class);
        recyclerView = binding.recyclerViewPastTraining;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        trainingSessionViewModel.getPastTrainingSessions(userId).observe(this, trainingSessions -> {
            if(trainingSessions != null){
                RecyclerViewPastTrainingSessionAdapter adapter = new RecyclerViewPastTrainingSessionAdapter(this::onClickButton, this, this.getContext(), trainingSessions);
                recyclerView.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }

    private void onClickButton(TrainingSessionWithUser trainingSession)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Details")
                .setMessage
                        (
                                "Date de la session : \n" +
                                        trainingSession.date +
                                        "\n\nScore : \n" +
                                        trainingSession.score
                        )
                .setPositiveButton("Ok", (dialog, which) -> { });
        builder.create().show();
    }
}
