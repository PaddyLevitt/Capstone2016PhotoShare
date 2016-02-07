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
import BackEnd.WarningDialog;

public class RegisterActivity extends AppCompatActivity {

    //Represents minimum password length
    private static final int passwordLength = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //For now just a mock test of register fields, button and background thread
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
        String password= editText3.getText().toString();
        String confirmPassword = editText4.getText().toString();
        String email = editText5.getText().toString();

        if(generateRegisterWarning(name, userName, password, confirmPassword, email)) { // Generate warning and returns from method
            return;
        }

        //Executes registration in background
        Registration myRegistration = new Registration(name, userName, password, email);
        myRegistration.execute();

        //Temp for testing, puts new user name on screen
        try {
            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText(myRegistration.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();

        }
    }

    //Checks if password and confirmed password are equal
    private boolean confirmPassword(String password, String confirmed) {
        return password.equals(confirmed) && !password.equals("") && !confirmed.equals("");
    }

    //Checks if password meets minimum length
    private boolean passwordLength(String password) {
        return password.length() < passwordLength;
    }

    //Checks if name and username fields are empty or null
    private boolean fullFields (String name, String username) {
        return name.equals("") || username.equals("");
    }

    //Checks if email format is correct
    private boolean emailFormat (String email) {
        return email!=null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //Generate applicable warning dialog based on invalid registration info, in sequence with text fields
    private boolean generateRegisterWarning(String name, String username, String password, String confirmPassword, String email) {

        if (fullFields(name, username)) {
            String warning = "Name and User Name are Required!!";
            new WarningDialog(this, warning);
            return true;
        }
        else if (passwordLength(password)) {
            String warning = "Password should be atleast 8 characters!!";
            new WarningDialog(this, warning);
            return true;
        }
        else if (!confirmPassword(password, confirmPassword)) {
            String warning = "Password and Confirmed Password DO NOT match!!";
            new WarningDialog(this, warning);
            return true;
        }
        else if (!emailFormat(email)) {
            String warning = "Incorrect email format!!";
            new WarningDialog(this, warning);
            return true;
        }
        else
            return false;
    }
}
