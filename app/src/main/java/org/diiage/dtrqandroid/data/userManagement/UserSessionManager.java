package org.diiage.dtrqandroid.data.userManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.diiage.dtrqandroid.MainActivity;
import org.diiage.dtrqandroid.data.view.LoginPage;

import java.util.HashMap;

public class UserSessionManager {

    SharedPreferences pref;

    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Context _context;
    private static final String PREFER_NAME = "DTRQAndroid";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_USERNAME = "username";

    // ID
    public static final String KEY_ID = "id";


    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String name, String id){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_USERNAME, name);

        editor.putString(KEY_ID, id);


        // commit changes
        editor.commit();
    }

    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginPage.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }

    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));

        user.put(KEY_ID, pref.getString(KEY_ID, null));


        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(Activity activity){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, MainActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);


        activity.finish();
    }

    public Long getUserId(){

        HashMap<String, String> user = getUserDetails();
        Long idUser = Long.parseLong(user.get(UserSessionManager.KEY_ID));
        return idUser;
    }
}
