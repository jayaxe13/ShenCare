package com.shencare.shencaremobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shencare.shencaremobile.Util.WebRequest;
import com.shencare.shencaremobile.userPackage.ShencareUser;
import com.shencare.shencaremobile.userPackage.ShencareUserProfileManager;

public class UserProfile extends Navigation_drawer implements View.OnClickListener {
    private Button editButton;
    private TextView username, name, surname, gender,contact,email,address,pot,mpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_user_profile, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("My Profile");
        menuCondition="UserProfile";
        //Navigation_drawer.setTitle(getTitle().toString());

        //editButton = (Button) findViewById(R.id.edit_profile_button);
        //editButton.setOnClickListener(this);
        username = (TextView)findViewById(R.id.user_username);
        name = (TextView)findViewById(R.id.user_name);
        surname = (TextView)findViewById(R.id.user_surname);
        gender = (TextView)findViewById(R.id.user_gender);
        contact = (TextView)findViewById(R.id.user_contact);
        email = (TextView)findViewById(R.id.user_email);
        address = (TextView)findViewById(R.id.user_addr);
        pot = (TextView)findViewById(R.id.user_pot);
        mpl = (TextView)findViewById(R.id.user_mpl);
        //new GetStudents().execute();

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            default:
                startActivity(new Intent(this,UserProfileEdit.class));
                break;
        }
    }

    /*private class GetStudents extends AsyncTask<Void, Void, Void> {

        // Hashmap for ListView
//ArrayList<HashMap<String, String>> studentList;
        ShencareUser tempUser;
        ProgressDialog proDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress loading dialog
            proDialog = new ProgressDialog(UserProfile.this);
            proDialog.setMessage("Loading...");
            proDialog.setCancelable(false);
            proDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            WebRequest webreq = new WebRequest();

             // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(ShencareUserProfileManager.url, WebRequest.GETRequest);

            Log.d("Response: ", "> " + jsonStr);//null here

            tempUser = ShencareUserProfileManager.ParseJSON(jsonStr);
            return null;
        }

        @Override
        protected void onPostExecute(Void requestresult) {
            super.onPostExecute(requestresult);
            // Dismiss the progress dialog
            if (proDialog.isShowing())
                proDialog.dismiss();
/**
 * Updating received data from JSON into userprofile
 * */
//ListAdapter adapter = new SimpleAdapter(
//MainActivity.this, studentList,
//R.layout.list_item, new String[]{TAG_STUDENT_NAME, TAG_EMAIL,
//TAG_STUDENT_PHONE_MOBILE}, new int[]{R.id.name,
//R.id.email, R.id.mobile});

//setListAdapter(adapter);

          /* username.setText(tempUser.getUsername());
            surname.setText(tempUser.getSurname());
            name.setText(tempUser.getFirstname());
            email.setText(tempUser.getEmail());
            contact.setText(tempUser.getContactNumber());

        }
    }*/
}
