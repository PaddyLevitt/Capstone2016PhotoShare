package com.capstone.photoshare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
import BackEnd.PhotoAlbumAdapter;
import BackEnd.ServerRequest;
import BackEnd.WarningDialog;

/* Created by Lee K. Mills.
 * This class represents the Photo album collection screen
 */

public class PhotoAlbumActivity extends AppCompatActivity {
    public final static String COLLECTION = "com.capstone.photoshare_1";
    public final static String USERNAME = "com.capstone.photoshare_2";
    public final static String ALBUM = "com.capstone.photoshare_3";
    private String albumCollection;
    private JSONArray jsonArray;
    private String newAlbumName;
    private String username;
    private String collection;
    private int origin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        origin = intent.getFlags();//Gets flag from intent that represents if the intent came for ProfileActivity (1) or FriendListActivity (2)

        if (origin == 1)
            username = intent.getStringExtra(ProfileActivity.USERNAME);

        if (origin == 2)
            username = intent.getStringExtra(FriendsListActivity.USERNAME);

        albumCollection = username + "Albums";

        loadAdapter();
    }

    //Sets the adapter to be used with the photo album list
    private void loadAdapter() {
        if (origin == 1)
            setContentView(R.layout.activity_photo_album);

        if (origin == 2)
            setContentView(R.layout.friend_photo_album);

        TextView textView = (TextView)findViewById(R.id.userAlbums);
        textView.setText(username + "'s Album Collection");

        userAlbums albums = new userAlbums();
        albums.execute();

        try {
            jsonArray = albums.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (!jsonArray.isNull(0)) {//If collection exists
            ListView listView = (ListView) findViewById(R.id.albumListView);
            listView.setAdapter(new PhotoAlbumAdapter(this, jsonArray));


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    Intent intent = new Intent(PhotoAlbumActivity.this, PictureGridActivity.class);
                    if(origin == 1) intent.addFlags(1);//If this activity was called from ProfileActivity
                    if(origin == 2) intent.addFlags(2);//If this activity was called from FriendListActivity

                    try {
                        String collection = jsonArray.getJSONObject(position).getString("collection");
                        String album = jsonArray.getJSONObject(position).getString("name");
                        intent.putExtra(COLLECTION, collection);
                        intent.putExtra(ALBUM, album);
                        intent.putExtra(USERNAME, username);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //Dialog for adding a new Photo album
    protected void showAddALbumDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(PhotoAlbumActivity.this);
        View promptView = layoutInflater.inflate(R.layout.add_album_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PhotoAlbumActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.enterAlbumName);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        newAlbumName = editText.getText().toString();
                        collection = createCollectionName(newAlbumName);
                        newAlbumName = formatNewAlbumName(newAlbumName);
                        addAlbum album = new addAlbum();
                        album.execute();

                        try {
                            if (album.get().equals("success")) {
                                loadAdapter();
                                new WarningDialog(PhotoAlbumActivity.this, "Album creation successful!");
                            }
                            else
                                new WarningDialog(PhotoAlbumActivity.this, album.get());

                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    //Create album button event
    public void createAlbum(View view) {
        showAddALbumDialog();
    }

    //Formats string of new album name to handle spaces when sent in URL route
    private String formatNewAlbumName(String name) {
        return name.replace(' ', '+');
    }

    //Formats string of new album name to create a collection name in MongoDB
    private String createCollectionName(String name) {
        return name.replace(' ', '_');
    }

    //Private class to return collection of the user's photo album list
    private class userAlbums extends AsyncTask<Void, Void, JSONArray> {
        private JSONArray jsonArray;

        protected JSONArray doInBackground(Void... params) {

            ServerRequest sr = new ServerRequest(albumCollection);
            jsonArray = sr.getCollection();

            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {//If collection does not exist
            if (jsonArray.isNull(0) && origin == 1)
                setContentView(R.layout.blank_album_list);

            if (jsonArray.isNull(0) && origin == 2)
                setContentView(R.layout.friend_empty_album);
        }
    }

    //Private class to push new album to applicable collection
    private class addAlbum extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... params) {
            String result;

            ServerRequest sr = new ServerRequest(newAlbumName, username, collection);
            result = sr.pushNewAlbum();

            return result;
        }
    }

}