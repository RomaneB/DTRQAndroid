package org.diiage.dtrqandroid.data.di.module;

import android.app.Application;

import androidx.annotation.NonNull;

import org.diiage.dtrqandroid.data.RoomApplication;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

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