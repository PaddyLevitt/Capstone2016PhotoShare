package com.capstone.photoshare;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;

public class ShowPictureActivity extends AppCompatActivity {

    //ImageView and drawable objects
    private ImageView imageView;
    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        imageView = (ImageView) findViewById(R.id.fullPicture);

        //Pulls in URL string from calling activity
        Intent intent = getIntent();
        String urlString = intent.getStringExtra("URL");

        //Passes url and performs private class methods
        getPicFromUrl picture = new getPicFromUrl(urlString);
        picture.execute();
    }

    //Closes this Activity
    public void closeText(View view) {
        finish();
    }

    //Class obtains drawable from URL and posts to imageview
    private class getPicFromUrl extends AsyncTask<Void, Void, Drawable> {
        String url;

        public getPicFromUrl(String  url) {
            this.url = url;
        }

        protected Drawable doInBackground(Void... params) {//returns drawable from url stream on background thread

            try {
                InputStream is = (InputStream) new URL(url).getContent();
                drawable = Drawable.createFromStream(is, "src");
            } catch (Exception e) {
                drawable = null;
            }
            return drawable;
        }

        @SuppressWarnings("deprecation")//handles API issues
        protected void onPostExecute(Drawable image) {//Posts results of drawable on UI imageview
            if (image != null) {
                if(Build.VERSION.SDK_INT >= 16)
                    imageView.setBackground(drawable);
                else
                    imageView.setBackgroundDrawable(drawable);

            }
        }
    }
}
