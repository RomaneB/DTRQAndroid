package org.diiage.dtrqandroid.data.list;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrivingLessonListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    
    private LayoutInflater layoutInflater;
    private DrivingLessonViewModel drivingLessonViewModel;
    private RecyclerView recyclerView;
    private DrivingLessonAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DrivingLesson> listOfData;
    public DrivingLessonListFragment() {
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
        drivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        drivingLessonViewModel.getAllDrivingLessons().observe(this, sessions -> {
                if(sessions != null && adapter != null){
                    adapter.setDrivingLessons(sessions);
                }
        }
        );
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.driving_lesson_list_fragment, container, false);
        layoutInflater = getActivity().getLayoutInflater();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        adapter = new DrivingLessonAdapter();

        recyclerView.setAdapter(adapter);

        if(drivingLessonViewModel != null && drivingLessonViewModel.getAllDrivingLessons().getValue() != null ){
            adapter.setDrivingLessons(drivingLessonViewModel.getAllDrivingLessons().getValue());
        }
        return view;

    }



}
