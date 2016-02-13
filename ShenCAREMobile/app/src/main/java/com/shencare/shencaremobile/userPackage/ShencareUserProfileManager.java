package com.shencare.shencaremobile.userPackage;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/2/11.
 */

public class ShencareUserProfileManager {
    // URL to get contacts JSON of one particular user
    public static String url = "http://shencare.net/api/user/get_userinfobyname/?username=lawrence";

    // JSON Node names
    private static final String TAG_USERNAME = "username";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_SURNAME = "firstname";
    private static final String TAG_LASTNAME = "lastname";
    private static final String TAG_CONTACT = "telephone";

    public static ShencareUser ParseJSON(String json) {
        if (json != null) {
            try {
                ShencareUser su = new ShencareUser();
                JSONObject jsonObjUser = new JSONObject(json);

                String sUsername = jsonObjUser.getString(TAG_USERNAME);
                String sEmail = jsonObjUser.getString(TAG_EMAIL);
                String sSurname = jsonObjUser.getString(TAG_SURNAME);
                String sLastname = jsonObjUser.getString(TAG_LASTNAME);
                String sContact = jsonObjUser.getString(TAG_CONTACT);

                su = new ShencareUser(sUsername, sSurname, sLastname, sEmail, sContact);
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
