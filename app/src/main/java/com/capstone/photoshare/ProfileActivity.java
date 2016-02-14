package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the user profile screen
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView1 = (TextView) findViewById(R.id.profileScreenName);
        TextView textView2 = (TextView) findViewById(R.id.profileScreenUserName);
        TextView textView3 = (TextView) findViewById(R.id.profileScreenEmail);

        //Just passes the mock data array index for now
        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);

        //Displays current user info from mock data array
        textView1.setText(MainActivity.tempData.tempProfileList.get(index).getName());
        textView2.setText(MainActivity.tempData.tempProfileList.get(index).getUserName());
        textView3.setText(MainActivity.tempData.tempProfileList.get(index).getEmailAddress());
    }


}
