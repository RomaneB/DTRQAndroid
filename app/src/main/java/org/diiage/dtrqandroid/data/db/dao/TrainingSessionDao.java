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
            "AND availableSeat > 0 AND trainingSessionId NOT IN (SELECT trainingId FROM user_training WHERE userId = :userId) " +
            "ORDER BY date")
    LiveData<List<TrainingSession>> getAvailableTrainingSessions(long userId);

    @Query("SELECT * FROM trainingsession INNER JOIN user_training ON trainingSession.trainingSessionId = user_training.trainingId WHERE userId = :userId AND date(datetime(date / 1000 , 'unixepoch')) < date('now') ORDER BY date")
    LiveData<List<TrainingSessionWithUser>> getPastTrainingSessions(long userId);

    @Insert
    void insert(TrainingSession trainingSession);

    @Query("SELECT * FROM trainingsession JOIN user_training ON trainingSessionId=trainingId WHERE userId= :userId")
    LiveData<List<TrainingSession>> getTrainingSessionByUserId(long userId);

    @Query("UPDATE trainingsession SET availableSeat= availableSeat+1 WHERE trainingSessionId= :trainingSessionId")
    void updateAvailableSeats(long trainingSessionId);

    @Query("UPDATE trainingSession SET availableSeat = availableSeat - 1 WHERE trainingSessionId = :traininSessionId")
    void inscriptionTrainingSessions(long traininSessionId);
}
