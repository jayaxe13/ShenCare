package com.shencare.shencaremobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class UserProfileEdit extends AppCompatActivity implements View.OnClickListener{
    private Button saveButton;
    private EditText name,surname,contact,address,email;
    RadioButton male, female;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        saveButton = (Button) findViewById(R.id.save_profile_button);
        saveButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            case R.id.save_profile_button:
                startActivity(new Intent(this,UserProfile.class));
                Toast editProfileToast = Toast.makeText(getBaseContext(), "Changed has been saved.", Toast.LENGTH_LONG);
                //*Set the position of the Toast box to the center of the UI
                editProfileToast.setGravity(Gravity.CENTER, 0, 0);
                editProfileToast.show();
                break;
        }

    }
}
