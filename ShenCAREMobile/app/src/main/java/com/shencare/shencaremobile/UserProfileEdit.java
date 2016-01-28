package com.shencare.shencaremobile;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileEdit extends Navigation_drawer implements View.OnClickListener{
    private Button saveButton;
    private EditText name,surname,contact,address,email;
    private TextView radioGender, spinnerPot, spinnerMpl;
    private RadioGroup editGender;
    private RadioButton male, female;
    private Spinner edit_pot, edit_mpl;
    private String selected_pot, selected_mpl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_user_profile_edit, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Edit Profile");
        menuCondition="UserProfileEdit";
        //Navigation_drawer.setTitle(getTitle().toString());

        name = (EditText)findViewById(R.id.edit_user_name);
        surname = (EditText)findViewById(R.id.edit_user_surname);
        contact = (EditText)findViewById(R.id.edit_user_contact);
        address = (EditText)findViewById(R.id.edit_user_addr);
        email = (EditText) findViewById(R.id.edit_user_email);
        editGender = (RadioGroup) findViewById(R.id.edit_gender_group);
        male = (RadioButton)findViewById(R.id.edit_gender_m);
        female = (RadioButton) findViewById(R.id.edit_gender_f);
        edit_pot = (Spinner) findViewById(R.id.edit_profile_pot);
        edit_mpl = (Spinner) findViewById(R.id.edit_profile_mpl);
        radioGender = (TextView) findViewById(R.id.edit_profile_gender_field);
        spinnerPot = (TextView) findViewById(R.id.edit_profile_pot_field);
        spinnerMpl =(TextView)findViewById(R.id.edit_profile_mpl_field);


        //saveButton = (Button) findViewById(R.id.save_profile_button);
        //saveButton.setOnClickListener(this);

        doubleCheck();
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            default:
                                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                * */
                if(checkValidation()){
                    submitProfileChange();
                }else{
                    final Toast submitProfile = Toast.makeText(getBaseContext(), R.string.form_contains_err,Toast.LENGTH_LONG);
                    submitProfile.setGravity(Gravity.CENTER, 0, 0);
                    submitProfile.show();
                    new CountDownTimer(4000, 1000) {
                        public void onTick(long millisUntilFinished) {submitProfile.show();}
                        public void onFinish() {submitProfile.cancel();}
                    }.start();
                }
                break;
        }
    }

    private boolean checkValidation(){
        boolean check = true;

        if(!Validation.isGeneralName(name,true)) check = false;
        if(!Validation.isGeneralName(surname,true)) check = false;
        if(!Validation.isPhone(contact, true)) check = false;
        if(!Validation.hasText(address)) check = false;
        if(!Validation.isEmailAddress(email, true)) check = false;

        if(!Validation.isRadioButtonChecked(editGender, radioGender, true)) check = false;
        if(!Validation.validateDropDown(edit_pot,selected_pot,spinnerPot)) check = false;
        if(!Validation.validateDropDown(edit_mpl,selected_mpl,spinnerMpl)) check = false;

        return check;
    }

    private void submitProfileChange(){
        //Submit your form here if your form is valid
        startActivity(new Intent(this,UserProfile.class));
        final Toast editProfileToast = Toast.makeText(getBaseContext(), "Changed has been saved.", Toast.LENGTH_LONG);
        //*Set the position of the Toast box to the center of the UI
        editProfileToast.setGravity(Gravity.CENTER, 0, 0);
        editProfileToast.show();
        new CountDownTimer(4000, 1000)
        {

            public void onTick(long millisUntilFinished) {editProfileToast.show();}
            public void onFinish() {editProfileToast.cancel();}

        }.start();
    }

    public void doubleCheck(){

        //check whether the gender field is checked
        editGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.edit_gender_m:
                        radioGender.setError(null);
                    case R.id.edit_gender_f:
                        radioGender.setError(null);
                }
            }

        });

        //if the dropdown list for most preferred location has been chosen, let the error message disappear
        edit_mpl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
                if (edit_mpl.getSelectedItemPosition() != 0) {
                    spinnerMpl.setError(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                spinnerMpl.setError("Please select one.");
            }
        });

        //if the dropdown list for most preferred location has been chosen, let the error message disappear
        edit_pot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
                if (edit_pot.getSelectedItemPosition() != 0) {
                    spinnerPot.setError(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                spinnerPot.setError("Please select one.");
            }
        });



    }

}
