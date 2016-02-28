package com.shencare.shencaremobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shencare.shencaremobile.Util.SessionManager;
import com.shencare.shencaremobile.Util.WebRequest;
import com.shencare.shencaremobile.UserPackage.ShencareUser;
import com.shencare.shencaremobile.UserPackage.ShencareUserProfileManager;

public class UserProfile extends Navigation_drawer implements View.OnClickListener {
    private Button editButton,logoutButton;
    private TextView username, name, surname, gender,contact,email,address,pot,mpl;
    private SessionManager session;
    private String universalUser,sUsername, sName,sSurname,sGender, sContact,sEmail, sAddress, sPot, sMpl;
    private ShencareUser shencareuser;

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
        session = new SessionManager(getApplicationContext());

        if(!session.isLoggedIn()){
            //User is already logged in. Take him to home activity
            Intent intent = new Intent(UserProfile.this, UserLogin.class);
            startActivity(intent);
            finish();
        }else{
            Log.d("Fields: ", ">" + session.getUserDetails());
            universalUser = session.getUserDetails();
            new GetStudents().execute();
        }

        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);
        username = (TextView)findViewById(R.id.user_username);
        name = (TextView)findViewById(R.id.user_name);
        surname = (TextView)findViewById(R.id.user_surname);
        //gender = (TextView)findViewById(R.id.user_gender);
        contact = (TextView)findViewById(R.id.user_contact);
        email = (TextView)findViewById(R.id.user_email);
        //address = (TextView)findViewById(R.id.user_addr);
        pot = (TextView)findViewById(R.id.user_pot);
        mpl = (TextView)findViewById(R.id.user_mpl);

    }



    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            case R.id.logoutButton:
                //startActivity(new Intent(UserProfile.this, UserLogin.class));
                final Toast logout = Toast.makeText(getBaseContext(), R.string.logoutInfo,Toast.LENGTH_LONG);
                logout.setGravity(Gravity.CENTER, 0, 0);
                logout.show();
                new CountDownTimer(3000, 1000) {
                    public void onTick(long millisUntilFinished) {logout.show();}
                    public void onFinish() {logout.cancel();}
                }.start();
                session.logoutUser();
                break;
        }
    }

    private class GetStudents extends AsyncTask<Void, Void, Void> {

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

            String urlForUser = ShencareUserProfileManager.url + universalUser.trim();
             // Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(urlForUser, WebRequest.GETRequest);

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
            username.setText(tempUser.getUsername());
            surname.setText(tempUser.getSurname());
            name.setText(tempUser.getFirstname());
            email.setText(tempUser.getEmail());
            contact.setText(tempUser.getContactNumber());
            //pot.setText(tempUser.getPot());
            //mpl.setText(tempUser.getMpl());

            String[] termlist = getResources().getStringArray(R.array.unsorted_terms);

            for (int i=0; i<termlist.length -1; i++){
                if(tempUser.getPot().equalsIgnoreCase(Integer.toString(i))){
                    Log.d("PrefOT: ", ">" + termlist[i-3]);
                    sPot = termlist[i-3];
                    pot.setText(sPot);
                }
            }
            for (int j=0; j<termlist.length -1; j++){
                if(tempUser.getMpl().equalsIgnoreCase(Integer.toString(j))){
                    Log.d("Location: ", ">" + termlist[j-3]);
                    sMpl = termlist[j-3];
                    mpl.setText(sMpl);
                }
            }


            //get parameters for passing to the userProfileEdit page
            sUsername = tempUser.getUsername();
            sName = tempUser.getFirstname();
            sSurname = tempUser.getSurname();
            sContact = tempUser.getContactNumber();
            sEmail = tempUser.getEmail();
            //sPot = tempUser.getPot();
            //sMpl = tempUser.getMpl();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {

            case R.id.action_edit:
                //Start Intend to UserProfileEdit class and pass the ShencareUser object to the UserProfileEdit class
                Intent intentUserProfile = new Intent(this, UserProfileEdit.class);
                intentUserProfile.putExtra("username", sUsername);
                intentUserProfile.putExtra("firstname", sName);
                intentUserProfile.putExtra("surname", sSurname);
                intentUserProfile.putExtra("contact", sContact);
                intentUserProfile.putExtra("email",sEmail);
                intentUserProfile.putExtra("pot",sPot);
                intentUserProfile.putExtra("mpl",sMpl);
                startActivity(intentUserProfile);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
