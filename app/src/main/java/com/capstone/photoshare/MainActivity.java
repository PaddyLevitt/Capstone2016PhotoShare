package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the initial login screen
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutionException;
import BackEnd.Login;
import BackEnd.MockData;
import BackEnd.WarningDialog;

public class MainActivity extends AppCompatActivity {

    public static MockData tempData = new MockData(); //temp mock data for testing until DB is active

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Login button event, will eventually connect to DB
    public void sendMessage(View view) {

        EditText editText = (EditText) findViewById(R.id.userNameText);
        String userName = editText.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.passwordText);
        String password = editText2.getText().toString();

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loginProgressBar);

        Login login = new Login(userName, password, progressBar);
        login.execute(); //must be called to execute protected doInBackground method in login class

        try {
            if(login.get()) {//returns boolean result from doInbackground method, will eventually return JSON object or null???
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
            else {//Dialog box warning when login in incorrect
                String warning = "The user name or password you entered is incorrect!!";
                new WarningDialog(this, warning);
                //progressBar.setVisibility(View.INVISIBLE);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    //Launches Register screen
    public void launchRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
