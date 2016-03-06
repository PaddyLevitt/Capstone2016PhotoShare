package BackEnd;

import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lee Mills on 2/13/2016.
 * Class for connection to the server
 */

public class ServerRequest implements UrlRoutes{//Interface UrlRoutes contains url routes...."Way..."

    String name ="";
    String username = "";
    String password = "";
    String email = "";
    String collection = "";


    public ServerRequest(String username, String password) {//Constructor to be used with login class
        this.username = username;
        this.password = password;
    }

    public ServerRequest(String name,  String username, String password, String email) {//Constructor to be used with register class
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public ServerRequest (String collection) {//Constructor to be used with PictureGridActivity class
        this.collection = collection; //This will be the name of the collection to return
    }

    //Push registration parameters to DB, returns status string
    public String pushRegister() {
        String status = "";

        //Url connection and input stream objects
        HttpURLConnection  urlConnection = null;
        InputStream in;

        try {
            // Construct the URL object
            URL url = new URL(UrlRegister + "?name=" + name + "&username=" + username + "&password=" + password + "&email=" + email);

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Construct and pass input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
            status = readStream(in);

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return status;
    }

    //Login returns error or requested JSON object
    public String getLoginJSON() {
        String request = "";

        //Url connection and input stream objects
        HttpURLConnection  urlConnection = null;
        InputStream in;

        try {
            // Construct the URL object
            URL url = new URL(UrlUserLogin + "?username=" + username + "&password=" + password);

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Construct and pass input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
            request = readStream(in);

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return request;
    }

    //Returns JSONArray of a collection
    public JSONArray getCollection() {
        JSONArray collection = null;

        //Url connection and input stream objects
        HttpURLConnection  urlConnection = null;
        InputStream in;

        try {
            // Construct the URL object
            URL url = new URL(Urlcollection);//Will need parameters of different collection names to be added to URL eventually

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Construct and pass input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
            String jsonArray = readStream(in);
            collection = new JSONArray(jsonArray);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return collection;
    }

    //Converts inputStream to a string
    private String readStream(InputStream inputStream) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder buffer = new StringBuilder();
        String result = "";

        if (inputStream == null) {
            result = "null";
        }

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (buffer.length() == 0) {
            result = "null";
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("PlaceholderFragment", "Error closing stream", e);
        }

        return result;
    }
}
