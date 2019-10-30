package org.diiage.dtrqandroid.data.db.dao;

import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DrivingLessonDao {
    @Query("SELECT * FROM drivingLesson")
    LiveData<List<DrivingLesson>> getAllDrivingLessons();

    @Query("SELECT * FROM drivingLesson INNER JOIN instructor ON drivingLesson.instructorId = instructor.instructorId WHERE userId = 0 AND date NOT IN (SELECT date FROM drivingLesson WHERE userId = :userId)")
    LiveData<List<DrivingLesson>> getAvailableDrivingLessons(Long userId);

    @Insert
    void insert(DrivingLesson drivingLesson);

    @Query("UPDATE drivingLesson SET userId = :userId WHERE drivingLessonId = :drivingId ")
    void registrer(Long userId, Long drivingId);

    @Query("SELECT * FROM drivingLesson WHERE userId = :userId")
    LiveData<List<DrivingLesson>> getMyDrivingLessons(long userId);


}
