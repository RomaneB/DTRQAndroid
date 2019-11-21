package org.diiage.dtrqandroid.data.db.dao;

import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TrainingSessionDao {
    @Query("SELECT * FROM trainingsession")
    LiveData<List<TrainingSession>> getAllTrainingSession();

    @Query("SELECT * FROM trainingSession " +
            "WHERE date(datetime(date / 1000 , 'unixepoch')) > date('now') " +
            "AND availableSeat > 0 " +
            "ORDER BY date")
    LiveData<List<TrainingSession>> getAvailableTrainingSessions();

    @Query("SELECT * FROM trainingsession INNER JOIN user_training ON trainingSession.trainingSessionId = user_training.trainingId WHERE userId = :userId AND date(datetime(date / 1000 , 'unixepoch')) < date('now') ORDER BY date")
    LiveData<List<TrainingSessionWithUser>> getPastTrainingSessions(long userId);

    @Insert
    void insert(TrainingSession trainingSession);
}
