package org.diiage.dtrqandroid.data.di.module;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import org.diiage.dtrqandroid.data.db.AppDatabase;
import org.diiage.dtrqandroid.data.db.dao.DrivingLessonDao;
import org.diiage.dtrqandroid.data.db.dao.InstructorDao;
import org.diiage.dtrqandroid.data.db.dao.TrainingSessionDao;
import org.diiage.dtrqandroid.data.db.dao.UserDao;
import org.diiage.dtrqandroid.data.db.dao.UserTrainingDao;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.entity.Instructor;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.User;
import org.diiage.dtrqandroid.data.db.entity.UserTraining;
import org.diiage.dtrqandroid.data.db.repository.DrivingLessonRepository;
import org.diiage.dtrqandroid.data.db.repository.InstructorRepository;
import org.diiage.dtrqandroid.data.db.repository.TrainingSessionRepository;
import org.diiage.dtrqandroid.data.db.repository.UserRepository;
import org.diiage.dtrqandroid.data.db.repository.UserTrainingRepository;
import org.diiage.dtrqandroid.data.db.viewmodel.CustomViewModelFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final AppDatabase database;

    public RoomModule(Application application){


        this.database = Room.databaseBuilder(application, AppDatabase.class, "DTRQBase.db").addCallback(roomDatabaseCallback).fallbackToDestructiveMigration().build();
    }

    ///////////// USER /////////
    /// Provides the repository ////
    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDao userDao){

        return new UserRepository(userDao);
    }
    /// provides the dao /////
    @Provides
    @Singleton
    UserDao provideUserDao(AppDatabase database){
        return database.userDao();
    }

    ////////// END USER /////////

    ////////// DrivingLesson /////////
    @Provides
    @Singleton
    DrivingLessonRepository provideDrivingLessonRepository(DrivingLessonDao drivingLessonDao){
        return new DrivingLessonRepository(drivingLessonDao);
    }

    @Singleton
    @Provides
    DrivingLessonDao provideDrivingLessonDao(AppDatabase db) { return db.drivingLessonDao();}

    ////////// END DrivingLesson /////////

    ////////// Instructor /////////

    @Provides
    @Singleton
    InstructorRepository provideInstructorRepository(InstructorDao instructorDao){
        return new InstructorRepository(instructorDao);
    }

    @Singleton
    @Provides
    InstructorDao provideInstructorDao(AppDatabase db) { return db.instructorDao();}

    ////////// END Instructor /////////
    ////////// TrainingSession /////////
    @Provides
    @Singleton
    TrainingSessionRepository provideTrainingRepository( TrainingSessionDao trainingSessionDao){
        return new TrainingSessionRepository(trainingSessionDao);
    }


    @Singleton
    @Provides
    TrainingSessionDao provideTrainingSessionDao(AppDatabase db) { return db.trainingSessionDao();}

    ////////// END TrainingSession /////////
    ////////// UserTraining /////////

    @Provides
    @Singleton
    UserTrainingRepository provideUserTrainingRepository(UserTrainingDao userTrainingDao){
        return new UserTrainingRepository(userTrainingDao);
    }

    @Singleton
    @Provides
    UserTrainingDao provideUserTrainingDao(AppDatabase db) { return db.userTrainingDao();}

    ////////// END UserTraining /////////



    //// provide database /////
    @Provides
    @Singleton
    AppDatabase provideDatabase(Application application) {
        return database;
    }



    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(
            UserRepository userRepository,
            TrainingSessionRepository trainingSessionRepository,
            DrivingLessonRepository drivingLessonRepository,
            InstructorRepository instructorRepository,
            UserTrainingRepository userTrainingRepository
    ){
        return new CustomViewModelFactory(
                drivingLessonRepository,
                instructorRepository,
                trainingSessionRepository,
                userRepository,
                userTrainingRepository
        );
    }

    private RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new RoomModule.PopulateDbAsync(database).execute();
        }
    };

    private class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao userDao;
        private final DrivingLessonDao drivingLessonDao;
        private final TrainingSessionDao trainingSessionDao;
        private final InstructorDao instructorDao;
        private final UserTrainingDao userTrainingDao;

        PopulateDbAsync(AppDatabase db) {
            userDao = db.userDao();
            drivingLessonDao = db.drivingLessonDao();
            trainingSessionDao = db.trainingSessionDao();
            instructorDao = db.instructorDao();
            userTrainingDao = db.userTrainingDao();

        }


        @Override
        protected Void doInBackground(final Void... params) {
            if( userDao.getCountUser() <= 0 ){


                User u1 = new User(1, "Roro", "roro123");
                userDao.insert(u1);
                User u2 = new User(2, "Baboon", "123babouin");
                userDao.insert(u2);
                User u3 = new User(3, "Tiphaine", "123");
                userDao.insert(u3);
                User u4 = new User(4, "Patatana", "123patate");
                userDao.insert(u4);
                User u5 = new User(5, "Michel", "123michel");
                userDao.insert(u5);

                Instructor i1 = new Instructor(1, "Matthieu", "Royer");
                instructorDao.insert(i1);
                Instructor i2 = new Instructor(2, "Léonard", "Potherat");
                instructorDao.insert(i2);
                Instructor i3 = new Instructor(3, "Georges", "Carbonare");
                instructorDao.insert(i3);


                DrivingLesson d1 = null;
                try {
                    d1 = new DrivingLesson(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-20 12:00:00"), "Driving de Patatana", 1,4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d1);
                DrivingLesson d2 = null;
                try {
                    d2 = new DrivingLesson(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-12 14:00:00"), "Driving sans user", 2, 4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d2);
                DrivingLesson d3 = null;
                try {
                    d3 = new DrivingLesson(3,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-21 12:40:00"), "Driving de Baboon",1,2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d3);
                DrivingLesson d4 = null;
                try {
                    d4 = new DrivingLesson(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-08 12:00:00"), "Driving de Patatana qu'il ne doit pas voir mais les autres oui", 2,0);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d4);

                DrivingLesson d5 = null;
                try {
                    d5 = new DrivingLesson(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-19 14:00:00"), "Driving de Patatana", 1,4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d5);

                DrivingLesson d6 = null;
                try {
                    d6 = new DrivingLesson(6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-20 10:00:00"), "Driving de Patatana", 1,4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d6);

                DrivingLesson d7 = null;
                try {
                    d7 = new DrivingLesson(7, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-02-10 10:00:00"), "Driving de Patatana qu'il ne doit pas voir mais les autres oui", 2,4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d7);
                DrivingLesson d8 = null;
                try {
                    d8 = new DrivingLesson(8, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-01-08 16:00:00"), "Driving de Patatana qu'il ne doit pas voir mais les autres oui", 2,0);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d8);
                DrivingLesson d9 = null;
                try {
                    d9 = new DrivingLesson(9, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-20 17:00:00"), "Driving de Patatana qu'il ne doit pas voir mais les autres oui", 1,0);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drivingLessonDao.insert(d9);

                TrainingSession t1 = null;
                try {
                    t1 = new TrainingSession(1,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-12-23 12:30:00"), 2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t1);
                TrainingSession t2 = null;
                try {
                    t2 = new TrainingSession(2,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-19 12:00:00"), 4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t2);
                TrainingSession t3 = null;
                try {
                    t3 = new TrainingSession(3,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-15 14:00:00"), 9);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t3);

                TrainingSession t4 = null;
                try{
                    t4 = new TrainingSession(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-12-15 14:00:00"), 5);
                } catch(ParseException e){
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t4);

                TrainingSession t5 = null;
                try{
                    t5 = new TrainingSession(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-15 14:00:00"), 5);
                } catch(ParseException e){
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t5);

                TrainingSession t6 = null;
                try{
                    t6 = new TrainingSession(6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-25 16:00:00"), 5);
                } catch(ParseException e){
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t6);

                TrainingSession t7 = null;
                try{
                    t7 = new TrainingSession(7, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-15 16:00:00"), 1);
                } catch(ParseException e){
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t7);

                TrainingSession t8 = null;
                try{
                    t8 = new TrainingSession(8, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-08-25 16:00:00"), 7);
                } catch(ParseException e){
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t8);

                TrainingSession t9 = null;
                try{
                    t9 = new TrainingSession(9, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-12-25 12:00:00"), 3);
                } catch(ParseException e){
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t9);

                TrainingSession t10 = null;
                try{
                    t10 = new TrainingSession(10, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-01-12 10:00:00"), 15);
                } catch(ParseException e){
                    e.printStackTrace();
                }
                trainingSessionDao.insert(t10);

                UserTraining ut1 = new UserTraining(4, 1, 0);
                userTrainingDao.insert(ut1);
                UserTraining ut2 = new UserTraining(5, 2, 12);
                userTrainingDao.insert(ut2);
                UserTraining ut3 = new UserTraining(5, 3, 16);
                userTrainingDao.insert(ut3);
                UserTraining ut4 = new UserTraining(4, 5, 0);
                userTrainingDao.insert(ut4);
                UserTraining ut5 = new UserTraining(4, 6, 5);
                userTrainingDao.insert(ut5);
                UserTraining ut6 = new UserTraining(4, 7, 2);
                userTrainingDao.insert(ut6);
                UserTraining ut7 = new UserTraining(4, 8, 10);
                userTrainingDao.insert(ut7);
            }
            return null;
        }

    }


}
