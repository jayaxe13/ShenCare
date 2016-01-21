package com.shencare.shencaremobile;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class VolunteerSignUp extends AppCompatActivity implements View.OnClickListener {
    private TextView vol_freqOfWorkText, vol_preferWorkText,vol_checkBoxes;
    private EditText name,email,phone_number, message;
    private Button volSignUp;
    private RadioGroup freqOfWork, preferWorkSize;
    private CheckBox volCheckBox1,volCheckBox2,volCheckBox3,volCheckBox4,volCheckBox5;
    private RadioButton freqOfWork_daily,freqOfWork_fortnightly,freqOfWork_weekly, freqOfWork_monthly,preferGroup,preferIndividual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_sign_up);
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
        vol_checkBoxes.requestFocus();
        return false;
    }

    // if all the fields are validated - redirect to home page and save the user input into database
    private void submitVolForm(){
        //Submit your form here. your form is valid
        startActivity(new Intent(this, Home.class));
        final Toast volSuccessToast = Toast.makeText(getBaseContext(), getString(R.string.successMessage), Toast.LENGTH_LONG);
        //*Set the position of the Toast box to the center of the UI
        volSuccessToast.setGravity(Gravity.CENTER, 0, 0);
        volSuccessToast.show();
        new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {volSuccessToast.show();}
            public void onFinish() {volSuccessToast.cancel();}
        }.start();
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

}
