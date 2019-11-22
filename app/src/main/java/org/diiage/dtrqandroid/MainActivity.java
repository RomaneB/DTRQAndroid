package org.diiage.dtrqandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.diiage.dtrqandroid.data.db.viewmodel.UserViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;

import java.util.HashMap;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

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
        AppCenter.start(getApplication(), "b76fe9b5-ece8-4594-b7a8-a9136915835d",
               Analytics.class, Crashes.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //gère le click sur une action de l'ActionBar
        switch (item.getItemId()){
            case R.id.btn_driving_lessons:
                Navigation.findNavController(findViewById(R.id.nav_host_fragment)).navigate(R.id.driving_lessons_tabs_fragment);
                return true;
            case R.id.btn_training_sessions:
                Navigation.findNavController(findViewById(R.id.nav_host_fragment)).navigate(R.id.training_sessions_tabs_fragment);
                return true;
            case R.id.btn_count_driving_lessons:
                Navigation.findNavController(findViewById(R.id.nav_host_fragment)).navigate(R.id.count_driving_lesson_page);
            case R.id.btn_logout:
                session.logoutUser(MainActivity.this);
        }

        return super.onOptionsItemSelected(item);
    }
}
