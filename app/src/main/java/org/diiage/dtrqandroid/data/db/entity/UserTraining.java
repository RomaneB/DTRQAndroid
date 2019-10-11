package org.diiage.dtrqandroid.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "User_Training",
        indices = {@Index(value = {"idUser", "idTraining"},unique = true)},
        foreignKeys = { @ForeignKey(entity = User.class, parentColumns = "idUser", childColumns ="idUser")}
)
public class UserTraining {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUserTraining")
    public long idUserTraining;

    @ColumnInfo(name = "idUser")
    public long idUser;

    @ColumnInfo(name = "idTraining")
    public long idTraining;

    @ColumnInfo(name = "score")
    public int score;

    public UserTraining(@NonNull long idUserTraining, @NonNull long idUser, @NonNull long idTraining, int score){
        this.idTraining = idTraining;
        this.idUser = idUser;
        this.idUserTraining = idUserTraining;
        this.score = score;
    }

    public long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(long idTraining) {
        this.idTraining = idTraining;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdUserTraining() {
        return idUserTraining;
    }

    public void setIdUserTraining(long idUserTraining) {
        this.idUserTraining = idUserTraining;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
