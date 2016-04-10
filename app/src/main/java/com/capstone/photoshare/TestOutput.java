package com.capstone.photoshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import BackEnd.UrlRoutes;

public class TestOutput extends AppCompatActivity implements UrlRoutes{//Class just used to to test different outputs from DB queries or anything really.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_output);

        Intent intent = getIntent();
        String album = intent.getStringExtra("message");


        TextView textView = (TextView) findViewById(R.id.testOutput);
        textView.setText(album);
    }
}
