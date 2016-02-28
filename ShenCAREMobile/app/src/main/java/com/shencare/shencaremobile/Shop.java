package com.shencare.shencaremobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Shop extends Navigation_drawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shop);

        getLayoutInflater().inflate(R.layout.activity_shop, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Eldercare Shop");
        menuCondition = "EldercareShop";
    }
}
