package com.shencare.shencaremobile;

import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shencare.shencaremobile.Util.SessionManager;


public class UserLogin extends Navigation_drawer implements View.OnClickListener{
    //Nameing the variables
    private TextView signUpLink, changePw;
    private AutoCompleteTextView username;
    private EditText password;
    private Button loginButton;
    private String sUsername, sPassword;
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
        getLayoutInflater().inflate(R.layout.activity_user_login, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Login");
        menuCondition = "UserLogin";


        username = (AutoCompleteTextView)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);


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

        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            //User is already logged in. Take him to home activity
            Intent intent = new Intent(UserLogin.this, Home.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            // When user want to go to the registration page
            case R.id.regis_link:
                startActivity(new Intent(UserLogin.this, UserRegistration.class));
                break;
            //case R.id.forget_pw:
            case R.id.log_in_button:
                //do login checking and store into database here
                if(loginFunction() == true) {
                    //Create login session
                    session.setLogin(true,sUsername);
                    startActivity(new Intent(UserLogin.this, Home.class));
                    //*To show users about login successful message after user is validated successfully
                    //*Here, the Toast.LENGTH_LONG is the lasting time for the toast box. There is also Toast.LENGTH_SHORT and also can set the time to be 2000ms
                    final Toast loginToast = Toast.makeText(getBaseContext(), "Welcome to ShenCARE.", Toast.LENGTH_LONG);
                    //*Set the position of the Toast box to the center of the UI
                    loginToast.setGravity(Gravity.CENTER, 0, 0);
                    loginToast.show();
                    new CountDownTimer(3000, 1000)
                    {
                        public void onTick(long millisUntilFinished) {loginToast.show();}
                        public void onFinish() {loginToast.cancel();}
                    }.start();
                    session.setLogin(true, sUsername);
                    startActivity(new Intent(UserLogin.this, Home.class));
                    finish();
                }else{
                    final Toast loginFailedToast = Toast.makeText(getBaseContext(), "Please try again", Toast.LENGTH_LONG);
                    //*Set the position of the Toast box to the center of the UI
                    loginFailedToast.setGravity(Gravity.CENTER, 0, 0);
                    loginFailedToast.show();
                    new CountDownTimer(3000, 1000)
                    {
                        public void onTick(long millisUntilFinished) {loginFailedToast.show();}
                        public void onFinish() {loginFailedToast.cancel();}
                    }.start();
                }
                break;
        }
    }

    private boolean loginFunction(){
        sUsername = username.getText().toString();
        sPassword = password.getText().toString();
        if(sUsername.equals("Lawrence") && sPassword.equals("isfun00")){
            return true;
        }
        return false;
    }


}
