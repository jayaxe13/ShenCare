package com.shencare.shencaremobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shencare.shencaremobile.Util.SessionManager;
import com.shencare.shencaremobile.Util.WebRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class UserLogin extends Navigation_drawer implements View.OnClickListener{
    //Nameing the variables
    private TextView signUpLink, changePw;
    private AutoCompleteTextView username;
    private EditText password;
    private Button loginButton;
    private String a,b,c,d,sUsername, sPassword, authCookie, loginStatus, authUsername,authPassword, userid, processId, aUsername,aCookie,aUserId;
    private SessionManager session, loginSession;
    private boolean loginSuccess, log;

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
                //loginFunction();
                new getAuth().execute();
                //loginFunction();
                //session.setLogin(log,aUsername,aCookie,aUserId);
                //Log.v("session login: ", "> " + session.isLoggedIn());//null here
               // Log.v("session login2: ", "> " + log);//null here
                //if(session.isLoggedIn()==true) {
                    //Create login session
                    //session.setLogin(true, authUsername, authCookie, processId);
                    //Log.d("session id : ", ">" + session.getUserDetails());
                    //Log.d("session USER ID : ", ">" + session.getUserId());
                    //*To show users about login successful message after user is validated successfully
                    //*Here, the Toast.LENGTH_LONG is the lasting time for the toast box. There is also Toast.LENGTH_SHORT and also can set the time to be 2000ms
                    /**final Toast loginToast = Toast.makeText(getBaseContext(), "Welcome to ShenCARE."+ " "+session.getUserDetails(), Toast.LENGTH_LONG);
                    //*Set the position of the Toast box to the center of the UI
                    loginToast.setGravity(Gravity.CENTER, 0, 0);
                    loginToast.show();
                    new CountDownTimer(3000, 1000)
                    {
                        public void onTick(long millisUntilFinished) {loginToast.show();}
                        public void onFinish() {loginToast.cancel();}
                    }.start();
                    //session.setLogin(true, authUsername, authCookie,userid);
                    //startActivity(new Intent(UserLogin.this, Home.class));
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    finish();*/
                //}else{
                //}
                break;
        }
    }

    private void loginFunction(Boolean a, String b, String c, String d){
        //authUsername = username.getText().toString();
        //authPassword = password.getText().toString();
       // if(sUsername.equals("Lawrence") && sPassword.equals("isfun00")){
       //     return true;
       // }
       // return false;
        session.setLogin(a,b,c,d);

    }

    public class getAuth extends AsyncTask<Void,Void,SessionManager>{

        ProgressDialog proDialog;
        String authUsername = username.getText().toString();
        String authPassword = password.getText().toString();
        //SessionManager logSession = new SessionManager(getApplicationContext());

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            // Showing progress loading dialog
            proDialog = new ProgressDialog(UserLogin.this);
            proDialog.setMessage("Loading...");
            proDialog.setCancelable(false);
            proDialog.show();
        }

        @Override
        protected SessionManager doInBackground(Void... arg0){
            WebRequest webreqGetAuth = new WebRequest();


            String jsonStr = webreqGetAuth.makeWebServiceCall
                    ("http://shencare.net/api/user/generate_auth_cookie/?username="+authUsername+"&password="+authPassword+"", WebRequest.GETRequest);

            Log.d("Response: ", "> " + jsonStr);//null

            final String TAG_STATUS = "status";
            final String TAG_COOKIE = "cookie";
            final String TAG_USERID = "id";

            if (jsonStr != null) {
                try {
                    JSONObject jsonAuth = new JSONObject(jsonStr);
                    JSONObject nestedJson = jsonAuth.getJSONObject("user");

                    loginStatus = jsonAuth.getString(TAG_STATUS);
                    authCookie = jsonAuth.getString(TAG_COOKIE);
                    userid = Integer.toString(nestedJson.getInt(TAG_USERID));

                    if(loginStatus.equalsIgnoreCase("ok")){
                        loginSuccess = true;
                        processId=userid;
                    }else{
                        loginSuccess = false;
                        promptWrongUserInfo();
                    }
                    //Log.d("login status: ", "> " + loginStatus);
                    //Log.d("user id from json: ", "> " + userid);
                    session.setLogin(loginSuccess, authUsername, authCookie, userid);
                    return session;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                Log.e("ServiceHandler", "No data received from HTTP request");
                return null;
            }

            // return null;
        }
        @Override
        protected void onPostExecute(SessionManager requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();

            //log = loginSuccess;
            //aUsername = authUsername;
            //aCookie = authCookie;
            //aUserId = processId;
            //session = logSession;
            //session.setLogin(log, aUsername, aCookie, aUserId);
            //Log.d("on post result: ", "> " + log + " " + aUsername + " " + aUserId);
           //loginFunction(log,aUsername,aCookie,aUserId);
            if(session.isLoggedIn()==true){
                promptSuccessLogin();
            }else{
                promptWrongUserInfo();
            }

        }
    }

    public void promptWrongUserInfo(){
        final Toast loginFailedToast = Toast.makeText(getBaseContext(), "Please try again", Toast.LENGTH_LONG);
        //*Set the position of the Toast box to the center of the UI
        loginFailedToast.setGravity(Gravity.CENTER, 0, 0);
        loginFailedToast.show();
        new CountDownTimer(2000, 1000)
        {
            public void onTick(long millisUntilFinished) {loginFailedToast.show();}
            public void onFinish() {loginFailedToast.cancel();}
        }.start();
    }

    public void promptSuccessLogin(){
        final Toast loginToast = Toast.makeText(getBaseContext(), "Welcome to ShenCARE."+ " "+session.getUserDetails(), Toast.LENGTH_LONG);
        //*Set the position of the Toast box to the center of the UI
        loginToast.setGravity(Gravity.CENTER, 0, 0);
        loginToast.show();
        new CountDownTimer(3000, 1000)
        {
            public void onTick(long millisUntilFinished) {loginToast.show();}
            public void onFinish() {loginToast.cancel();}
        }.start();
        //session.setLogin(true, authUsername, authCookie,userid);
        //startActivity(new Intent(UserLogin.this, Home.class));
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
    }
}
