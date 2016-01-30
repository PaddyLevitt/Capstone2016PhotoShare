package com.capstone.photoshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
        When the login button is clicked this method takes the password and user name found in the MockData class and
        checks it against what is entered on the unfinished login screen(Main activity).
        If the data matches the "Hello World" text changes to read "Success" else it changes
        to read "Failed". The check is done using a background thread with the login class via
        an AsyncTask extension. This background threading will be critical when accessing the live DB.
     */
    public void sendMessage(View view) {
        //From user name field
        EditText editText = (EditText) findViewById(R.id.userNameText);
        String userName = editText.getText().toString();

        //From password field
        EditText editText2 = (EditText) findViewById(R.id.passwordText);
        String password = editText2.getText().toString();

        Login login = new Login(userName, password);
        login.execute(); //must be called to execute protected doInBackground method

        String result = "Failed";

        try {
            if(login.get()) //returns boolean result from doInbackground method
                result = "Success";
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(result);
    }
}
