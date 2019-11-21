package org.diiage.dtrqandroid.drivingLessons;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentPreviousDrivingLessonsListBinding;
import org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter.RecyclerViewPastDrivingLessonAdapter;

import java.util.Date;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousDrivingLessonsListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private DrivingLessonViewModel pastDrivingLessonViewModel;
    private FragmentPreviousDrivingLessonsListBinding binding;

    private Long userId;
    UserSessionManager session;

    private RecyclerView recyclerView;
    private RecyclerViewPastDrivingLessonAdapter adapter;

    public PreviousDrivingLessonsListFragment() {
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
        pastDrivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        pastDrivingLessonViewModel.getMyDrivingLessons(userId).observe(this, sessions -> {
                    if(sessions != null && adapter != null){
                        adapter.setPastDrivingLessons(sessions);
                    }
                }
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPreviousDrivingLessonsListBinding.inflate(inflater,container,false);
        this.pastDrivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        recyclerView = binding.recyclerViewPastDriving;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        pastDrivingLessonViewModel.getPastDrivingLessons(userId).observe(this, drivingLessons -> {
            if(drivingLessons != null){
                RecyclerViewPastDrivingLessonAdapter adapter = new RecyclerViewPastDrivingLessonAdapter(this::onClickButton,this, this.getContext(), drivingLessons );
                recyclerView.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }

    private void onClickButton(DrivingLessonWithInstructor drivingLesson){
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Informations")
                    .setMessage("Commentaire : \n" + drivingLesson.text);
            builder.create().show();
        }

}
