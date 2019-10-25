package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.repository.DrivingLessonRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DrivingLessonViewModel extends ViewModel {

    private DrivingLessonRepository drivingLessonRepository;

    public DrivingLessonViewModel (DrivingLessonRepository drivingLessonRepository) {

        this.drivingLessonRepository = drivingLessonRepository;
    }

    public LiveData<List<DrivingLesson>> getAllDrivingLessons() { return drivingLessonRepository.getAllDrivingLessons();}
    public LiveData<List<DrivingLesson>> getAvailableDrivingLessons(Long idUser) { return drivingLessonRepository.getAvailableDrivingLessons(idUser);}
    public LiveData<List<DrivingLesson>> getMyDrivingLessons(long idUser) { return drivingLessonRepository.getMyDrivingLessons(idUser);}

    public void insert(DrivingLesson drivingLesson) { drivingLessonRepository.insert(drivingLesson);}
}
