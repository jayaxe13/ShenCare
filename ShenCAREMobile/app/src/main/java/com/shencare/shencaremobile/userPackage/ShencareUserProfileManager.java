package com.shencare.shencaremobile.UserPackage;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/2/11.
 */

public class ShencareUserProfileManager {
    // URL to get contacts JSON of one particular user
    public static String url = "http://shencare.net/api/user/get_userinfobyname/?username=";

    // JSON Node names
    private static final String TAG_USERNAME = "username";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_NAME = "firstname";
    private static final String TAG_SURNAME = "lastname";
    private static final String TAG_CONTACT = "telephone";
    private static final String TAG_POT = "pref_ot";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_PASSWORD = "password";

    public static ShencareUser ParseJSON(String json) {
        if (json != null) {
            try {
                ShencareUser su = new ShencareUser();
                JSONObject jsonObjUser = new JSONObject(json);

                String sUsername = jsonObjUser.getString(TAG_USERNAME);
                String sEmail = jsonObjUser.getString(TAG_EMAIL);
                String sName = jsonObjUser.getString(TAG_NAME);
                String sLastname = jsonObjUser.getString(TAG_SURNAME);
                String sContact = jsonObjUser.getString(TAG_CONTACT);
                String sPOT = jsonObjUser.getString(TAG_POT);
                String sLocation = jsonObjUser.getString(TAG_LOCATION);
                String sPassword = jsonObjUser.getString(TAG_PASSWORD);

                su = new ShencareUser(sUsername, sName, sLastname, sEmail,sPassword, sContact, sPOT,sLocation);
                return su;
            } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    } else {
        Log.e("ServiceHandler", "No data received from HTTP request");
        return null;
    }
}


}
