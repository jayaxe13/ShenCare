package com.shencare.shencaremobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {
    private Button editButton;
    private TextView username, name, surname, gender,contact,email,address,pot,mpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editButton = (Button) findViewById(R.id.edit_profile_button);
        editButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            case R.id.edit_profile_button:
                startActivity(new Intent(this,UserProfileEdit.class));
                break;
        }

    }
}
