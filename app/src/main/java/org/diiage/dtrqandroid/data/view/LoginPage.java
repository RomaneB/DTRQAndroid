package org.diiage.dtrqandroid.data.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.db.viewmodel.UserViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

public class LoginPage extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserViewModel userViewModel;
    UserSessionManager session;


    String username;
    String password;
    public LoginPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        ((RoomApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_page, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        session = new UserSessionManager(getContext());
        Fragment currentFragment = this;

        if (!session.isUserLoggedIn()) {
            view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText usernameEditText = (EditText) view.findViewById(R.id.editUsername);
                    username = usernameEditText.getText().toString();
                    EditText passwordEditText = view.findViewById(R.id.editPassword);
                    password = passwordEditText.getText().toString();
                    if(userViewModel != null ){
                        LoginUser(username, password);
                    }

                }
            });
        } else {
            Navigation.findNavController(view).navigate(R.id.action_loginPage_to_home_fragment);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    public void LoginUser(String username, String password){
        View view = getView();
        userViewModel.getUserByUsername(username, password).observe(this, user -> {
            if (user != null) {
                session.createUserLoginSession(username, Long.toString(user.getUserId()));
                getActivity().setTitle("DTRQAndroid - Bienvenue " + username);
                Navigation.findNavController(view).navigate(R.id.action_loginPage_to_home_fragment);
            } else {
                TextView txtError = (TextView) view.findViewById(R.id.txtError);
                txtError.setText("Nom d'utlisateur ou mot de passe incorrect");
            }
        });
    }
}
