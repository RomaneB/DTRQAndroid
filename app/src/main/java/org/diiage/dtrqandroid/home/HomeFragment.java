package org.diiage.dtrqandroid.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.dtrqandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       //inflater.inflate(R.layout.fragment_home, container, false).findViewById(R.id.btnGoToDriving).setOnClickListener(v -> Navigation.findNavController(this.getView()).navigate(R.id.driving_lessons_tabs_fragment));



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void goToSession(View view){

        Navigation.findNavController(view).navigate(R.id.driving_lessons_tabs_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       view.findViewById(R.id.btnGoToDriving).setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.driving_lessons_tabs_fragment));

        view.findViewById(R.id.btnGoToTraining).setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.training_sessions_tabs_fragment));
        view.findViewById(R.id.btnGoToNombreLecons).setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.nombre_lessons_fragment));
    }
}
