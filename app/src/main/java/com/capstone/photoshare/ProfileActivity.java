package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the user profile screen
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        //Temp turned off for testing showPhotos button
/*        //Passes user profile string from loginResults function called in main activity
        Intent intent = getIntent();
        String jsonstr = intent.getStringExtra("JSONString");


        try {//JSON object paramaters displayed on profile screen
            JSONObject user = new JSONObject(jsonstr);
            textView1.setText(user.getString("name"));
            textView2.setText(user.getString("username"));
            textView3.setText(user.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    //Passes URL to ShowPictureActivity and starts the activity
    public void showPhotos(View view) {
        Intent intent = new Intent(this, ShowPictureActivity.class);
        intent.putExtra("URL", "https://placeholdit.imgix.net/~text?txtsize=18&txt=image&w=120&h=120"); ////test url off web
        startActivity(intent);
    }
}
