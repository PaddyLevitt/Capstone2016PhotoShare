package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the registration screen
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import BackEnd.Registration;

public class RegisterActivity extends AppCompatActivity {

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
        String name = editText1.getText().toString();
        String userName = editText2.getText().toString();
        String password = editText3.getText().toString();
        String confirmPassword = editText4.getText().toString();
        String email = editText5.getText().toString();

        //Executes registration in background
        Registration myRegistration = new Registration(name, userName, password, confirmPassword, email);

        //Displays registration warning or success message
        if (!myRegistration.generateRegisterWarning(this)) {
            try {
                myRegistration.execute();
                TextView textView = (TextView) findViewById(R.id.registerMessage);
                textView.setText(myRegistration.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
