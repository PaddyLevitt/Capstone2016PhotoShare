package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the user photo albums listview
 */

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
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
import BackEnd.PhotoAlbumAdapter;
import BackEnd.ServerRequest;
import BackEnd.WarningDialog;

public class PhotoAlbumActivity extends AppCompatActivity {
    public final static String COLLECTION = "com.capstone.photoshare_1";
    public final static String USERNAME = "com.capstone.photoshare_2";
    public final static String ALBUM = "com.capstone.photoshare_3";
    private String albumCollection;
    private JSONArray jsonArray;
    private String newAlbumName;
    private String username;
    private String collection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        username = intent.getStringExtra(ProfileActivity.USERNAME);
        albumCollection = username + "Albums";

        loadAdapter();
    }

    private void loadAdapter() {
        setContentView(R.layout.activity_photo_album);
        userAlbums albums = new userAlbums();
        albums.execute();

        try {
            jsonArray = albums.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (!jsonArray.isNull(0)) {
            ListView listView = (ListView) findViewById(R.id.albumListView);
            listView.setAdapter(new PhotoAlbumAdapter(this, jsonArray));


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    Intent intent = new Intent(PhotoAlbumActivity.this, PictureGridActivity.class);

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

    public void createAlbum(View view) {
        showAddALbumDialog();
    }

    private String formatNewAlbumName(String name) {
        return name.replace(' ', '+');
    }

    private String createCollectionName(String name) {
        return name.replace(' ', '_');
    }

    private class userAlbums extends AsyncTask<Void, Void, JSONArray> {
        private JSONArray jsonArray;

        protected JSONArray doInBackground(Void... params) {

            ServerRequest sr = new ServerRequest(albumCollection);
            jsonArray = sr.getCollection();

            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray.isNull(0))
                setContentView(R.layout.blank_album_list);
        }
    }

    private class addAlbum extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... params) {
            String result;

            ServerRequest sr = new ServerRequest(newAlbumName, username, collection);
            result = sr.pushNewAlbum();

            return result;
        }
    }

}