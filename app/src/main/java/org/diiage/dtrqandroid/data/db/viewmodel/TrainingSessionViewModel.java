package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.entity.TrainingSessionWithUser;
import org.diiage.dtrqandroid.data.db.repository.TrainingSessionRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class TrainingSessionViewModel extends ViewModel {

    private TrainingSessionRepository trainingSessionRepository;

    public TrainingSessionViewModel(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public LiveData<List<TrainingSession>> getAllTrainingSessions() {
        return trainingSessionRepository.getAllTrainingSessions();
    }

    public LiveData<List<TrainingSession>> getAvailableTrainingSessions(long userId){
        return trainingSessionRepository.getAvailableTrainingSessions(userId);
    }

    public LiveData<List<TrainingSessionWithUser>> getPastTrainingSessions(long userId) {
        return trainingSessionRepository.getPastTrainingSessions(userId);
    }

    public void inscriptionTrainingSessions(long traininSessionId){
        trainingSessionRepository.inscriptionTrainingSessions(traininSessionId);
    }

    public void insert(TrainingSession trainingSession) { trainingSessionRepository.insert(trainingSession);}

    public LiveData<List<TrainingSession>> getTrainingSessionsByUserId(long userId) {
        return trainingSessionRepository.getTrainingSessionsByUserId(userId);
    }

    public void updateAvailableSeats(long trainingSessionId){
        trainingSessionRepository.updateAvailableSeats(trainingSessionId);
    }
}
