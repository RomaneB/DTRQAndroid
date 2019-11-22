package org.diiage.dtrqandroid.drivingLessons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import org.diiage.dtrqandroid.databinding.FragmentCountDrivingLessonsBinding;


public class CountDrivingLessonsFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private DrivingLessonViewModel drivingLessonViewModel;
    private FragmentCountDrivingLessonsBinding binding;

    UserSessionManager session;
    private Long userId;

    public CountDrivingLessonsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        session = new UserSessionManager(getContext());
        //get UserId
        userId = session.getUserId();
        ((RoomApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Binding
        binding = FragmentCountDrivingLessonsBinding.inflate(inflater, container, false);

        //ViewModel
        this.drivingLessonViewModel = ViewModelProviders.of(this, viewModelFactory).get(DrivingLessonViewModel.class);
        drivingLessonViewModel.getDrivingLessonCountByUserid(userId).observe(this, count -> {
            //Update value of the number
            binding.setCount(count);
        });

        return binding.getRoot();
    }
}
