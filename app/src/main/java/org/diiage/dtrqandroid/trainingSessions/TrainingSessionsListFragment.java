package org.diiage.dtrqandroid.trainingSessions;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.viewmodel.TrainingSessionViewModel;
import org.diiage.dtrqandroid.databinding.FragmentTrainingSessionsListBinding;
import org.diiage.dtrqandroid.trainingSessions.recyclerViewAdapter.RecyclerViewTrainingSessionsAdapter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingSessionsListFragment extends Fragment {

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

        this.viewModel.getAllTrainingSessions().observe(this, trainingSessions -> {
            RecyclerViewTrainingSessionsAdapter adapter = new RecyclerViewTrainingSessionsAdapter(this, this.getContext(), trainingSessions);
            recyclerView.setAdapter(adapter);
        });

    }
}
