package com.capstone.photoshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import BackEnd.Registration;

/* Created by Lee K. Mills.
 * This class represents the registration screen
 */

public class RegisterActivity extends AppCompatActivity {
    private String name;
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void registerButton(View view) {

        //Text fields
        EditText editText1 = (EditText) findViewById(R.id.fullName);
        EditText editText2 = (EditText) findViewById(R.id.userName);
        EditText editText3 = (EditText) findViewById(R.id.password);
        EditText editText4 = (EditText) findViewById(R.id.confirmPassword);
        EditText editText5 = (EditText) findViewById(R.id.email);

        //Input from text fields
        name = editText1.getText().toString();
        userName = editText2.getText().toString();
        password = editText3.getText().toString();
        confirmPassword = editText4.getText().toString();
        email = editText5.getText().toString();


        //Create registration object
        formatFullName();
        Registration myRegistration = new Registration(name, userName, password, confirmPassword, email);

        //Displays registration warning or success message
        if (!myRegistration.generateRegisterWarning(this)) {
            try {
                myRegistration.execute();//Performs registration in background
                TextView textView = (TextView) findViewById(R.id.registerMessage);
                textView.setText(myRegistration.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    //Replaces spaces with '+' in register fields so URL parameters are passed to DB correctly
    private void formatFullName() {
        this.name = name.replace(' ', '+');
    }
}
