package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.entity.TrainingSession;
import org.diiage.dtrqandroid.data.db.repository.TrainingSessionRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TrainingSesssionViewModel extends ViewModel {

    private TrainingSessionRepository trainingSessionRepository;

    public TrainingSesssionViewModel (TrainingSessionRepository trainingSessionRepository) {

        this.trainingSessionRepository = trainingSessionRepository;
    }

    LiveData<List<TrainingSession>> getAllTrainingSessions() { return trainingSessionRepository.getAllTrainingSessions();}

    public void insert(TrainingSession trainingSession) { trainingSessionRepository.insert(trainingSession);}
}
