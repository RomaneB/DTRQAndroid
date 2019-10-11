package org.diiage.dtrqandroid.data.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.diiage.dtrqandroid.R;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class LoginPage extends Fragment {

    public LoginPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_login_page, container, false);


    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnLogin).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginPage_to_drivingLessonListFragment));

    }

}
