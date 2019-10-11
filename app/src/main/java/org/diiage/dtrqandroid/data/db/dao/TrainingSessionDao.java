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
}
