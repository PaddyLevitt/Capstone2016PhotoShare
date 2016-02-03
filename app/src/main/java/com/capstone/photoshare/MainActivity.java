package com.capstone.photoshare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import BackEnd.Login;

public class MainActivity extends AppCompatActivity {

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

        Login login = new Login(userName, password);
        login.execute(); //must be called to execute protected doInBackground method

        try {
            if(login.get()) {//returns boolean result from doInbackground method, will eventuall return JSON object???
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            }
            else {//Dialog box warning when login in incorrect
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("The user name or password you entered is incorrect!!");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    //Method will launch Register screen
    public void launchRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
