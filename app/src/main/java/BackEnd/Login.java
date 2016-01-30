package BackEnd;

import android.os.AsyncTask;

/**
 * Created by Lee Mills on 1/30/2016.
 * This class is designed to handle login parameters on a background thread.
 */
public class Login extends AsyncTask <Void, Void, Boolean>{ //AsyncTask provides easy use of threads as to not interrupt main UI thread

    private String userName;
    private String password;

    public Login(String userName, String password) { //parameters will be passed from login screen text when login button is pressed
        this.userName = userName;
        this.password = password;
    }
    @Override
    protected Boolean doInBackground(Void... params) { // method executes thread that is done in background separate from main UI thread (will matter when DB is being accessed)

        MockData tempData = new MockData(); //will be replaced by the DB connection when DB becomes active (maybe a separate class???))

        for(UserProfile temp : tempData.tempProfileList)  //will be replaced by searching database for login credentials when DB becomes active (maybe a separate class??)
            if (userName.equals(temp.getUserName()) && password.equals(temp.getPassword()))
                return true;

        return false;
    }

}
