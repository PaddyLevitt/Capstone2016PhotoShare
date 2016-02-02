package BackEnd;

import android.os.AsyncTask;
import android.view.View;

/**
 * Created by Lee Mills on 2/1/2016.
 * This class is designed to create a new user profile and push the profile to the DB on a background thread
 */
public class Registration extends AsyncTask<Void, Void, String> {

    private UserProfile newProfile;

    public Registration(String name, String userName, String password, String emailAddress) {
        newProfile = new UserProfile(name, userName, password, emailAddress);
    }

    protected String doInBackground(Void... params) {

        //Code here to connect to DB and push Register object to database

        return newProfile.getName(); // test that this method is doing something in background
    }
}
