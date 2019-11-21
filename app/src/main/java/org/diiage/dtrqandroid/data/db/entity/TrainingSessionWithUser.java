package org.diiage.dtrqandroid.data.db.entity;

import java.util.Date;

import androidx.annotation.NonNull;

public class TrainingSessionWithUser {
    public long trainingSessionId;
    public Date date;
    public int availableSeat;
    public long userTrainingId;
    public long userId;
    public long trainingId;
    public int score;

    public TrainingSessionWithUser(@NonNull long trainingSessionId, @NonNull Date date, int availableSeat, long userTrainingId, long userId, long trainingId, int score){
        this.trainingSessionId = trainingSessionId;
        this.date = date;
        this.availableSeat = availableSeat;
        this.userTrainingId = userTrainingId;
        this.userId = userId;
        this.trainingId = trainingId;
        this.score = score;
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

    public long getUserTrainingId() {
        return userTrainingId;
    }

    public void setUserTrainingId(long userTrainingId) {
        this.userTrainingId = userTrainingId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
