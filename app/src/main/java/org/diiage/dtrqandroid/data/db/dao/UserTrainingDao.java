package org.diiage.dtrqandroid.data.db.dao;

import org.diiage.dtrqandroid.data.db.entity.UserTraining;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserTrainingDao {
    @Query("SELECT * FROM User_Training")
    LiveData<List<UserTraining>> getAllUserTrainings();

    @Insert
    void insert(UserTraining userTraining);

    @Query("DELETE FROM USER_TRAINING WHERE trainingId=:trainingSessionId AND userId=:userId")
    void unregister(long trainingSessionId, long userId);
}
