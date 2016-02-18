package BackEnd;

import android.util.Log;
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

    public ServerRequest() {
        //Create object
    }

    //Method uses url route defined in interface UrlRoutes and returns a string representation of the requested JSON object
    public String getJSON() {

        //Url connection and reader objects
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String returnParams = "";

        try {
            // Construct the URL object
            URL url = new URL(UrlUserList);

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            if (inputStream == null) {
                returnParams = "Null input stream";
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if (buffer.length() == 0) {
               returnParams = "buffer length is 0";
            }
            returnParams = buffer.toString();

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // Something went wrong
            returnParams = "Catch block : (";
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }

        }return returnParams;
    }
}
