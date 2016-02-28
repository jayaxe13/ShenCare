package com.shencare.shencaremobile;

import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.shencare.shencaremobile.Util.SessionManager;
import com.shencare.shencaremobile.Util.WebRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRegistration extends Navigation_drawer implements View.OnClickListener {
    private EditText regis_name, regis_surname, regis_username,
            regis_pw,regis_cp,regis_email, regis_telephone;
    private Button regis_submit;
    private Spinner regis_pot, regis_mpl;
    private String selected_pot, selected_mpl, new_name, new_surname,
            new_username, new_pw, new_email, display_name, urlx, pref_ot, location, telephone, pref, loc, x, y, nonce, user_id;
    private TextView termOfUseView,regis_pot_text, regis_mpl_text;
    private CheckBox checkAgree;
    private SessionManager session;
    private int temp1, temp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_user_registration, frameLayout);

        /**
         * Setting title and itemChecked
         */
        //mDrawerList.setItemChecked(position, true);
        setTitle("Registration");
        menuCondition="UserRegistration";

        regis_name =(EditText)findViewById(R.id.regis_name);
        regis_surname = (EditText)findViewById(R.id.regis_surname);
        regis_username = (EditText)findViewById(R.id.regis_username);
        regis_pw = (EditText) findViewById(R.id.regis_password);
        regis_cp = (EditText)findViewById(R.id.regis_confirmed_password);
        regis_email = (EditText)findViewById(R.id.regis_email);
        regis_telephone = (EditText)findViewById(R.id.regis_telephone);

        regis_pot = (Spinner) findViewById(R.id.regis_pot);
        regis_mpl = (Spinner) findViewById(R.id.regis_mpl);

        checkAgree = (CheckBox) findViewById(R.id.chk_agree);
        regis_pot_text =(TextView)findViewById(R.id.regis_pot_text);
        regis_mpl_text = (TextView)findViewById(R.id.regis_mpl_text);

        termOfUseView = (TextView)findViewById(R.id.regis_term);
        termOfUseView.setOnClickListener(this);
        termOfUseView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        regis_submit = (Button) findViewById(R.id.registration_button);
        regis_submit.setOnClickListener(this);
        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            //User is already logged in. Take him to home activity
            Intent intent = new Intent(UserRegistration.this, Home.class);
            startActivity(intent);
            finish();
        }

        doubleCheck();
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
                          /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                * */
                if(checkValidation()){
                    new GetNonce().execute();
                    getRegistrationFields();
                    new PostRegistration().execute();
                    submitRegisForm();
                }else{
                    final Toast submitRegisForm = Toast.makeText(getBaseContext(), R.string.form_contains_err,Toast.LENGTH_LONG);
                    submitRegisForm.setGravity(Gravity.CENTER, 0, 0);
                    submitRegisForm.show();
                    new CountDownTimer(3000, 1000) {
                        public void onTick(long millisUntilFinished) {submitRegisForm.show();}
                        public void onFinish() {submitRegisForm.cancel();}
                    }.start();
                }
                break;
        }
    }

    private boolean checkValidation(){
        boolean check = true;
        if(!Validation.isGeneralName(regis_name,true)) check = false;
        if(!Validation.isGeneralName(regis_surname,true)) check =false;
        if(!Validation.isUsername(regis_username, true)) check = false;
        if(!Validation.isPassword(regis_pw, true)) check = false;
        if(!Validation.isValidConfirmedPassword(regis_cp, regis_pw.getText().toString().trim(), true)) check = false;
        if(!Validation.isEmailAddress(regis_email, true)) check = false;
        if(!validateDropDown(regis_pot, selected_pot, regis_pot_text)) check = false;
        if(!validateDropDown(regis_mpl, selected_mpl, regis_mpl_text)) check = false;
        if(!isAgreementChecked()) check = false;
        if(!Validation.isPhone(regis_telephone,true)) check=false;

        return check;
    }

    private void submitRegisForm(){
        //Submit your form here if your form is valid
        final Toast registrationToast = Toast.makeText(getBaseContext(), "Registered Successfully.", Toast.LENGTH_LONG);
        //*Set the position of the Toast box to the center of the UI
        registrationToast.setGravity(Gravity.CENTER, 0, 0);
        registrationToast.show();
        new CountDownTimer(3000, 1000)
        {
            public void onTick(long millisUntilFinished) {registrationToast.show();}
            public void onFinish() {registrationToast.cancel();}
        }.start();
        startActivity(new Intent(this, UserLogin.class));
        finish();
    }

    //method for dropdown list validation
    private boolean validateDropDown(Spinner spinner, String selected, TextView textview){
        textview.setError(null);
        String st = spinner.getSelectedItem().toString();
        int pos = spinner.getSelectedItemPosition();
        if(pos == 0 || st == null || st.equals("--Please select one--")){
             textview.setError("Please select one.");
             textview.requestFocus();
             return false;
         }
        selected = spinner.getSelectedItem().toString();
        return true;
    }

    //method for checkbox validation
    private boolean isAgreementChecked(){
        termOfUseView.setError(null);
        if(!checkAgree.isChecked()){
            termOfUseView.setError("Please ensure you agree with the Term of use.");
            termOfUseView.requestFocus();
            return false;
        }
        return true;
    }

    public void doubleCheck(){
        //if the dropdown list for preferred therapist has been chosen, let the error message disappear
        regis_pot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
                if(regis_pot.getSelectedItemPosition() != 0) {
                    regis_pot_text.setError(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                regis_pot_text.setError("Please select one.");
            }
        });

        //if the dropdown list for most preferred location has been chosen, let the error message disappear
        regis_mpl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
                if(regis_mpl.getSelectedItemPosition() != 0) {
                    regis_mpl_text.setError(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                regis_mpl_text.setError("Please select one.");
            }
        });

        //if the agree button is checked, let the error message disappear
        checkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    termOfUseView.setError(null);
                }
            }
        });
    }

    private class GetNonce extends AsyncTask<Void, Void, Void> {

        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
           // proDialog = new ProgressDialog(UserRegistration.this);
            //proDialog.setMessage("Loading...");
            //proDialog.setCancelable(false);
            //proDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0){
            WebRequest webreqNonce = new WebRequest();

            String jsonStr = webreqNonce.makeWebServiceCall("http://shencare.net/api/get_nonce/?controller=user&method=register", WebRequest.GETRequest);

            Log.d("Response: ", "> " + jsonStr);//null

            final String TAG_NONCE = "nonce";

            if (jsonStr != null) {
                try {
                    //ShencareUser su = new ShencareUser();
                    JSONObject jsonNonce = new JSONObject(jsonStr);

                    nonce = jsonNonce.getString(TAG_NONCE);

                    //su = new ShencareUser(sUsername, sSurname, sLastname, sEmail, sContact);
                    return null;
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
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
            //if (proDialog.isShowing())
              //  proDialog.dismiss();

        }
    }

    private class PostRegistration extends AsyncTask<Void, Void, Void>{

        //ShencareUser tempUser;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
            //proDialog = new ProgressDialog(UserRegistration.this);
            //proDialog.setMessage("Loading...");
           // proDialog.setCancelable(false);
           // proDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WebRequest webreq = new WebRequest();
            //final String TAG_USERID = "user_id";

            //String url = "http://shencare.net/api/user/register/?nonce=c8e10c5b9e&username=" + regis_username + "&email=" + regis_email + "&user_pass=" + regis_pw + "&firstname=" + regis_name + "&lastname=" + regis_surname;
            urlx ="http://shencare.net/api/user/register_new_user/?nonce="+nonce+"&username="
                    +new_username+"&email="+new_email+"&user_pass="+new_pw+"&firstname="+new_name
                    +"&lastname="+new_surname+"&display_name="+display_name+"&telephone="+telephone
                    +"&pref_ot="+pref+"&location="+loc+"";
            // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(urlx, WebRequest.POSTRequest);
            Log.d("Response: ", "> " + urlx);

            /*if (jsonStr != null) {
                try {
                    JSONObject jsonNonce = new JSONObject(jsonStr);

                    user_id = jsonNonce.getString(TAG_USERID);

                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                Log.e("ServiceHandler", "No data received from HTTP request");
                return null;
            }*/
            return null;


        }

        @Override
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
            //if (proDialog.isShowing())
            //    proDialog.dismiss();
        }
    }

    private void getRegistrationFields(){
        new_name = regis_name.getText().toString();
        new_surname = regis_surname.getText().toString();
        new_username = regis_username.getText().toString();
        new_pw = regis_pw.getText().toString();
        new_email = regis_email.getText().toString();
        display_name = new_name;
        pref_ot = regis_pot.getSelectedItem().toString();
        location = regis_mpl.getSelectedItem().toString();
        telephone = regis_telephone.getText().toString();


        String[] termlist = getResources().getStringArray(R.array.unsorted_terms);

        for (int i=0; i<termlist.length -1; i++){
            if(pref_ot.toLowerCase().trim().equals(termlist[i].toLowerCase().trim())){
                temp2 = i+3;
                pref = Integer.toString(temp2);
                Log.d("Fields: ", ">" + termlist[i]);
            }
        }

        for (int j=0; j<termlist.length -1; j++){
            if(location.toLowerCase().trim().equals(termlist[j].toLowerCase().trim())){
                temp1 = j+3;
                loc = Integer.toString(temp1);
                Log.d("Fields: ", ">" + termlist[j]);
            }

        }

        Log.d("Fields: ", ">" + temp1);
        Log.d("Fields: ", ">" + location);
        Log.d("Fields: ", ">" + pref);
        Log.d("Fields: ", ">" + '"' + pref_ot + '"');

    }

}
