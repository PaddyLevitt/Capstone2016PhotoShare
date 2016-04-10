package BackEnd;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import com.capstone.photoshare.ProfileActivity;
import java.util.concurrent.ExecutionException;

/**
 * Created by Lee Mills on 1/30/2016.
 * This class is designed to handle login parameters on a background thread.
 */

public class Login extends AsyncTask<Void, Void, String> {
    public final static String JSONSTRING = "com.capstone.photoshare.JASONSTRING";
    private String userName;
    private String password;
    private ProgressBar progressBar;

    //Constructor
    public Login(String userName, String password, ProgressBar progressBar) {
        this.userName = userName;
        this.password = password;
        this.progressBar = progressBar;
    }

    //Method executes on background thread and returns a string representation of a JSON object
    protected String doInBackground(Void... params) {
        ServerRequest serverRequest = new ServerRequest(userName, password);
        return serverRequest.getLoginJSON();
    }

    //Method executes prior to doInBackground
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE); //progress bar set visible while waiting on DB response
    }

    //Checks if login is successful
    public boolean loginResults(Context context) {
        boolean loginSuccess = false;

        try {
            if (this.get() == null || this.get().equals("error")) {//"error" string is returned from Node.js function
                String warning = "The user name or password you entered is incorrect!!";
                new WarningDialog(context, warning);
                progressBar.setVisibility(View.INVISIBLE);
            }
            else {//Launches profile screen if login works and passes JSON object string to ProfileActivity
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra(JSONSTRING, this.get());
                context.startActivity(intent);
                loginSuccess = true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return loginSuccess;
    }
}
