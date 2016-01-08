package com.shencare.shencaremobile;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener{
    TextView aboutUs,termOfUse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        aboutUs = (TextView)findViewById(R.id.aboutUs_text_button);
        termOfUse = (TextView)findViewById(R.id.termOfUse_text_button);

        aboutUs.setOnClickListener(this);
        termOfUse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            // about Us
            case R.id.aboutUs_text_button:
                startActivity(new Intent(Home.this, About_Us.class));
                break;
            case R.id.termOfUse_text_button:
                startActivity(new Intent(Home.this, Term_Of_Use.class));
                break;
        }

    }
}
