package org.diiage.dtrqandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        }

        return super.onOptionsItemSelected(item);
    }
}
