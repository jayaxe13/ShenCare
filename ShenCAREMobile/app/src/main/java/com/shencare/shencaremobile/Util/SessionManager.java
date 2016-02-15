package com.shencare.shencaremobile.Util;

/**
 * Created by Administrator on 2016/2/13.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.shencare.shencaremobile.Home;

public class SessionManager {
    //LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();
    //Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    //Shared pref mode
    int PRIVATE_MODE =0;

    //Shared preferences file name
    private static final String PREF_NAME = "ShencareProject";

    //All Shared Preferences Keys
    private static final String KEY_IS_LOGGEDIN ="isLoggedIn";

    //User name (make variable public to access from outside)
    public static final String KEY_USERNAME = "username";
    //public static final String KEY_COOKIE="cookie";

    //Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String username){
        //Storing login value as TRUE
        editor.putBoolean(KEY_IS_LOGGEDIN,isLoggedIn);

        //Storing username in pref
        editor.putString(KEY_USERNAME,username);
        //editor.putString(KEY_COOKIE,cookie)
        //commit changes
        editor.commit();

        Log.d(TAG,"User login session modified!");
    }

    /**
     * Quick check for login
     * **/
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }


    /**
     * Get stored session data
     * */
    public String getUserDetails(){
        return pref.getString(KEY_USERNAME,null);
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Home.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }
}
