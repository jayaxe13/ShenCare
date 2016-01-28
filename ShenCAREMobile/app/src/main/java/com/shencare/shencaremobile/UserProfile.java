package com.shencare.shencaremobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
}
