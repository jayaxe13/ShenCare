package com.shencare.shencaremobile;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VolunteerSignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText name,email,phone_number, message;
    private Button volSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_sign_up);
        volSignUp = (Button)findViewById(R.id.joinVolunteer);
        volSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch(id){
            case R.id.joinVolunteer:
                startActivity(new Intent(this,Home.class));
                final Toast volSuccessToast = Toast.makeText(getBaseContext(), getString(R.string.successMessage), Toast.LENGTH_LONG);
                //*Set the position of the Toast box to the center of the UI
                volSuccessToast.setGravity(Gravity.CENTER, 0, 0);
                volSuccessToast.show();
                new CountDownTimer(4000, 1000)
                {

                    public void onTick(long millisUntilFinished) {volSuccessToast.show();}
                    public void onFinish() {volSuccessToast.cancel();}

                }.start();
                break;
        }

    }
}
