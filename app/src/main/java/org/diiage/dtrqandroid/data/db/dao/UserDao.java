package org.diiage.dtrqandroid.data.db.dao;

import org.diiage.dtrqandroid.data.db.entity.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT COUNT(*) FROM User")
    int getCountUser();

    @Insert
    void insert(User user);
}
