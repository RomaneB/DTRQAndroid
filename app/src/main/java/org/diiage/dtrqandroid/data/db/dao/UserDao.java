package org.diiage.dtrqandroid.data.db.dao;

import org.diiage.dtrqandroid.data.db.entity.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User WHERE  password = '123'")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT COUNT(*) FROM User")
    int getCountUser();

    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    LiveData<User> getUserByUsername(String username, String password);
}
