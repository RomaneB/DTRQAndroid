package org.diiage.dtrqandroid.data.db.entity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "drivingLesson")
public class DrivingLesson {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "drivingLessonId")
    public long drivingLessonId;

    @ColumnInfo(name = "date")
    @TypeConverters({DateTypeConverter.class})
    public Date date;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "instructorId")
    public long instructorId;

    @ColumnInfo(name = "userId")
    public long userId;

    public DrivingLesson(@NonNull long drivingLessonId, @NonNull Date  date, String text, long instructorId, long userId)
    {
        this.drivingLessonId = drivingLessonId;
        this.date = date;
        this.text = text;
        this.instructorId = instructorId;
        this.userId = userId;
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

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
