package org.diiage.dtrqandroid.data.di.component;

import android.app.Application;

import org.diiage.dtrqandroid.MainActivity;
import org.diiage.dtrqandroid.data.di.module.ApplicationModule;
import org.diiage.dtrqandroid.data.di.module.RoomModule;
import org.diiage.dtrqandroid.data.view.LoginPage;
import org.diiage.dtrqandroid.drivingLessons.MyDrivingLessonsListFragment;
import org.diiage.dtrqandroid.drivingLessons.NextDrivingLessonsListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        RoomModule.class,
})
public interface ApplicationComponent {
    void inject (MainActivity mainActivity);
    void inject (NextDrivingLessonsListFragment nextDrivingLessonListFragment);
    void inject (LoginPage loginPage);
    void inject (MyDrivingLessonsListFragment myDrivingLessonsListFragment);

    Application getApplication();

}

