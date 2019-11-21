package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.TrainingSessionDao;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;

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

    public LiveData<List<TrainingSession>> getAvailableTrainingSessions(long userId){
        return trainingSessionDao.getAvailableTrainingSessions(userId);
    }

    public LiveData<List<TrainingSessionWithUser>> getPastTrainingSessions(long userId){
        return trainingSessionDao.getPastTrainingSessions(userId);
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

    public void inscriptionTrainingSessions(long traininSessionId){
        Executors.newSingleThreadExecutor().execute(() -> trainingSessionDao.inscriptionTrainingSessions(traininSessionId));
    }
}
