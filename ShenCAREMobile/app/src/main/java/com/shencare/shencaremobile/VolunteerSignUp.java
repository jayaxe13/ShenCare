package com.shencare.shencaremobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VolunteerSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_sign_up);
    }

    public void joinAsVolunteer(View view) {
        String button_text;
        button_text = ((Button) view).getText().toString();
        if(button_text.equals("Join")) {
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
            //*To show users about login successful message after user is validated successfully
            //*Here, the Toast.LENGTH_LONG is the lasting time for the toast box. There is also Toast.LENGTH_SHORT and also can set the time to be 2000ms
            Toast loginToast = Toast.makeText(getBaseContext(), getString(R.string.successMessage), Toast.LENGTH_LONG);
            //*Set the position of the Toast box to the center of the UI
            loginToast.setGravity(Gravity.CENTER, 0, 0);
            loginToast.show();
        }

    }
}
