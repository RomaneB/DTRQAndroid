package org.diiage.dtrqandroid.data.list;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrivingLessonListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserSessionManager session;
    private Long userId;
    private LayoutInflater layoutInflater;
    private DrivingLessonViewModel drivingLessonViewModel;
    private RecyclerView recyclerView;
    private DrivingLessonAdapter adapter;
    public DrivingLessonListFragment() {
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
        drivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        drivingLessonViewModel.getAvailableDrivingLessons(userId).observe(this, sessions -> {
                if(sessions != null && adapter != null){
                    adapter.setDrivingLessons(sessions);
                }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.driving_lesson_list_fragment, container, false);


        view.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser(getActivity());
            }
        });

        view.findViewById(R.id.btnGoToMyDriving).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_drivingLessonListFragment_to_myDrivingLessonListFragment);
            }
        });



        layoutInflater = getActivity().getLayoutInflater();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        adapter = new DrivingLessonAdapter(this, getContext());

        recyclerView.setAdapter(adapter);

        if(drivingLessonViewModel != null && drivingLessonViewModel.getAvailableDrivingLessons(userId).getValue() != null ){
            adapter.setDrivingLessons(drivingLessonViewModel.getAvailableDrivingLessons(userId).getValue());
        }
        return view;

    }



}
