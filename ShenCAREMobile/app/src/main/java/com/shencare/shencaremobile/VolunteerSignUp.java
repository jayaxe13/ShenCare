package com.shencare.shencaremobile;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shencare.shencaremobile.Email_package.EmailSender;
import com.shencare.shencaremobile.VolunteerPackage.ShencareVolunteer;

public class VolunteerSignUp extends Navigation_drawer implements View.OnClickListener {
    private TextView vol_freqOfWorkText, vol_preferWorkText,vol_checkBoxes;
    private EditText name,email,phone_number, message;
    private Button volSignUp;
    private RadioGroup freqOfWork, preferWorkSize;
    private CheckBox volCheckBox1,volCheckBox2,volCheckBox3,volCheckBox4,volCheckBox5;
    private RadioButton freqOfWork_daily,freqOfWork_fortnightly,freqOfWork_weekly, freqOfWork_monthly,preferGroup,preferIndividual;
    public String volCheckBox1Str,volCheckBox2Str,volCheckBox3Str,volCheckBox4Str,volCheckBox5Str,
            freqOfWork_dailyStr,preferGroupStr,
            vol_freqOfWorkTextStr, vol_preferWorkTextStr,vol_checkBoxesStr,nameStr,emailStr,phone_numberStr, messageStr, textMessage, freqOfWorkStr, preferWorkSizeStr;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_volunteer_sign_up, frameLayout);

        /**
         * Setting title and itemChecked
         */
        context = this;
        mDrawerList.setItemChecked(position, true);
        setTitle("Volunteer Sign Up");
        menuCondition="VolunteerSignUp";


        //Match all the variables with layout elements
        volSignUp = (Button)findViewById(R.id.joinVolunteer);
        volSignUp.setOnClickListener(this);
        vol_freqOfWorkText = (TextView) findViewById(R.id.vol_freqOfWorkText);
        vol_preferWorkText = (TextView)findViewById(R.id.vol_preferWorkText);
        vol_checkBoxes = (TextView)findViewById(R.id.vol_checkBoxes);
        name = (EditText) findViewById(R.id.vol_editName);
        email = (EditText) findViewById(R.id.vol_editEmail);
        phone_number = (EditText) findViewById(R.id.vol_editPhone);
        freqOfWork = (RadioGroup) findViewById(R.id.freqOfWork);
        preferWorkSize = (RadioGroup) findViewById(R.id.preferWorkSize);

        freqOfWork_daily = (RadioButton) findViewById(R.id.freqOfWork_daily);
        preferGroup = (RadioButton) findViewById(R.id.preferGroup);

        volCheckBox1 = (CheckBox) findViewById(R.id.volCheckBox1);
        volCheckBox2 = (CheckBox) findViewById(R.id.volCheckBox2);
        volCheckBox3 = (CheckBox) findViewById(R.id.volCheckBox3);
        volCheckBox4 = (CheckBox) findViewById(R.id.volCheckBox4);
        volCheckBox5 = (CheckBox) findViewById(R.id.volCheckBox5);
        message = (EditText) findViewById(R.id.vol_msg_box);


        doubleCheck();
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            case R.id.joinVolunteer:
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                * */
                if(checkValidation()){

                    //converting to string
                    vol_freqOfWorkTextStr = vol_freqOfWorkText.getText().toString();
                    vol_preferWorkTextStr = vol_preferWorkText.getText().toString();
                    vol_checkBoxesStr = vol_checkBoxes.getText().toString();
                    nameStr = name.getText().toString();
                    emailStr = email.getText().toString();
                    phone_numberStr = phone_number.getText().toString();
                    volCheckBox1Str = volCheckBox1.getText().toString();
                    volCheckBox2Str = volCheckBox2.getText().toString();
                    volCheckBox3Str = volCheckBox3.getText().toString();
                    volCheckBox4Str = volCheckBox4.getText().toString();
                    volCheckBox5Str = volCheckBox5.getText().toString();
                    messageStr = message.getText().toString();
                    freqOfWork_dailyStr = freqOfWork_daily.getText().toString();
                    preferGroupStr = preferGroup.getText().toString();

                    freqOfWorkStr = returnChosenRadioButton(freqOfWork);
                    preferWorkSizeStr = returnChosenRadioButton(preferWorkSize);

                    textMessage = ("Volunteer Name: "+nameStr+ "<br>"+
                            "Volunteer Email: "+emailStr+"<br>"+
                            "Volunteer Phone Number: "+phone_numberStr+"<br>"+
                            "Volunteer Choice of Task: " + atLeastOneCheckBoxCheckedValue()+"<br>"+
                            "Volunteer Choice of Frequency Of Work: " + freqOfWorkStr+"<br>"+
                            "Volunteer Choice of Work Group : "+preferWorkSizeStr+"<br>"+
                            "Volunteer Message: "+ messageStr);
                    new EmailSender( textMessage, emailStr);

                    submitVolForm();

                }else{
                    final Toast submitVolForm = Toast.makeText(getBaseContext(), R.string.form_contains_err,Toast.LENGTH_LONG);
                    submitVolForm.setGravity(Gravity.CENTER, 0, 0);
                    submitVolForm.show();
                    new CountDownTimer(4000, 1000) {
                        public void onTick(long millisUntilFinished) {submitVolForm.show();}
                        public void onFinish() {submitVolForm.cancel();}
                    }.start();
                }

                break;
        }
    }

    // Method for handle all the validation calls
    private boolean checkValidation(){
        boolean check = true;

        if(!Validation.isGeneralName(name, true)) check = false;
        if(!Validation.isEmailAddress(email, true))  check = false;
        if(!Validation.isPhone(phone_number, true)) check = false;
        if(!Validation.isRadioButtonChecked(freqOfWork,vol_freqOfWorkText, true)) check =false;
        if(!Validation.isRadioButtonChecked(preferWorkSize,vol_preferWorkText,true)) check = false;
        if(!atLeastOneCheckBoxChecked()) check = false;
        return check;
    }


    // method for checkbox validation
    private boolean atLeastOneCheckBoxChecked(){
        vol_checkBoxes.setError(null);
        if(volCheckBox1.isChecked() || volCheckBox2.isChecked() || volCheckBox3.isChecked() || volCheckBox4.isChecked() || volCheckBox5.isChecked()){
            return true;
        }
        vol_checkBoxes.setError(getString(R.string.Checkbox));
        //vol_checkBoxes.requestFocus();
        return false;
    }
    private String atLeastOneCheckBoxCheckedValue(){

        String checkedChoices = "";

        if(volCheckBox1.isChecked()){
            checkedChoices = checkedChoices+ volCheckBox1Str +",";}
        if(volCheckBox2.isChecked()){
            checkedChoices = checkedChoices+ volCheckBox2Str +",";}
        if(volCheckBox3.isChecked()){
            checkedChoices = checkedChoices+ volCheckBox3Str +",";}
        if(volCheckBox4.isChecked()){
            checkedChoices = checkedChoices+ volCheckBox4Str +",";}
        if(volCheckBox5.isChecked()){
            checkedChoices = checkedChoices+ volCheckBox5Str +",";}
        return checkedChoices;
    }

    // if all the fields are validated - redirect to home page and save the user input into database
    private void submitVolForm(){
        //Submit your form here. your form is valid
        final Toast volSuccessToast = Toast.makeText(getBaseContext(), getString(R.string.successMessage), Toast.LENGTH_LONG);
        //*Set the position of the Toast box to the center of the UI
        volSuccessToast.setGravity(Gravity.CENTER, 0, 0);
        volSuccessToast.show();
        new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {volSuccessToast.show();}
            public void onFinish() {volSuccessToast.cancel();}
        }.start();
        startActivity(new Intent(this, Home.class));
        finish();
    }

    //Enable the alert to disappear after the button is being clicked
    private void doubleCheck(){
        volCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vol_checkBoxes.setError(null);
                }
            }
        });

        volCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vol_checkBoxes.setError(null);
                }
            }
        });
        volCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vol_checkBoxes.setError(null);
                }
            }
        });
        volCheckBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vol_checkBoxes.setError(null);
                }
            }
        });
        volCheckBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vol_checkBoxes.setError(null);
                }
            }
        });

        freqOfWork.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.freqOfWork_daily:
                        vol_freqOfWorkText.setError(null);
                    case R.id.freqOfWork_fortnightly:
                        vol_freqOfWorkText.setError(null);
                    case R.id.freqOfWork_weekly:
                        vol_freqOfWorkText.setError(null);
                    case R.id.freqOfWork_monthly:
                        vol_freqOfWorkText.setError(null);
                }
            }

        });

        preferWorkSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.preferGroup:
                        vol_preferWorkText.setError(null);
                    case R.id.preferIndividual:
                        vol_preferWorkText.setError(null);
                }
            }

        });
    }
    private String returnChosenRadioButton(RadioGroup x){
        int id = x.getCheckedRadioButtonId();
        RadioButton xButton = (RadioButton)findViewById(id);
        String xStr = xButton.getText().toString();
        return xStr;
    }

    private boolean updateDatabase(){
        //name,email,phone_number, message;
        ShencareVolunteer shencareVolunteer = new ShencareVolunteer();
        String sName = name.getText().toString();
        String sEmail = email.getText().toString();
        String sPhone = phone_number.getText().toString();
        String sMessage = null;
        if(message.getText() != null){
            sMessage = message.getText().toString();
        }

        //preferredAreaInfo
        //preferSizeInfo
        //preferWorkSize
        //sendVolEmail(sName,sEmail,sPhone, sMessage, PreferredAreaInfo, preferSizeInfo, preferWorkSize)
        return true;
    }
}



