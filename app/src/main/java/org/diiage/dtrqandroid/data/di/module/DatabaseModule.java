package org.diiage.dtrqandroid.data.di.module;

import android.content.Context;

import org.diiage.dtrqandroid.data.db.AppDatabase;
import org.diiage.dtrqandroid.data.db.dao.DrivingLessonDao;
import org.diiage.dtrqandroid.data.db.dao.InstructorDao;
import org.diiage.dtrqandroid.data.db.dao.TrainingSessionDao;
import org.diiage.dtrqandroid.data.db.dao.UserDao;
import org.diiage.dtrqandroid.data.db.dao.UserTrainingDao;
import org.diiage.dtrqandroid.data.di.ApplicationContext;
import org.diiage.dtrqandroid.data.di.DatabaseInfo;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

public class DatabaseModule {
}