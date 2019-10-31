package org.diiage.dtrqandroid.data.db.entity;

import java.util.Date;

import androidx.annotation.NonNull;

public class DrivingLessonWithInstructor {
    public long drivingLessonId;

    public Date date;

    public String text;

    public long instructorId;
    public String lastName;
    public String firstName;
    public long userId;

    public DrivingLessonWithInstructor(@NonNull long drivingLessonId, @NonNull Date  date, String text, long instructorId, long userId, String lastName, String firstName)
    {
        this.drivingLessonId = drivingLessonId;
        this.date = date;
        this.text = text;
        this.instructorId = instructorId;
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public long getDrivingLessonId() {
        return drivingLessonId;
    }

    public void setDrivingLessonId(long drivingLessonId) {
        this.drivingLessonId = drivingLessonId;
    }

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

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
