package org.diiage.dtrqandroid.data.db.entity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "TrainingSession")
public class TrainingSession {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTrainingSession")
    public long idTrainingSession;

    @ColumnInfo(name = "date")
    @TypeConverters({DateTypeConverter.class})
    public Date date;

    @ColumnInfo(name = "availableSeat")
    public int availableSeat;

    public TrainingSession(@NonNull long idTrainingSession, @NonNull Date date, @NonNull int availableSeat){
        this.idTrainingSession = idTrainingSession;
        this.availableSeat = availableSeat;
        this.date = date;
    }

    public long getIdTrainingSession() {
        return idTrainingSession;
    }

    public void setIdTrainingSession(long idTrainingSession) {
        this.idTrainingSession = idTrainingSession;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }
}
