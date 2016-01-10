package com.shencare.shencaremobile;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener{
    ImageButton volunteerButton;
    TextView aboutUs,termOfUse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        volunteerButton = (ImageButton)findViewById(R.id.volunteerButton);
        volunteerButton.setOnClickListener(this);

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
            //case R.id.calendarButton:
                //startActivity(new Intent(Home.this,Event.class));
               // break;
            //case R.id.services:
                //startActivity(new Intent(Home.this, EldercareServices.class));
                //break;
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
