package com.capstone.photoshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import BackEnd.Login;
import BackEnd.UrlRoutes;

/* Created by Lee K. Mills.
 * This class represents the user profile screen
 */

public class ProfileActivity extends AppCompatActivity implements UrlRoutes{
    public final static String USERNAME = "com.capstone.photoshare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView1 = (TextView) findViewById(R.id.profileScreenName);
        TextView textView2 = (TextView) findViewById(R.id.profileScreenUserName);
        TextView textView3 = (TextView) findViewById(R.id.profileScreenEmail);


        //Passes user profile string from loginResults function called in main activity
        Intent intent = getIntent();
        String jsonstr = intent.getStringExtra(Login.JSONSTRING);

        try {//JSON object parameters displayed on profile screen
            JSONObject user = new JSONObject(jsonstr);
            textView1.setText(user.getString("name"));
            textView2.setText(user.getString("username"));
            textView3.setText(user.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Passes username to PictureGridActivity
    public void showPhotos(View view) {
        TextView textView = (TextView) findViewById(R.id.profileScreenUserName);
        Intent intent = new Intent(this, PhotoAlbumActivity.class);
        intent.addFlags(1);//Flag added to specify this intent versus the intent created in the FriendsListActivity class
        intent.putExtra(USERNAME, textView.getText());
        startActivity(intent);
    }

    //Passes username to FriendsListActivity
    public void showFriends(View view) {
        TextView textView = (TextView) findViewById(R.id.profileScreenUserName);
        Intent intent = new Intent(this, FriendsListActivity.class);
        intent.putExtra(USERNAME, textView.getText());
        startActivity(intent);
    }
}
