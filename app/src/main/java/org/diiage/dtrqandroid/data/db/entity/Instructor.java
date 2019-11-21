package org.diiage.dtrqandroid.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Instructor")
public class Instructor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "instructorId")
    public long instructorId;

    @ColumnInfo(name = "firstName")
    public String firstName;

    @ColumnInfo(name = "lastName")
    public String lastName;

    public Instructor(@NonNull long instructorId, @NonNull String firstName, @NonNull String lastName)
    {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long idInstructor) {
        this.instructorId = idInstructor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}