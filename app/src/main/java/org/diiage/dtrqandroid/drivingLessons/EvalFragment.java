package org.diiage.dtrqandroid.drivingLessons;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentEvalFragmentBinding;

import javax.inject.Inject;

public class EvalFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserSessionManager session;
    private Long userId;

    private DrivingLessonViewModel drivingLessonViewModel;
    private FragmentEvalFragmentBinding binding;
    private TextView txtEval;

    public EvalFragment() {
        // Required empty public constructor
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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_eval_fragment, container, false);
        binding = FragmentEvalFragmentBinding.inflate(inflater,container,false);
        this.drivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        txtEval = binding.txtEvalFragment;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());



        drivingLessonViewModel.getCountDrivingLessonByUserId(userId).observe(this, count -> {
           binding.setValue(count);
        });
        return binding.getRoot();

    }


}
