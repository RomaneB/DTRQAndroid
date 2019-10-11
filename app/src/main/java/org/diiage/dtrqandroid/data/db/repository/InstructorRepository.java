package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.InstructorDao;
import org.diiage.dtrqandroid.data.db.entity.Instructor;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class InstructorRepository {
    private InstructorDao instructorDao;

    @Inject
    public InstructorRepository(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    public LiveData<List<Instructor>> getAllAllInstructors() {
        return instructorDao.getAllInstructors();
    }

    public void insert(Instructor instructor) {
        instructorDao.insert(instructor);
    }
}
