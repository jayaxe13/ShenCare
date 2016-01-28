package com.shencare.shencaremobile;

import android.os.Bundle;
import android.webkit.WebView;

public class About_Us extends Navigation_drawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_about__us, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("About Us");
        menuCondition="AboutUs";

        WebView content = (WebView)findViewById(R.id.aboutUs_content);
        //Spanned result = Html.fromHtml(readTxt());
        String result = getString(R.string.aboutUs_txt);
        content.loadData(result, "text/html", "utf-8");
        //content.setText(Html.fromHtml(result));
    }
}
