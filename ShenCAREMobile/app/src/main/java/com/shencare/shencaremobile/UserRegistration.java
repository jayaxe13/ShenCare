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

public class UserRegistration extends Navigation_drawer implements View.OnClickListener {
    private EditText regis_name, regis_surname, regis_username,
            regis_pw,regis_cp,regis_email;
    private Button regis_submit;
    private Spinner regis_pot, regis_mpl;
    private String selected_pot, selected_mpl;
    private TextView termOfUseView,regis_pot_text, regis_mpl_text;
    private CheckBox checkAgree;


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
                    submitRegisForm();
                }else{
                    final Toast submitRegisForm = Toast.makeText(getBaseContext(), R.string.form_contains_err,Toast.LENGTH_LONG);
                    submitRegisForm.setGravity(Gravity.CENTER, 0, 0);
                    submitRegisForm.show();
                    new CountDownTimer(4000, 1000) {
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
        if(!Validation.isUsername(regis_username,true)) check = false;
        if(!Validation.isPassword(regis_pw,true)) check = false;
        if(!Validation.isValidConfirmedPassword(regis_cp, regis_pw.getText().toString().trim(),true)) check = false;
        if(!Validation.isEmailAddress(regis_email,true)) check = false;
        if(!validateDropDown(regis_pot, selected_pot, regis_pot_text)) check = false;
        if(!validateDropDown(regis_mpl, selected_mpl, regis_mpl_text)) check = false;
        if(!isAgreementChecked()) check = false;

        return check;
    }

    private void submitRegisForm(){
        //Submit your form here if your form is valid
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

}
