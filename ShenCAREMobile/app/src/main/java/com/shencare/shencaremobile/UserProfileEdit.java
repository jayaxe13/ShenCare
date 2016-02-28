package com.shencare.shencaremobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shencare.shencaremobile.Util.SessionManager;
import com.shencare.shencaremobile.Util.WebRequest;
import com.shencare.shencaremobile.UserPackage.ShencareUser;

public class UserProfileEdit extends Navigation_drawer implements View.OnClickListener{
    private Button saveButton;
    private EditText  name,surname,contact,address,email;
    private TextView username, radioGender, spinnerPot, spinnerMpl;
    private RadioGroup editGender;
    private RadioButton male, female;
    private Spinner edit_pot, edit_mpl;
    private String selected_pot, selected_mpl, new_name, new_surname,
            new_username, new_pw, new_email, display_name, url, pref_ot, location,
            telephone, pref, loc, x, y, nonce, user_id, userId;
    private ShencareUser shencareUser;
    private Intent intentReceiver;
    private SessionManager session;
    private int temp1,temp2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initiate session
        session = new SessionManager(getApplicationContext());

        userId = session.getUserId();

        getLayoutInflater().inflate(R.layout.activity_user_profile_edit, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Edit Profile");
        menuCondition="UserProfileEdit";
        //Navigation_drawer.setTitle(getTitle().toString());
        //In process getting user information from UserProfile Class
        intentReceiver = getIntent();

        username =(TextView)findViewById(R.id.edit_user_username);
        username.setText(intentReceiver.getStringExtra("username"));
        name = (EditText)findViewById(R.id.edit_user_name);
        name.setText(intentReceiver.getStringExtra("firstname"));
        surname = (EditText)findViewById(R.id.edit_user_surname);
        surname.setText(intentReceiver.getStringExtra("surname"));
        contact = (EditText)findViewById(R.id.edit_user_contact);
        contact.setText(intentReceiver.getStringExtra("contact"));
        //address = (EditText)findViewById(R.id.edit_user_addr);
        email = (EditText) findViewById(R.id.edit_user_email);
        email.setText(intentReceiver.getStringExtra("email"));
        //editGender = (RadioGroup) findViewById(R.id.edit_gender_group);
        //male = (RadioButton)findViewById(R.id.edit_gender_m);
        //female = (RadioButton) findViewById(R.id.edit_gender_f);
        edit_pot = (Spinner) findViewById(R.id.edit_profile_pot);
        edit_mpl = (Spinner) findViewById(R.id.edit_profile_mpl);
        //radioGender = (TextView) findViewById(R.id.edit_profile_gender_field);
        ArrayAdapter<CharSequence> adapterPOT = ArrayAdapter.createFromResource(
        this, R.array.pot_characters, android.R.layout.simple_spinner_item);
        adapterPOT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPot = (TextView) findViewById(R.id.edit_profile_pot_field);
        edit_pot.setAdapter(adapterPOT);
        //System.out.println(adapterPOT.getPosition(intentReceiver.getStringExtra("sPot")));
        for (int i=0;i<edit_pot.getCount();i++){
            if (edit_pot.getItemAtPosition(i).equals(intentReceiver.getStringExtra("pot"))){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + i + edit_pot.getItemAtPosition(i));
                edit_pot.setSelection(i);
            }
        }
        //edit_pot.setSelection(adapterPOT.getPosition(intentReceiver.getStringExtra("sPot")));
        spinnerMpl =(TextView)findViewById(R.id.edit_profile_mpl_field);
        ArrayAdapter<CharSequence> adapterMPL = ArrayAdapter.createFromResource(
                this, R.array.mpl_characters, android.R.layout.simple_spinner_item);
        adapterMPL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_mpl.setAdapter(adapterMPL);
        for (int i=0;i<edit_mpl.getCount();i++){
            if (edit_mpl.getItemAtPosition(i).equals(intentReceiver.getStringExtra("mpl"))){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + i + edit_mpl.getItemAtPosition(i));
                edit_mpl.setSelection(i);
            }
        }
        //edit_mpl.setSelection(adapterMPL.getPosition(intentReceiver.getStringExtra("sMpl")));
        //System.out.println(adapterMPL.getPosition(intentReceiver.getStringExtra("sMpl")));

        //saveButton = (Button) findViewById(R.id.save_profile_button);
        //saveButton.setOnClickListener(this);

        doubleCheck();
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            default:
                break;
        }
    }

    public void changeSavingProcessing(){
        /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                * */
        if(checkValidation()){
            getRegistrationFields();
            new UpdateUserProfile().execute();
            submitProfileChange();
        }else{
            final Toast submitProfile = Toast.makeText(getBaseContext(), R.string.form_contains_err,Toast.LENGTH_LONG);
            submitProfile.setGravity(Gravity.CENTER, 0, 0);
            submitProfile.show();
            new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {submitProfile.show();}
                public void onFinish() {submitProfile.cancel();}
            }.start();
        }
    }

    private boolean checkValidation(){
        boolean check = true;

        if(!Validation.isGeneralName(name,true)) check = false;
        if(!Validation.isGeneralName(surname, true)) check = false;
        if(!Validation.isPhone(contact, true)) check = false;
        //if(!Validation.hasText(address)) check = false;
        if(!Validation.isEmailAddress(email, true)) check = false;

        //if(!Validation.isRadioButtonChecked(editGender, radioGender, true)) check = false;
        if(!Validation.validateDropDown(edit_pot,selected_pot,spinnerPot)) check = false;
        if(!Validation.validateDropDown(edit_mpl,selected_mpl,spinnerMpl)) check = false;

        return check;
    }

    private void submitProfileChange(){
        //Submit your form here if your form is valid
        final Toast editProfileToast = Toast.makeText(getBaseContext(), "Changed has been saved.", Toast.LENGTH_LONG);
        //*Set the position of the Toast box to the center of the UI
        editProfileToast.setGravity(Gravity.CENTER, 0, 0);
        editProfileToast.show();
        new CountDownTimer(3000, 1000)
        {

            public void onTick(long millisUntilFinished) {editProfileToast.show();}
            public void onFinish() {editProfileToast.cancel();
            }

        }.start();
        startActivity(new Intent(this, UserProfile.class));
        finish();
    }

    public void doubleCheck(){

        //check whether the gender field is checked
        /**editGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.edit_gender_m:
                        radioGender.setError(null);
                    case R.id.edit_gender_f:
                        radioGender.setError(null);
                }
            }

        });*/

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {

            case R.id.action_save:
                changeSavingProcessing();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class UpdateUserProfile extends AsyncTask<Void, Void,Void>{

        ProgressDialog proDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
            //proDialog = new ProgressDialog(UserProfileEdit.this);
           // proDialog.setMessage("Loading...");
           // proDialog.setCancelable(false);
            //proDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WebRequest webreq = new WebRequest();


            url ="http://shencare.net/api/user/update_user_profile/?ID="+userId+"&username="
                    +new_username+"&email="+new_email+"&firstname="+new_name
                    +"&lastname="+new_surname+"&display_name="+display_name+"&telephone="+telephone
                    +"&pref_ot="+pref+"&location="+loc+"";
            // Making a request to url and getting response
            webreq.makeWebServiceCall(url, WebRequest.POSTRequest);

            Log.d("Response: ", "> " + url);//null here

            //tempUser = ShencareUserProfileManager.ParseJSON(jsonStr);
            return null;
        }

        @Override
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
          //  if (proDialog.isShowing())
          //      proDialog.dismiss();
        }

    }

    private void getRegistrationFields(){
        new_name = name.getText().toString();
        new_surname = surname.getText().toString();
        new_username = username.getText().toString();
        new_email = email.getText().toString();
        display_name = new_name;
        pref_ot = edit_pot.getSelectedItem().toString();
        location = edit_mpl.getSelectedItem().toString();
        telephone = contact.getText().toString();


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
