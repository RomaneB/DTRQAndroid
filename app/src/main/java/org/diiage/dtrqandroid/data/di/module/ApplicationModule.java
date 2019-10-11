package org.diiage.dtrqandroid.data.di.module;

import android.app.Application;
import android.content.Context;

import org.diiage.dtrqandroid.data.RoomApplication;
import org.diiage.dtrqandroid.data.di.ApplicationContext;

import javax.inject.Singleton;

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