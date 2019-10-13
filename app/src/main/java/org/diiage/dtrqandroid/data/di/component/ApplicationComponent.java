package org.diiage.dtrqandroid.data.di.component;

import android.app.Application;

import org.diiage.dtrqandroid.MainActivity;
import org.diiage.dtrqandroid.data.di.module.ApplicationModule;
import org.diiage.dtrqandroid.data.di.module.DatabaseModule;
import org.diiage.dtrqandroid.data.di.module.RoomModule;
import org.diiage.dtrqandroid.data.list.DrivingLessonListFragment;
import org.diiage.dtrqandroid.data.view.LoginPage;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        RoomModule.class,
})
public interface ApplicationComponent {
    void inject (MainActivity mainActivity);
    void inject (DrivingLessonListFragment drivingLessonListFragment);
    void inject (LoginPage loginPage);

    Application getApplication();

}

