package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.repository.DrivingLessonRepository;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DrivingLessonViewModel extends ViewModel {

    private DrivingLesson drivingLesson;
    private Date date;
    private String text;

    private DrivingLessonRepository drivingLessonRepository;
    public DrivingLessonViewModel () {
}

    public DrivingLessonViewModel (DrivingLessonRepository drivingLessonRepository) {

        this.drivingLessonRepository = drivingLessonRepository;
    }

    public LiveData<List<DrivingLesson>> getAllDrivingLessons() { return drivingLessonRepository.getAllDrivingLessons();}
    public LiveData<List<DrivingLesson>> getAvailableDrivingLessons(Long userId) { return drivingLessonRepository.getAvailableDrivingLessons(userId);}
    public LiveData<List<DrivingLesson>> getMyDrivingLessons(long userId) {
        return drivingLessonRepository.getMyDrivingLessons(userId);
    }
    public void registrer(Long userId, Long drivingLessonId) {
        drivingLessonRepository.registrer(userId, drivingLessonId);
    }
    public void insert(DrivingLesson drivingLesson) { drivingLessonRepository.insert(drivingLesson);}


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
