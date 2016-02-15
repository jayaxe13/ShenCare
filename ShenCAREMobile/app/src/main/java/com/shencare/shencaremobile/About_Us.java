package com.shencare.shencaremobile;

import android.os.Bundle;
import android.webkit.WebView;

import com.shencare.shencaremobile.Util.SessionManager;

public class About_Us extends Navigation_drawer {
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_about__us, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("About Us");
        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            //User is already logged in. Hide the login button
            menuCondition = "UserLogin";
        }else{
            menuCondition="AboutUs";
        }

        WebView content = (WebView)findViewById(R.id.aboutUs_content);
        //Spanned result = Html.fromHtml(readTxt());
        String result = getString(R.string.aboutUs_txt);
        content.loadData(result, "text/html", "utf-8");
        //content.setText(Html.fromHtml(result));
    }
}
