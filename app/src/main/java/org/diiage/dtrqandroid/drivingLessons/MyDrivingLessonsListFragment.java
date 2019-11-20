package org.diiage.dtrqandroid.drivingLessons;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentMyDrivingLessonsListBinding;
import org.diiage.dtrqandroid.databinding.FragmentNextDrivingLessonsListBinding;
import org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter.RecyclerViewMyDrivingLessonAdapter;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MyDrivingLessonsListFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserSessionManager session;

    private DrivingLessonViewModel myDrivingLessonViewModel;
    private  FragmentMyDrivingLessonsListBinding binding;

    private Long userId;

    private RecyclerView recyclerView;
    private RecyclerViewMyDrivingLessonAdapter adapter;

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

        binding = FragmentMyDrivingLessonsListBinding.inflate(inflater,container,false);
        this.myDrivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        recyclerView = binding.myDrivingLessonRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        myDrivingLessonViewModel.getMyDrivingLessons(userId).observe(this, drivingLessons -> {
            if(drivingLessons != null){
                RecyclerViewMyDrivingLessonAdapter adapter = new RecyclerViewMyDrivingLessonAdapter(this::onClickButton,this, this.getContext(), drivingLessons );
                recyclerView.setAdapter(adapter);
            }
        });

        return binding.getRoot();

    }

    private void onClickButton(DrivingLessonWithInstructor myDrivingLesson){

        Date currentDate = new Date();
        Date threeDaysAfter = new Date(currentDate.getTime() + 259200000L);
        if(myDrivingLesson.getDate().before(threeDaysAfter)){
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Impossible de vous désinscrire")
                    .setMessage("Impossible de vous désinscrire car votre leçon est dans moins de 72 heures")
                    .setPositiveButton("Ok", (dialog, which) -> {
                    });
            builder.create().show();
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Se désincrire")
                    .setMessage("Voulez-vous vous désinscrire?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        //TODO retrieve id and update the driving lesson with the user id

                        myDrivingLessonViewModel = ViewModelProviders.of(getActivity() , viewModelFactory).get(DrivingLessonViewModel.class);
                        myDrivingLessonViewModel.registrer(Long.valueOf(0),  myDrivingLesson.getDrivingLessonId());
                        Toast.makeText(getContext(),"Désinscription réussie" , Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Non", (dialog, which) -> {
                        Toast.makeText(getContext(), "Désinscription annulée", Toast.LENGTH_SHORT).show();
                    });
            builder.create().show();
        }
    }
}
