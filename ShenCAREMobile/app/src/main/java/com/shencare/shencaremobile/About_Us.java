package com.shencare.shencaremobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class About_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);

        TextView content = (TextView)findViewById(R.id.aboutUs_content);
        //Spanned result = Html.fromHtml(readTxt());
        String result = getString(R.string.aboutUs_content);
        content.setText(Html.fromHtml(result));
    }

    private String readTxt(){

        InputStream inputStream = getResources().openRawResource(R.raw.aboutus);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try{
            i = inputStream.read();
            while(i != -1)
            {
                byteArrayOutputStream.write(i);
                i =inputStream.read();
            }
        }catch (IOException e){
            //TODO auto-generated catch block
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }
}
