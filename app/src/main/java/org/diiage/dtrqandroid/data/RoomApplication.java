package org.diiage.dtrqandroid.data;

import android.app.Application;
import android.util.Log;

import org.diiage.dtrqandroid.data.di.component.ApplicationComponent;
import org.diiage.dtrqandroid.data.di.component.DaggerApplicationComponent;
import org.diiage.dtrqandroid.data.di.module.ApplicationModule;
import org.diiage.dtrqandroid.data.di.module.RoomModule;

public class RoomApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
