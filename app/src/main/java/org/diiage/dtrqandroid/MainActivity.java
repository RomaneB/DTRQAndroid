package org.diiage.dtrqandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.repository.UserRepository;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.list.DrivingLessonAdapter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
