package com.shencare.shencaremobile;

import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener {
    private EditText regis_name, regis_surname, regis_username,
            regis_pw,regis_cp,regis_email;
    private TextView termOfUseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        regis_name =(EditText)findViewById(R.id.regis_name);
        regis_surname = (EditText)findViewById(R.id.regis_surname);
        regis_username = (EditText)findViewById(R.id.regis_password);
        regis_cp = (EditText)findViewById(R.id.regis_confirmed_password);
        regis_email = (EditText)findViewById(R.id.regis_email);

        termOfUseView = (TextView)findViewById(R.id.regis_term);
        termOfUseView.setOnClickListener(this);
        termOfUseView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            // term of use
            case R.id.regis_term:
                startActivity(new Intent(UserRegistration.this, Term_Of_Use.class));
                break;
            //Register
            case R.id.registration_button:
                startActivity(new Intent(this,UserLogin.class));
                final Toast registrationToast = Toast.makeText(getBaseContext(), "Registered Successfully.", Toast.LENGTH_LONG);
                //*Set the position of the Toast box to the center of the UI
                registrationToast.setGravity(Gravity.CENTER, 0, 0);
                registrationToast.show();
                new CountDownTimer(4000, 1000)
                {
                    public void onTick(long millisUntilFinished) {registrationToast.show();}
                    public void onFinish() {registrationToast.cancel();}
                }.start();
                break;
        }
    }


}
