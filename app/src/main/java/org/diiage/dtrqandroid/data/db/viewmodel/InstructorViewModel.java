package org.diiage.dtrqandroid.data.db.viewmodel;

import org.diiage.dtrqandroid.data.db.entity.Instructor;
import org.diiage.dtrqandroid.data.db.repository.InstructorRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class InstructorViewModel extends ViewModel {


    private InstructorRepository instructorRepository;

    public InstructorViewModel (InstructorRepository instructorRepository) {

        this.instructorRepository = instructorRepository;
    }

    LiveData<List<Instructor>> getAllInstructors() { return instructorRepository.getAllAllInstructors();}

    LiveData<Instructor> getInstructorById(Long instructorId) {return instructorRepository.getInstructoById(instructorId);}

    public void insert(Instructor instructor) { instructorRepository.insert(instructor);}
}
