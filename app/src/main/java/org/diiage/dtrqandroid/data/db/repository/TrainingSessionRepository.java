package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.TrainingSessionDao;
import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;

import java.util.List;

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

    public LiveData<List<TrainingSession>> getAvailableTrainingSessions(){
        return trainingSessionDao.getAvailableTrainingSessions();
    }

    public LiveData<List<TrainingSessionWithUser>> getPastTrainingSessions(long userId){
        return trainingSessionDao.getPastTrainingSessions(userId);
    }

    public void insert(TrainingSession trainingSession) {
        trainingSessionDao.insert(trainingSession);
    }
}
