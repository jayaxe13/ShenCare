package com.shencare.shencaremobile.Service_Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.shencare.shencaremobile.R;

public class ServiceDetails_Profile extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_service_details__profile, container, false);
        //TextView tv = (TextView) v.findViewById(R.id.service_detail_profile);
        //tv.setText("Profile");
        return v;
    }
}
