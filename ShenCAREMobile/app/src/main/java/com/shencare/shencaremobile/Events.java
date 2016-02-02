package com.shencare.shencaremobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Events extends Navigation_drawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_events, frameLayout);

        /**
         * Setting title and itemChecked
         */
        //mDrawerList.setItemChecked(position, true);
        setTitle("Senior Living");
        menuCondition="ElderlyEvents";
    }
}
