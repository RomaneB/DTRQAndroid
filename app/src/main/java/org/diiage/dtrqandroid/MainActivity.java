package org.diiage.dtrqandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.repository.UserRepository;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.db.viewmodel.UserViewModel;
import org.diiage.dtrqandroid.data.list.DrivingLessonAdapter;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new UserSessionManager(getApplicationContext());
        if(session.isUserLoggedIn()){
            // get user data from session
            HashMap<String, String> user = session.getUserDetails();

            // get name
            String username = user.get(UserSessionManager.KEY_USERNAME);
            setTitle("DTRQAndroid- Bienvenue " + username);
        }
    }
}
