package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.repository.DrivingLessonRepository;
import org.diiage.dtrqandroid.data.db.repository.InstructorRepository;
import org.diiage.dtrqandroid.data.db.repository.TrainingSessionRepository;
import org.diiage.dtrqandroid.data.db.repository.UserRepository;
import org.diiage.dtrqandroid.data.db.repository.UserTrainingRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private final DrivingLessonRepository drivingLessonRepository;
    private final InstructorRepository instructorRepository;
    private final TrainingSessionRepository trainingSessionRepository;
    private final UserRepository userRepository;
    private final UserTrainingRepository userTrainingRepository;

    public CustomViewModelFactory(DrivingLessonRepository drivingLessonRepository,
                                  InstructorRepository instructorRepository,
                                  TrainingSessionRepository trainingSessionRepository,
                                  UserRepository userRepository,
                                  UserTrainingRepository userTrainingRepository)
    {
        this.drivingLessonRepository = drivingLessonRepository;
        this.instructorRepository = instructorRepository;
        this.trainingSessionRepository = trainingSessionRepository;
        this.userRepository = userRepository;
        this.userTrainingRepository = userTrainingRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DrivingLessonViewModel.class))
            return (T) new DrivingLessonViewModel(drivingLessonRepository);
        else  if (modelClass.isAssignableFrom(TrainingSesssionViewModel.class))
            return (T) new TrainingSesssionViewModel(trainingSessionRepository);
        else  if (modelClass.isAssignableFrom(InstructorViewModel.class))
            return (T) new InstructorViewModel(instructorRepository);
        else  if (modelClass.isAssignableFrom(UserTrainingViewModel.class))
            return (T) new UserTrainingViewModel(userTrainingRepository);
        else  if (modelClass.isAssignableFrom(UserViewModel.class))
            return (T) new UserViewModel(userRepository);
        else {
            throw new IllegalArgumentException("ViewModel not found");
        }
    }
}
