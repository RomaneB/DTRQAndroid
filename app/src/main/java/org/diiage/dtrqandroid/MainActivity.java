package org.diiage.dtrqandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.diiage.dtrqandroid.data.db.viewmodel.UserViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;

import java.util.HashMap;

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
                // Step 6 : Navigate to evaluation fragment when the user click on the new button.
            case R.id.btn_evaluation:
                NavController navCtrl = Navigation.findNavController(findViewById(R.id.nav_host_fragment));

                /*
                  It allows me to prevent a "bad behavior" :
                  The user is able to navigate between fragments with view pager but
                  that is not the case when we navigate from a fragment with a view pager
                  in it to another one without view pager. I don't understand why
                  at this moment, if you an explanation for this, I am interested.

                  I only found this : https://stackoverflow.com/a/55705293.
                 */
                if (navCtrl.getCurrentDestination().getId() != R.id.home_fragment)
                {
                    navCtrl.navigate(R.id.home_fragment);
                }

                navCtrl.navigate(R.id.action_home_fragment_to_evaluationFragment);
                return true;
            case R.id.btn_logout:
                session.logoutUser(MainActivity.this);

        }

        return super.onOptionsItemSelected(item);
    }
}
