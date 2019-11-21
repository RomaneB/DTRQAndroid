package org.diiage.dtrqandroid.trainingSessions;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.viewmodel.TrainingSessionViewModel;
import org.diiage.dtrqandroid.data.db.viewmodel.UserTrainingViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentMyTrainingSessionsListBinding;
import org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter.RecyclerViewMyTrainingSessionsAdapter;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTrainingSessionsListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserSessionManager session;
    Long userId;

    private TrainingSessionViewModel viewModel;
    private UserTrainingViewModel userTrainingViewModel;
    private FragmentMyTrainingSessionsListBinding binding;

    private RecyclerView recyclerView;
    private RecyclerViewMyTrainingSessionsAdapter adapter;

    public MyTrainingSessionsListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new UserSessionManager(getContext());
        userId = session.getUserId();

        ((RoomApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrainingSessionViewModel.class);
        viewModel.getTrainingSessionsByUserId(userId).observe(this, sessions -> {
                    if(sessions != null && adapter != null){
                        adapter.setTrainingSessions(sessions);
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentMyTrainingSessionsListBinding.inflate(inflater,container,false);
        this.viewModel = ViewModelProviders.of(this,viewModelFactory).get(TrainingSessionViewModel.class);
        recyclerView = binding.recyclerViewMyTrainingLessons;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getTrainingSessionsByUserId(userId).observe(this, trainingSessions -> {
            if (trainingSessions !=null){
                RecyclerViewMyTrainingSessionsAdapter adapter = new RecyclerViewMyTrainingSessionsAdapter(this::onClickButton,this.getContext(),this, trainingSessions);
                recyclerView.setAdapter(adapter);
            }
        });
        return binding.getRoot();
    }

    private void onClickButton(TrainingSession trainingSession) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmation de désinscription")
                .setMessage("Êtes-vous sûr de vouloir vous désinscrire à la session du : "+trainingSession.date.toString())
                .setPositiveButton("Oui", (dialog, which) -> {
                    viewModel = ViewModelProviders.of(getActivity() , viewModelFactory).get(TrainingSessionViewModel.class);
                    userTrainingViewModel = ViewModelProviders.of(getActivity(),viewModelFactory).get(UserTrainingViewModel.class);
                    viewModel.updateAvailableSeats(trainingSession.getTrainingSessionId());
                    userTrainingViewModel.unregister(trainingSession.getTrainingSessionId(), userId);
                    Toast.makeText(getContext(),"Désinscription réussie" , Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Non", (dialog, which) -> {
                    Toast.makeText(getContext(), "Désinscription annulée", Toast.LENGTH_SHORT).show();
                });
        builder.create().show();
    }
}
