package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.DrivingLessonDao;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;

import java.util.List;

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

    public void insert(DrivingLesson drivingLesson) {
        drivingLessonDao.insert(drivingLesson);
    }
}
