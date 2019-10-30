package org.diiage.dtrqandroid.drivingLessons;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        this.drivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
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


        view.findViewById(R.id.btnLogout).setOnClickListener(v -> session.logoutUser(getActivity()));

        layoutInflater = getActivity().getLayoutInflater();

        recyclerView = view.findViewById(R.id.recyclerViewNextDriving);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new RecyclerViewNextDrivingLessonAdapter(drivingLesson -> {
            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Inscription")
                    .setMessage("Voulez-vous vous inscrire?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        //TODO retrieve id and update the driving lesson with the user id

                        drivingLessonViewModel = ViewModelProviders.of(getActivity() , viewModelFactory).get(DrivingLessonViewModel.class);
                        drivingLessonViewModel.registrer(userId,  drivingLesson.getDrivingLessonId());
                        Toast.makeText(view.getContext(),"Inscription réussie" + drivingLesson.drivingLessonId, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Non", (dialog, which) -> {
                        Toast.makeText(view.getContext(), "Inscription annulée", Toast.LENGTH_SHORT).show();
                    });
            builder.create().show();
        });

        recyclerView.setAdapter(adapter);

        if(drivingLessonViewModel != null && drivingLessonViewModel.getAvailableDrivingLessons(userId).getValue() != null ){
            adapter.setDrivingLessons(drivingLessonViewModel.getAvailableDrivingLessons(userId).getValue());
        }
        return view;

    }



}
