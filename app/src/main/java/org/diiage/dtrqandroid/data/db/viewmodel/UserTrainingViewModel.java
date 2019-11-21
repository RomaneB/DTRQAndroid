package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.entity.UserTraining;
import org.diiage.dtrqandroid.data.db.repository.UserTrainingRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserTrainingViewModel extends ViewModel {


    private UserTrainingRepository userTrainingRepository;

    public UserTrainingViewModel (UserTrainingRepository userTrainingRepository) {

        this.userTrainingRepository = userTrainingRepository;
    }

    LiveData<List<UserTraining>> getAllUserTrainings() { return userTrainingRepository.getAllUserTrainings();}

    public void insert(UserTraining userTraining) { userTrainingRepository.insert(userTraining);}


    public void unregister(long trainingSessionId, long userId){
        userTrainingRepository.unregister(trainingSessionId, userId);
    }
}
