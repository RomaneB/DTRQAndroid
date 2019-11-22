package org.diiage.dtrqandroid.drivingLessons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;
import org.diiage.dtrqandroid.databinding.FragmentNombreBinding;
import org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter.RecyclerViewNombreLeconsAdapter;
import org.diiage.dtrqandroid.drivingLessons.viewPager.NombreLeconsFragmentPagerAdapter;
import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;


public class NombreLessonsFragment extends Fragment {
    ViewModelProvider.Factory viewModelFactory;

    private DrivingLessonViewModel drivingLessonViewModel;
    UserSessionManager session;
    private Long userId;

    private TextView textView;
    private FragmentNombreLessonsBinding binding;

    public NombreLessonsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((RoomApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        binding = FragmentNombreLessonsBinding.inflate(inflater, container, false);
        this.drivingLessonViewModel = ViewModelProviders.of(this,viewModelFactory).get(DrivingLessonViewModel.class);
        textView = binding.txtNombreLessons;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        drivingLessonViewModel.getNombreMesLecons(userId).observe(this, nombreLecons -> {
            binding.setValue(nombreLecons);
        });

        return binding.getRoot();
    }

}
