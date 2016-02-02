package com.shencare.shencaremobile.Service_Fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shencare.shencaremobile.R;

public class ServiceDetails_Reviews extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_service_details__reviews, container, false);
        TextView tv = (TextView) v.findViewById(R.id.service_detail_reviews);
        //tv.setText(this.getTag() + " Content");
        tv.setText("There are current no reviews available.");
        return v;
    }
}
