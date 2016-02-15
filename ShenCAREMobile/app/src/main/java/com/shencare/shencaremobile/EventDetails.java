package com.shencare.shencaremobile;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shencare.shencaremobile.Util.SessionManager;

public class EventDetails extends Navigation_drawer implements View.OnClickListener{
    private TextView eventName, eventDate, eventTime, eventVenue, eventDetails;
    private Button register;
    private String name, date, time, venue, details, confirmationMsg;
    Context context = this;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_event_details, frameLayout);

        /**
         * Setting title and itemChecked
         */
        //mDrawerList.setItemChecked(position, true);
        setTitle("Event Details");
        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            //User is already logged in. Hide the login button
            menuCondition = "UserLogin";
        }else{
            menuCondition = "Events Details";
        }

        register = (Button) findViewById(R.id.registerButton);
        register.setOnClickListener(this);
        eventName = (TextView) findViewById(R.id.eventName);
        eventName.setText(R.string.eventName);

        eventDate = (TextView) findViewById(R.id.eventDate);
        eventDate.setText(R.string.eventDate);

        eventTime = (TextView) findViewById(R.id.eventTime);
        eventTime.setText(R.string.eventTime);

        eventVenue = (TextView) findViewById(R.id.eventVenue);
        eventVenue.setText(R.string.eventVenue);

        eventDetails = (TextView) findViewById(R.id.eventDetails);
        eventDetails.setText(R.string.eventDetails);

        name = getString(R.string.eventName);
        date = getString(R.string.eventDate);
        time = getString(R.string.eventTime);
        venue = getString(R.string.eventVenue);
        details = getString(R.string.eventDetails);
    }

    @Override
    public void onClick(View v) {


        int id = v.getId();
        switch (id) {
            //click register button
            case R.id.registerButton:
                if(session.isLoggedIn()){
                    //User is already logged in. Hide the login button
                    eventRegister();
                }else{
                    final Toast bookEventWarn = Toast.makeText(getBaseContext(), R.string.bookEventWarning,Toast.LENGTH_LONG);
                    bookEventWarn.setGravity(Gravity.CENTER, 0, 0);
                    bookEventWarn.show();
                    new CountDownTimer(4000, 1000) {
                        public void onTick(long millisUntilFinished) {bookEventWarn.show();}
                        public void onFinish() {bookEventWarn.cancel();}
                    }.start();
                }
                break;

        }

    }

    public void eventRegister(){
        //prepare for confirmation message
        confirmationMsg = "The event you are about to register for:\n\n" + name + "\nDate: " + date
                + "\nTime: " + time + "\nVenue: " + venue;
        //construct dialog box
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        // LayoutInflater inflater = this.getLayoutInflater();
        //final View eventDetailsView = inflater.inflate(R.layout.event_registration_confirmation_dialog, null);
        //dialogBuilder.setView(eventDetailsView);
        //dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogBuilder.setTitle("Event Registration");
        dialogBuilder.setMessage(confirmationMsg);
        dialogBuilder.setPositiveButton("Confirm Registration", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //send email to user
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //return to eventDetails page
            }
        });

        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
