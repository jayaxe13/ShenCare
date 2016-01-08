package com.shencare.shencaremobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class About_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);

        WebView content = (WebView)findViewById(R.id.aboutUs_content);
        //Spanned result = Html.fromHtml(readTxt());
        String result = getString(R.string.aboutUs_txt);
        content.loadData(result, "text/html", "utf-8");
        //content.setText(Html.fromHtml(result));
    }
}
