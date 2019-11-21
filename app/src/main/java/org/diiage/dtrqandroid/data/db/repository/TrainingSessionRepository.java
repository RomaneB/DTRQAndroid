package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.TrainingSessionDao;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class TrainingSessionRepository {

    private TrainingSessionDao trainingSessionDao;

    @Inject
    public TrainingSessionRepository(TrainingSessionDao trainingSessionDao) {
        this.trainingSessionDao = trainingSessionDao;
    }

    public LiveData<List<TrainingSession>> getAllTrainingSessions() {
        return trainingSessionDao.getAllTrainingSession();
    }

    public void insert(TrainingSession trainingSession) {
        trainingSessionDao.insert(trainingSession);
    }

    public LiveData<List<TrainingSession>> getTrainingSessionsByUserId(long userId){
        return trainingSessionDao.getTrainingSessionByUserId(userId);
    }

    public void updateAvailableSeats(long trainingSessionId){
        Executors.newSingleThreadExecutor().execute(() -> trainingSessionDao.updateAvailableSeats(trainingSessionId));
    }
}
