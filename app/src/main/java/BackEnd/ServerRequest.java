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

public class ServerRequest implements UrlRoutes{

    private String name;
    private String username;
    private String password;
    private String email;
    private String collection;
    private String albumName;
    private String friendUserName;

    //Constructor to be used with login class and FriendsListActivity class
    public ServerRequest(String param1, String param2) {
        this.username = param1;
        this.password = param2;
        this.friendUserName = param2;
    }

    //Constructor to be used with register class
    public ServerRequest(String name,  String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Constructor to be used with PictureGridActivity class and FriendsListActivity class
    public ServerRequest (String param) {
        this.collection = param;
        this.email = param;
    }

    //Constructor to be used with PhotoAlbumActivity class
    public ServerRequest (String albumName, String username, String collection) {
        this.albumName = albumName;
        this.username = username;
        this.collection = collection;
    }


    //Push new photo album parameters
    public String pushNewAlbum() {
        String status = "";

        //Url connection and input stream objects
        HttpURLConnection  urlConnection = null;
        InputStream in;

        try {
            // Construct the URL object
            URL url = new URL(UrlcreateAlbum + "?name=" + albumName + "&username=" + username + "&collection=" + collection);

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

    //Push new friend to friend list
    public String pushNewFriend() {
        String status = "";

        //Url connection and input stream objects
        HttpURLConnection  urlConnection = null;
        InputStream in;

        try {
            // Construct the URL object
            URL url = new URL(UrladdFriend + "?username=" + username + "&friendName=" + friendUserName);

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

    //Login returns error or string representation of requested JSON object
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
        JSONArray coll = null;

        //Url connection and input stream objects
        HttpURLConnection  urlConnection = null;
        InputStream in;

        try {
            // Construct the URL object
            URL url = new URL(Urlcollection + "?collection=" + collection);

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Construct and pass input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
            String jsonArray = readStream(in);
            coll = new JSONArray(jsonArray);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return coll;
    }

    //Returns string result of searching for a user by email address
    public String findUserByEmail() {
        String result = "";

        //Url connection and input stream objects
        HttpURLConnection  urlConnection = null;
        InputStream in;

        try {
            // Construct the URL object
            URL url = new URL(UrlfindUser + "?email=" + collection);//collection parameter represents email for this method

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Construct and pass input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
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
