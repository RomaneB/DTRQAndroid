package org.diiage.dtrqandroid.data.db.repository;

import org.diiage.dtrqandroid.data.db.dao.UserDao;
import org.diiage.dtrqandroid.data.db.entity.User;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private UserDao userDao;

    @Inject
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    public LiveData<User> getUserByUsername(String username, String password) {return userDao.getUserByUsername(username, password);}

    public void insert(User user) {
        userDao.insert(user);
    }
}
