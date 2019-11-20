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

    public LiveData<List<TrainingSessionWithUser>> getPastTrainingSessions(long userId) {
        return trainingSessionRepository.getPastTrainingSessions(userId);
    }

    public void insert(TrainingSession trainingSession) { trainingSessionRepository.insert(trainingSession);}
}
