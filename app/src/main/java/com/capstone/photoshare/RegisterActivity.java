package com.capstone.photoshare;

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

    //For now just a mock test of register fields, button and background thread
    public void registerButton(View view) {
        EditText editText1 = (EditText) findViewById(R.id.fullName);
        EditText editText2 = (EditText) findViewById(R.id.userName);
        EditText editText3 = (EditText) findViewById(R.id.password);
        EditText editText4 = (EditText) findViewById(R.id.email);

        String name = editText1.getText().toString();
        String userName = editText2.getText().toString();
        String password= editText3.getText().toString();
        String email = editText4.getText().toString();

        Registration myRegistration = new Registration(name, userName, password, email);

        myRegistration.execute();

        try {
            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText(myRegistration.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
