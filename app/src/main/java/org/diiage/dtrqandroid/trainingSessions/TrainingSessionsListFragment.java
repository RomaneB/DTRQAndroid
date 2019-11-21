package org.diiage.dtrqandroid.trainingSessions;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.UserTraining;
import org.diiage.dtrqandroid.data.db.viewmodel.TrainingSessionViewModel;
import org.diiage.dtrqandroid.data.db.viewmodel.UserTrainingViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentTrainingSessionsListBinding;
import org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter.RecyclerViewTrainingSessionsAdapter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingSessionsListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private Long userId;
    UserSessionManager session;

    private RecyclerView recyclerView;
    private RecyclerViewTrainingSessionsAdapter adapter;
    private Button btnLogout;

    private TrainingSessionViewModel trainingSessionViewModel;
    private FragmentTrainingSessionsListBinding binding;

    public TrainingSessionsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new UserSessionManager(getContext());
        // get id
        userId = session.getUserId();

        ((RoomApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(trainingSessionViewModel != null && trainingSessionViewModel.getAvailableTrainingSessions(userId).getValue() != null){
            adapter.setTrainingSessions(trainingSessionViewModel.getAvailableTrainingSessions(userId).getValue());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTrainingSessionsListBinding.inflate(inflater, container, false);
        this.trainingSessionViewModel = ViewModelProviders.of(this,viewModelFactory).get(TrainingSessionViewModel.class);
        recyclerView = binding.trainingSessionsListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        this.trainingSessionViewModel.getAvailableTrainingSessions(userId).observe(this, trainingSessions -> {
            if(trainingSessions != null) {
                RecyclerViewTrainingSessionsAdapter adapter = new RecyclerViewTrainingSessionsAdapter(this::onClickButton, this, this.getContext(), trainingSessions);
                recyclerView.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }

    private void onClickButton(TrainingSession trainingSession){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Inscription")
                .setMessage("Voulez-vous vous inscrire ?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    UserTrainingViewModel userTrainingViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(UserTrainingViewModel.class);
                    UserTraining userTraining = new UserTraining(userId, trainingSession.trainingSessionId, 0);
                    userTrainingViewModel.insert(userTraining);

                    trainingSessionViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(TrainingSessionViewModel.class);
                    trainingSessionViewModel.inscriptionTrainingSessions(trainingSession.trainingSessionId);

                    Toast.makeText(getContext(),"Inscription réussie", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Non", ((dialog, which) -> {
                    Toast.makeText(getContext(), "Inscrition annulée", Toast.LENGTH_SHORT).show();
                }));
        builder.create().show();
    }


}
