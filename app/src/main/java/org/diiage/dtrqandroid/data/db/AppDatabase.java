package org.diiage.dtrqandroid.data.db;


import org.diiage.dtrqandroid.data.db.dao.DrivingLessonDao;
import org.diiage.dtrqandroid.data.db.dao.InstructorDao;
import org.diiage.dtrqandroid.data.db.dao.TrainingSessionDao;
import org.diiage.dtrqandroid.data.db.dao.UserDao;
import org.diiage.dtrqandroid.data.db.dao.UserTrainingDao;
import org.diiage.dtrqandroid.data.converters.DateTypeConverter;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.entity.Instructor;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.User;
import org.diiage.dtrqandroid.data.db.entity.UserTraining;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, DrivingLesson.class, Instructor.class, UserTraining.class, TrainingSession.class}, version = 16, exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract DrivingLessonDao drivingLessonDao();
    public abstract InstructorDao instructorDao();
    public abstract UserTrainingDao userTrainingDao();
    public abstract TrainingSessionDao trainingSessionDao();
}
