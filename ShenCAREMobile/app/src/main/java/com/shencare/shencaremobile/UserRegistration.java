package com.shencare.shencaremobile;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener {
    private TextView regis_name, regis_surname, regis_username,
            regis_pw,regis_cp,regis_email, termOfUseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        regis_name =(TextView)findViewById(R.id.regis_name);
        regis_surname = (TextView)findViewById(R.id.regis_surname);
        regis_username = (TextView)findViewById(R.id.regis_password);
        regis_cp = (TextView)findViewById(R.id.regis_confirmed_password);
        regis_email = (TextView)findViewById(R.id.regis_email);
        termOfUseView = (TextView)findViewById(R.id.regis_term);
        termOfUseView.setOnClickListener(this);
        termOfUseView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            // Sign up
            case R.id.regis_term:
                startActivity(new Intent(UserRegistration.this, Term_Of_Use.class));
                break;


        }
    }


}
