package org.diiage.dtrqandroid.data.db.dao;

import org.diiage.dtrqandroid.data.db.entity.TrainingSession;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TrainingSessionDao {
    @Query("SELECT * FROM trainingsession")
    LiveData<List<TrainingSession>> getAllTrainingSession();

    @Insert
    void insert(TrainingSession trainingSession);

    @Query("SELECT * FROM trainingsession JOIN user_training ON trainingSessionId=trainingId WHERE userId= :userId")
    LiveData<List<TrainingSession>> getTrainingSessionByUserId(long userId);

    @Query("UPDATE trainingsession SET availableSeat= availableSeat+1 WHERE trainingSessionId= :trainingSessionId")
    void updateAvailableSeats(long trainingSessionId);
}
