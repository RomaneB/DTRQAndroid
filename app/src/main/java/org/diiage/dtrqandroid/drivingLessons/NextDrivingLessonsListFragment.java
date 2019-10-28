package org.diiage.dtrqandroid.drivingLessons;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter.RecyclerViewNextDrivingLessonAdapter;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NextDrivingLessonsListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserSessionManager session;
    private Long userId;
    private LayoutInflater layoutInflater;
    private DrivingLessonViewModel drivingLessonViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewNextDrivingLessonAdapter adapter;

    public NextDrivingLessonsListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_next_driving_lessons_list, container, false);


        view.findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser(getActivity());
            }
        });

        layoutInflater = getActivity().getLayoutInflater();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        adapter = new RecyclerViewNextDrivingLessonAdapter(this, getContext());

        recyclerView.setAdapter(adapter);

        if(drivingLessonViewModel != null && drivingLessonViewModel.getAvailableDrivingLessons(userId).getValue() != null ){
            adapter.setDrivingLessons(drivingLessonViewModel.getAvailableDrivingLessons(userId).getValue());
        }
        return view;

    }



}
