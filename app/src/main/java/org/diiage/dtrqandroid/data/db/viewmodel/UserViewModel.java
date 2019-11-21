package org.diiage.dtrqandroid.data.db.viewmodel;

import android.app.Application;

import org.diiage.dtrqandroid.data.db.entity.User;
import org.diiage.dtrqandroid.data.db.repository.UserRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;

    public UserViewModel (UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public LiveData<List<User>> getAllUsers() { return userRepository.getAllUsers();}
    public LiveData<User> getUserByUsername(String username, String password) { return userRepository.getUserByUsername(username, password);}

    public void insert(User user) { userRepository.insert(user);}
}
