package org.diiage.dtrqandroid.data.db.entity;

import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.diiage.dtrqandroid.data.converters.DateTypeConverter;

import javax.inject.Inject;

@Entity(tableName = "TrainingSession")
public class TrainingSession {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trainingSessionId")
    public long trainingSessionId;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "availableSeat")
    public int availableSeat;

    public TrainingSession(@NonNull long trainingSessionId, @NonNull Date date, @NonNull int availableSeat){
        this.trainingSessionId = trainingSessionId;
        this.availableSeat = availableSeat;
        this.date = date;
    }

    public long getTrainingSessionId() {
        return trainingSessionId;
    }

    public void setTrainingSessionId(long trainingSessionId) {
        this.trainingSessionId = trainingSessionId;
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
