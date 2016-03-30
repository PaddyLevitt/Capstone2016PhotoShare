package com.capstone.photoshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import BackEnd.ServerRequest;
import BackEnd.UrlRoutes;

public class TestOutput extends AppCompatActivity implements UrlRoutes{//Class just used to to test different outputs from DB queries or anything really.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_output);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        TextView textView = (TextView) findViewById(R.id.testOutput);
        textView.setText(message);
    }
}
