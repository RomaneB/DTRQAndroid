package org.diiage.dtrqandroid.data.di.module;

import android.app.Application;

import org.diiage.dtrqandroid.data.RoomApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule (RoomApplication app){
        application = app;
    }

    @Provides
    Application provideApplication() {
        return  application;
    }
}