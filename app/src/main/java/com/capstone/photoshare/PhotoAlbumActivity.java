package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the user photo albums listview
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
import BackEnd.PhotoAlbumAdapter;
import BackEnd.ServerRequest;

public class PhotoAlbumActivity extends AppCompatActivity {
    private String albumCollection;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_album);

        Intent intent = getIntent();
        albumCollection = intent.getStringExtra("username") + "Albums";

        userAlbums albums = new userAlbums();
        albums.execute();

        try {
            jsonArray = albums.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.albumListView);
        listView.setAdapter(new PhotoAlbumAdapter(this, jsonArray));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intent = new Intent(PhotoAlbumActivity.this, PictureGridActivity.class);

                try {
                    String collection = jsonArray.getJSONObject(position).getString("collection");
                    intent.putExtra("collection", collection);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class userAlbums extends AsyncTask<Void, Void, JSONArray> {
        private JSONArray jsonArray;

        protected JSONArray doInBackground(Void... params) {

            ServerRequest sr = new ServerRequest(albumCollection);
            jsonArray = sr.getCollection();

            return jsonArray;
        }
    }
}