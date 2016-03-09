package com.capstone.photoshare;

/* Created by Lee K. Mills.
 * This class represents the user picture gridview
 */

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import BackEnd.ImageAdapter;
import BackEnd.ServerRequest;
import BackEnd.UrlRoutes;

public class PictureGridActivity extends AppCompatActivity implements UrlRoutes{
    private Drawable[] drawable;
    private JSONArray jsonArray;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_grid);

        //Grabs username from calling activity
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        getPicFromUrl pic = new getPicFromUrl();
        pic.execute();

        try {
            drawable = pic.get();//Sets drawable array to array returned by private class method
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, drawable));//pass array to gridview adapter

        //Show large picture of thumbnail click
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(PictureGridActivity.this, ShowPictureActivity.class);

                try {
                    JSONObject jsonObject = (JSONObject)jsonArray.get(position);
                    String path = jsonObject.getString("_id");
                    intent.putExtra("URL", UrlPic + "?objID=" + path);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Class creates and returns array of drawable objects from URLs in a collection
    private class getPicFromUrl extends AsyncTask<Void, Void, Drawable[]> {
        String urlString = "";
        JSONObject jsonObject = null;

        protected Drawable[] doInBackground(Void... params) {//returns drawable array
            HttpURLConnection urlConnection;
            URL url;

            ServerRequest sr = new ServerRequest(username + "Images");//Name of collection to request username is unique identifier
            jsonArray = sr.getCollection();
            Drawable pics[] = new Drawable[jsonArray.length()];

            for (int i = 0; i < pics.length; i++) {
                try {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    urlString = UrlPic + "?objID=" + jsonObject.getString("_id");

                    //DB connection
                    url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    //Stream from DB to drawable object
                    InputStream is = urlConnection.getInputStream();
                    pics[i] = Drawable.createFromStream(is, "src");

                    urlConnection.disconnect();

                } catch (Exception e) {
                    pics = null;
                }
            }
            return pics;
        }
    }
}
