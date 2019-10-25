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

    @Query("SELECT * FROM drivingLesson WHERE userId = 0 AND date NOT IN (SELECT date FROM drivingLesson WHERE userId = :idUser)")
    LiveData<List<DrivingLesson>> getAvailableDrivingLessons(Long idUser);

    @Insert
    void insert(DrivingLesson drivingLesson);

    @Query("SELECT * FROM drivingLesson WHERE userId = :idUser")
    LiveData<List<DrivingLesson>> getMyDrivingLessons(long idUser);


}
