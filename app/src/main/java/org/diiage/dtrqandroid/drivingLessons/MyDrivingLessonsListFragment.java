package org.diiage.dtrqandroid.drivingLessons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter.RecyclerViewMyDrivingLessonAdapter;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyDrivingLessonsListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserSessionManager session;

    private LayoutInflater layoutInflater;
    private DrivingLessonViewModel myDrivingLessonViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewMyDrivingLessonAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private Long userId;

    public MyDrivingLessonsListFragment() {
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
        myDrivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        myDrivingLessonViewModel.getMyDrivingLessons(userId).observe(this, sessions -> {
                    if(sessions != null && adapter != null){
                        adapter.setMyDrivingLessons(sessions);
                    }
                }
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_driving_lessons_list, container, false);


        layoutInflater = getActivity().getLayoutInflater();

        recyclerView = (RecyclerView) view.findViewById(R.id.myDrivingLessonRecyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        adapter = new RecyclerViewMyDrivingLessonAdapter();

        recyclerView.setAdapter(adapter);

        if(myDrivingLessonViewModel != null && myDrivingLessonViewModel.getMyDrivingLessons(userId).getValue() != null ){
            adapter.setMyDrivingLessons(myDrivingLessonViewModel.getMyDrivingLessons(userId).getValue());
        }
        return view;

    }
}
