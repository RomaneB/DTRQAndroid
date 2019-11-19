package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;
import org.diiage.dtrqandroid.data.db.entity.User;
import org.diiage.dtrqandroid.data.db.repository.DrivingLessonRepository;
import org.diiage.dtrqandroid.data.db.repository.UserRepository;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class DrivingLessonViewModel extends ViewModel {

    //private DrivingLesson drivingLesson;


    private DrivingLessonRepository drivingLessonRepository;
    private UserRepository userRepository;
    //private LiveData<String> userName;
    //private LiveData<User> user;

    private LiveData<Long> drivingLessonId;
    private LiveData<Date> dateDrivingLesson;
    private LiveData<String> descriptionDrivingLesson;
    private LiveData<DrivingLesson> drivingLesson;
    private LiveData<DrivingLessonWithInstructor> drivingLessonWithInstructor;
    //private MutableLiveData<Long> drivingLessonId = new MutableLiveData<>();
private MutableLiveData<Long> userId = new MutableLiveData<>();

    public DrivingLessonViewModel () {
}

    public DrivingLessonViewModel (DrivingLessonRepository drivingLessonRepository) {
        //this.userRepository = userRepository;
        this.drivingLessonRepository = drivingLessonRepository;

        drivingLessonId = Transformations.map(drivingLessonWithInstructor, DrivingLessonWithInstructor::getDrivingLessonId);
        dateDrivingLesson = Transformations.map(drivingLessonWithInstructor, DrivingLessonWithInstructor::getDate);
        descriptionDrivingLesson = Transformations.map(drivingLessonWithInstructor, DrivingLessonWithInstructor::getText);
        //drivingLessonWithInstructor = Transformations.switchMap(drivingLessonWithInstructor, DrivingLessonRepository::getDrivingLessonId);

        //userName = Transformations.map(user,User::getUsername);
        //user = Transformations.switchMap(userId,userRepository::getById);
    }

    public LiveData<List<DrivingLessonWithInstructor>> getPastDrivingLessons(long userId) { return drivingLessonRepository.getPastDrivingLessons(userId);}
    public LiveData<List<DrivingLesson>> getAllDrivingLessons() { return drivingLessonRepository.getAllDrivingLessons();}
    public LiveData<List<DrivingLessonWithInstructor>> getAvailableDrivingLessons(Long userId) { return drivingLessonRepository.getAvailableDrivingLessons(userId);}
    public LiveData<List<DrivingLessonWithInstructor>> getMyDrivingLessons(long userId) {
        return drivingLessonRepository.getMyDrivingLessons(userId);
    }
    public LiveData<DrivingLesson> getDrivingLessonsById(long drivingLessonId) {
        return drivingLessonRepository.getDrivingLessonById(drivingLessonId);
    }
    public void registrer(Long userId, Long drivingLessonId) {
        drivingLessonRepository.registrer(userId, drivingLessonId);
    }
    public void insert(DrivingLesson drivingLesson) { drivingLessonRepository.insert(drivingLesson);}

    public void setUserId(Long userId) {
        this.userId.setValue(userId);
    }

    public LiveData<Date> getDate() {
        return dateDrivingLesson;
    }

    public LiveData<String> getDescription(){
        return descriptionDrivingLesson;
    }
    public LiveData<Long> getDrivingLessonId(){
        return drivingLessonId;
    }
}
