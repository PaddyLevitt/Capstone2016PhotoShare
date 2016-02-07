package BackEnd;

import android.os.AsyncTask;
import com.capstone.photoshare.MainActivity;

/**
 * Created by Lee Mills on 2/1/2016.
 * This class is designed to create a new user profile and push the profile to the DB on a background thread
 */
public class Registration extends AsyncTask<Void, Void, String> {

    private UserProfile newProfile;

    public Registration(String name, String userName, String password, String emailAddress) {
        newProfile = new UserProfile(name, userName, password, emailAddress);
        MainActivity.tempData.tempProfileList.add(newProfile); //mock data for now
    }

    protected String doInBackground(Void... params) {

        //Code here to connect to DB and push Register object to database

        return MainActivity.tempData.tempProfileList.get(MainActivity.tempData.tempProfileList.size() - 1).getName(); //mopck data for now
    }
}
