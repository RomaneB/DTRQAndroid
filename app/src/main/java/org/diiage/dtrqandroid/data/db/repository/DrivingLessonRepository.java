package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.DrivingLessonDao;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.entity.DrivingLessonWithInstructor;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class DrivingLessonRepository {

    private DrivingLessonDao drivingLessonDao;
    @Inject
    public DrivingLessonRepository(DrivingLessonDao drivingLessonDao) {
        this.drivingLessonDao = drivingLessonDao;
    }

    public LiveData<List<DrivingLesson>> getAllDrivingLessons() {
        return drivingLessonDao.getAllDrivingLessons();
    }

    public LiveData<List<DrivingLessonWithInstructor>> getAvailableDrivingLessons(Long userId) {
        return drivingLessonDao.getAvailableDrivingLessons(userId);
    }

    public LiveData<List<DrivingLessonWithInstructor>> getMyDrivingLessons(long userId) {
        return drivingLessonDao.getMyDrivingLessons(userId);
    }

    public LiveData<DrivingLesson> getDrivingLessonById(long drivingLessonId) {
        return drivingLessonDao.getDrivingLessonById(drivingLessonId);
    }

    public void registrer(Long userId, Long drivingLessonId) {
        Executors.newSingleThreadExecutor().execute(() -> drivingLessonDao.registrer(userId, drivingLessonId));
    }

    public void insert(DrivingLesson drivingLesson) {
        drivingLessonDao.insert(drivingLesson);
    }
}
