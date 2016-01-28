package com.shencare.shencaremobile;

import android.os.Bundle;
import android.webkit.WebView;

public class Term_Of_Use extends Navigation_drawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_term__of__use, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Term of Use");
        menuCondition="TermOfUse";

        WebView content = (WebView)findViewById(R.id.termOfUse_content);
        String result = getString(R.string.termOfUse_txt);
        content.loadData(result, "text/html", "utf-8");
        //content.setText(Html.fromHtml(result));
    }
}
