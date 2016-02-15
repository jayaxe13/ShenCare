package com.shencare.shencaremobile;

import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.FragmentTabHost;

import com.shencare.shencaremobile.Service_Fragment.ServiceDetails_ProceduresPrices;
import com.shencare.shencaremobile.Service_Fragment.ServiceDetails_Profile;
import com.shencare.shencaremobile.Service_Fragment.ServiceDetails_Reviews;
import com.shencare.shencaremobile.Util.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServiceDetails extends Navigation_drawer implements View.OnClickListener{
    private TextView serviceProviderName, serviceProviderType;
    private ImageView serviceProviderPhoto;
    private Button bookAppointment;
    private int mYear, mMonth, mDay;
    private int mHour, mMinute;
    private String theReason;
    private String confirmationMsg;
    private FragmentTabHost mTabHost;
    private String serviceType;
    private SessionManager session;
    //private static final int DATE_DIALOG_ID = 0; ignore this line
    //private static final int TIME_DIALOG_ID= 1; ignore this line
    //private static final int REASON_FOR_VISIT_VIEW = 2; ignore this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_service_details);
        getLayoutInflater().inflate(R.layout.activity_service_details, frameLayout);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Service Details");
        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            //User is already logged in. Hide the login button
            menuCondition = "UserLogin";
        }else{
            menuCondition="ServiceDetails";
        }

        serviceProviderPhoto = (ImageView)findViewById(R.id.service_provider_photo);
        serviceProviderName = (TextView)findViewById(R.id.service_provider_name);
        serviceProviderType = (TextView)findViewById(R.id.service_provider_type);
        bookAppointment = (Button) findViewById(R.id.service_regis_button);
        bookAppointment.setOnClickListener(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Profile"),
                ServiceDetails_Profile.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Reviews"),
                ServiceDetails_Reviews.class, null);
        mTabHost.setCurrentTab(0);
        iniTabColor();

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String arg0) {
                // TODO Auto-generated method stub
                for(int i=0;i < mTabHost.getTabWidget().getChildCount();i++) {
                    mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.unselectedTabColor));
                    TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#000000"));
                }
                mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.selectedTabColor));
                TextView tv = (TextView) mTabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#ffffff"));
            }
        });
    }

    public void iniTabColor(){
        // TODO Auto-generated method stub
        for(int i=0;i < mTabHost.getTabWidget().getChildCount();i++) {
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.unselectedTabColor));
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#000000"));
        }
        mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.selectedTabColor));
        TextView tv = (TextView) mTabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#ffffff"));
    }

    public void addTabDecision(){
        if(serviceType.equals("volunteer")){
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab1").setIndicator("Profile"),
                    ServiceDetails_Profile.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab2").setIndicator("Reviews"),
                    ServiceDetails_Reviews.class, null);
        }else{
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab1").setIndicator("Profile"),
                    ServiceDetails_Profile.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab2").setIndicator("Procedures & Prices"),
                    ServiceDetails_ProceduresPrices.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab3").setIndicator("Reviews"),
                    ServiceDetails_Reviews.class, null);
        }
    }


    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            // Book Appointment Button
            case R.id.service_regis_button:
                pickDate();
                break;
        }
    }

    public void pickDate(){
        // get the current date and time
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        //txtDate.setText(dayOfMonth + "-"
                        //+ (monthOfYear + 1) + "-" + year);
                        //when the user pick the date, capture the new
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        pickTime();
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }


    public void pickTime(){
        //get current hour and minutes
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // Display Selected time in textbox
                        //txtTime.setText(hourOfDay + ":" + minute);
                        //when the user pick the time, capture the time using mHour and mMinute
                        mHour = hourOfDay;
                        mMinute = minute;
                        reasonForVisit();
                    }
                }, mHour, mMinute, false);
        tpd.show();
    }

    public void reasonForVisit(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.reason_for_visit_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText reason = (EditText) dialogView.findViewById(R.id.reason_for_visit_field);

        dialogBuilder.setTitle("Book Appointment");
        dialogBuilder.setMessage("Please enter your reason for visit");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                theReason = reason.getText().toString();
                serviceBookingConfirmation();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void serviceBookingConfirmation(){
        //Prepare for confirmation msg
        Calendar temp = Calendar.getInstance();
        temp.set(mYear,mMonth,mDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String appointmentDate = sdf.format(temp.getTime());
        String finalMinute = "00";
        if(mMinute < 10){
            finalMinute = "0"+ mMinute;
        } else {
            finalMinute = String.valueOf(mMinute);
        }
        String appointmentTime = mHour + ":" + finalMinute + ":" + "00";

        confirmationMsg = "Your appointment information is as followed: \n\n" + appointmentDate + "  " + appointmentTime +
                "\n\nReason for visit: " + theReason;
        //construct a dialogbox
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle("Book Appointment");
        dialogBuilder.setMessage(confirmationMsg);
        dialogBuilder.setPositiveButton("Send Appointment", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do the email sending logic

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }

}
