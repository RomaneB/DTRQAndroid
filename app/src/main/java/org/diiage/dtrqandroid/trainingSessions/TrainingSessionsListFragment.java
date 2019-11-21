package org.diiage.dtrqandroid.trainingSessions;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;
import org.diiage.dtrqandroid.data.db.entity.User;
import org.diiage.dtrqandroid.data.db.viewmodel.TrainingSessionViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentTrainingSessionsListBinding;
import org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter.RecyclerViewTrainingSessionsAdapter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingSessionsListFragment extends Fragment {

    private Long userId;
    UserSessionManager session;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private TrainingSessionViewModel viewModel;
    private FragmentTrainingSessionsListBinding binding;

    public TrainingSessionsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RoomApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

        session = new UserSessionManager(getContext());
        // get id
        userId = session.getUserId();
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.binding = FragmentTrainingSessionsListBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainingSessionViewModel.class);
        this.setupRecyclerView();
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.trainingSessionsListRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        this.viewModel.getAvailableTrainingSessions().observe(this, trainingSessions -> {
            RecyclerViewTrainingSessionsAdapter adapter = new RecyclerViewTrainingSessionsAdapter( this::onClickButton, this, this.getContext(), trainingSessions);
            recyclerView.setAdapter(adapter);
        });
    }

        private void onClickButton(TrainingSession trainingSession)
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Confirmation")
                    .setMessage
                            (
                                    "Voulez-vous vous inscrire ?"
                            )
                    .setPositiveButton("Oui", (dialog, which) -> {
                        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(TrainingSessionViewModel.class);
                    })
                .setNegativeButton("Non", ((dialog, which) -> {
                    Toast.makeText(getContext(), "Inscrition annulée", Toast.LENGTH_SHORT).show();
                }));
            builder.create().show();
        }


}
