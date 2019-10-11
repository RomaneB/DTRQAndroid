package org.diiage.dtrqandroid.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Instructor")
public class Instructor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long idInstructor;

    @ColumnInfo(name = "firstName")
    public String firstName;

    @ColumnInfo(name = "lastName")
    public String lastName;

    public Instructor(@NonNull long idInstructor, @NonNull String firstName, @NonNull String lastName)
    {
        this.idInstructor = idInstructor;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public long getidInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(long idInstructor) {
        this.idInstructor = idInstructor;
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