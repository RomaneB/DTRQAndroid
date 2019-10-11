package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.UserTrainingDao;
import org.diiage.dtrqandroid.data.db.entity.UserTraining;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class UserTrainingRepository {

    private UserTrainingDao userTrainingDao;

    @Inject
    public UserTrainingRepository(UserTrainingDao userTrainingDao) {
        this.userTrainingDao = userTrainingDao;
    }

    public LiveData<List<UserTraining>> getAllUserTrainings() {
        return userTrainingDao.getAllUserTrainings();
    }

    public void insert(UserTraining userTraining) {
        userTrainingDao.insert(userTraining);
    }
}
