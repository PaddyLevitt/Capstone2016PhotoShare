package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the initial login screen
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import BackEnd.MockData;

public class MainActivity extends AppCompatActivity {

    public static MockData tempData = new MockData(); //temp mock data for testing until DB is active

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Login button event
    public void loginButton(View view) {

     /*   EditText editText = (EditText) findViewById(R.id.userNameText);
        String userName = editText.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.passwordText);
        String password = editText2.getText().toString();

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);

        Login login = new Login(userName, password, progressBar);
        login.execute(); //must be called to execute protected methods in login class

        if(login.loginResults(this))//if login is successful close this activity and proceed to ProfileActivity
            finish();*/

        //Bypass login function to test other features
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    //Launches Register screen
    public void launchRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
