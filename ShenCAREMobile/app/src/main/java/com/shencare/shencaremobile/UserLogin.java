package com.shencare.shencaremobile;

import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends Navigation_drawer implements View.OnClickListener{
    //Nameing the variables
    private TextView signUpLink, changePw;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *  We will not use setContentView in this activty
         *  Rather than we will use layout inflater to add view in FrameLayout of our base activity layout*/

        /**
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.activity_user_login, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Login");
        menuCondition = "UserLogin";



        //*Enable this textview to jump to the Registration Activity
        signUpLink = (TextView) findViewById(R.id.regis_link);
        signUpLink.setOnClickListener(this);
        signUpLink.setPaintFlags(signUpLink.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);


        //*Directly access to the ShenCARE.com's change password webpage.
        changePw = (TextView) findViewById(R.id.forget_pw);
        changePw.setText(Html.fromHtml("<a href=\"http://www.shencare.com/forgot-password\" >Forget Password</a>"));
        changePw.setMovementMethod(LinkMovementMethod.getInstance());

        //Login Button
        loginButton = (Button) findViewById(R.id.log_in_button);
        loginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            // Sign up
            case R.id.regis_link:
                startActivity(new Intent(UserLogin.this, UserRegistration.class));
                break;
            //case R.id.forget_pw:
            case R.id.log_in_button:
                startActivity(new Intent(UserLogin.this, UserProfile.class));
                //*To show users about login successful message after user is validated successfully
                //*Here, the Toast.LENGTH_LONG is the lasting time for the toast box. There is also Toast.LENGTH_SHORT and also can set the time to be 2000ms
                final Toast loginToast = Toast.makeText(getBaseContext(), "Welcome to ShenCARE.", Toast.LENGTH_LONG);
                //*Set the position of the Toast box to the center of the UI
                loginToast.setGravity(Gravity.CENTER, 0, 0);
                loginToast.show();
                new CountDownTimer(4000, 1000)
                {
                    public void onTick(long millisUntilFinished) {loginToast.show();}
                    public void onFinish() {loginToast.cancel();}
                }.start();
                break;
        }
    }


    /* private void setSignUpLink(){
        signUpLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
                startActivity(intent);
            }
        });
    } */
}
