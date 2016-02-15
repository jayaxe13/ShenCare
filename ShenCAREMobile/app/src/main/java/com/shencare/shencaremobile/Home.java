package com.shencare.shencaremobile;


import android.content.Intent;
import android.view.View;


import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shencare.shencaremobile.Util.SessionManager;

public class Home extends Navigation_drawer implements View.OnClickListener{
    private ImageButton volunteerButton, serviceButton, eventButton, shopButton;
    private TextView aboutUs,termOfUse;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *  We will not use setContentView in this activty
         *  Rather than we will use layout inflater to add view in FrameLayout of our base activity layout*/

        /**
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.activity_home, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Shencare");

        //Navigation_drawer.setTitle(getTitle().toString());

        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            //User is already logged in. Hide the login button
            menuCondition = "UserLogin";
        }else{
            menuCondition = "Home";
        }
        volunteerButton = (ImageButton)findViewById(R.id.volunteerButton);
        volunteerButton.setOnClickListener(this);
        serviceButton = (ImageButton) findViewById(R.id.servicesButton);
        serviceButton.setOnClickListener(this);
        eventButton = (ImageButton) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(this);

        aboutUs = (TextView)findViewById(R.id.aboutUs_text_button);
        termOfUse = (TextView)findViewById(R.id.termOfUse_text_button);

        aboutUs.setOnClickListener(this);
        termOfUse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            //main 4 buttons
            case R.id.eventButton:
                startActivity(new Intent(Home.this,Events.class));
                break;
            case R.id.servicesButton:
                startActivity(new Intent(Home.this, ServiceDetails.class));
                break;
           // case R.id.shopButton:
               // startActivity(new Intent(Home.this, ShoppingActivity.class));
               // break;
            case R.id.volunteerButton:
                startActivity(new Intent(Home.this, VolunteerSignUp.class));
                break;

            // about Us
            case R.id.aboutUs_text_button:
                startActivity(new Intent(Home.this, About_Us.class));
                break;
            //term of use
            case R.id.termOfUse_text_button:
                startActivity(new Intent(Home.this, Term_Of_Use.class));
                break;
        }

    }
}
