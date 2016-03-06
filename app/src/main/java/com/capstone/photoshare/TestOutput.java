package com.capstone.photoshare;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import BackEnd.ServerRequest;
import BackEnd.UrlRoutes;

public class TestOutput extends AppCompatActivity implements UrlRoutes{//Class just used to to test different outputs from DB queries or anything really.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_output);

        TextView textView = (TextView) findViewById(R.id.testOutput);
        ServerStuff st = new ServerStuff();
        st.execute();

        JSONArray jsonArray;

        try {
            jsonArray = st.get();
            JSONObject jsonObject = (JSONObject) jsonArray.get(5);
            String id = UrlPic + "?objID=" + jsonObject.getString("_id");
            textView.setText(id);

        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }

    }

    private class ServerStuff extends AsyncTask <Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... params) {
            ServerRequest sr = new ServerRequest("nothing");
            return  sr.getCollection();
        }
    }
}
