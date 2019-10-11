package org.diiage.dtrqandroid.data.db.dao;


import org.diiage.dtrqandroid.data.db.entity.Instructor;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface InstructorDao {

    @Query("SELECT * FROM instructor")
    LiveData<List<Instructor>> getAllInstructors();

    @Insert
    void insert(Instructor instructor);
}
