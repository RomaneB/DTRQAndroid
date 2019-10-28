package org.diiage.dtrqandroid.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "User_Training",
        indices = {@Index(value = {"userId", "trainingId"},unique = true)},
        foreignKeys = { @ForeignKey(entity = User.class, parentColumns = "userId", childColumns ="userId")}
)
public class UserTraining {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userTrainingId")
    public long userTrainingId;

    @ColumnInfo(name = "userId")
    public long userId;

    @ColumnInfo(name = "trainingId")
    public long trainingId;

    @ColumnInfo(name = "score")
    public int score;

    public UserTraining(@NonNull long userTrainingId, @NonNull long userId, @NonNull long trainingId, int score){
        this.trainingId = trainingId;
        this.userId = userId;
        this.userTrainingId = userTrainingId;
        this.score = score;
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserID(long userId) {
        this.userId = userId;
    }

    public long getUserTrainingId() {
        return userTrainingId;
    }

    public void setUserTrainingId(long idUserTraining) {
        this.userTrainingId = userTrainingId;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
