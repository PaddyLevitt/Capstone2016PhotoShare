package com.capstone.photoshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
import BackEnd.FriendsListAdapter;
import BackEnd.ServerRequest;

/* Created by Lee K. Mills.
 * This class represents the Friend list screen
 */

public class FriendsListActivity extends AppCompatActivity {
    public final static String USERNAME = "com.capstone.photoshare_2";
    private String username;
    private JSONArray jsonArray;
    private String friendCollection;

    //Creates the instance of the friend list screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Gets username from calling activity
        Intent intent = getIntent();
        username = intent.getStringExtra(ProfileActivity.USERNAME);

        //creates the friend collection parameter
        friendCollection = username + "friendslist";

        loadAdapter();
    }

    //Sets the adapter to be used with the friend list
    private void loadAdapter() {
        setContentView(R.layout.friend_list_layout);
        TextView textView = (TextView)findViewById(R.id.userFriends);
        textView.setText(username + "'s Friends List");

        userFriends friends = new userFriends();
        friends.execute();

        try {
            jsonArray = friends.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (!jsonArray.isNull(0)) {//If friend list exists
            ListView listView = (ListView) findViewById(R.id.friendListView);
            listView.setAdapter(new FriendsListAdapter(this, jsonArray));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    Intent intent = new Intent(FriendsListActivity.this, PhotoAlbumActivity.class);
                    intent.addFlags(2);//Flag added to specify this intent versus the intent created in the ProfileActivity class

                    try {
                        String friendUserName = jsonArray.getJSONObject(position).getString("username");
                        intent.putExtra(USERNAME, friendUserName);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //Button to launch adding a friend to friend list
    public void addFriend (View view) {
        Intent intent = new Intent(FriendsListActivity.this, TestOutput.class);
        intent.putExtra("message", "This will be an add friend function.");
        startActivity(intent);
    }

    //Private class to return collection of the user's friend list
    private class userFriends extends AsyncTask<Void, Void, JSONArray> {
        private JSONArray jsonArray;

        protected JSONArray doInBackground(Void... params) {

            ServerRequest sr = new ServerRequest(friendCollection);
            jsonArray = sr.getCollection();

            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray.isNull(0))//If friend list does not exist
                setContentView(R.layout.empty_friend_list);
        }
    }
}
