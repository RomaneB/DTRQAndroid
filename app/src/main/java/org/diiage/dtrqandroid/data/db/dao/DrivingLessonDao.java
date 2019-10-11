package org.diiage.dtrqandroid.data.db.dao;

import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DrivingLessonDao {
    @Query("SELECT * FROM drivingLesson")
    LiveData<List<DrivingLesson>> getAllDrivingLessons();

    @Insert
    void insert(DrivingLesson drivingLesson);
}
