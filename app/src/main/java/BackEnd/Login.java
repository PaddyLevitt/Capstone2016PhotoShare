package BackEnd;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import com.capstone.photoshare.MainActivity;
import com.capstone.photoshare.ProfileActivity;
import java.util.concurrent.ExecutionException;

/**
 * Created by Lee Mills on 1/30/2016.
 * This class is designed to handle login parameters on a background thread.
 */

public class Login extends AsyncTask<Void, Void, Integer> { //AsyncTask provides easy use of threads as to not interrupt main UI thread

    private String userName;
    private String password;
    private ProgressBar progressBar;
    private Context context;

    public Login(String userName, String password, ProgressBar progressBar) {
        this.userName = userName;
        this.password = password;
        this.progressBar = progressBar;
    }

    public Login(Context context, ProgressBar progressBar) {//Used to test phone to node with URL no login parameters needed
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override //Method executes in Background thread when execute method is called, this method is not called directly
    protected Integer doInBackground(Void... params) { // Just to test mock data (make sure the AsyncTask 3rd param is Integer)
        int index = -1;

        //MocK Data that is instantiated in Main Activity , search will be on backend server
        for(UserProfile temp : MainActivity.tempData.tempProfileList)
            if (userName.equals(temp.getUserName()) && password.equals(temp.getPassword()))
               index =  MainActivity.tempData.tempProfileList.indexOf(temp);

        return index;
    }

    public boolean loginResults(Context context) {//Context is the host Activity calling this method (make sure the AsyncTask 3rd param is Integer)
        boolean loginSuccess = false;

        try {
            if (this.get() != -1) {//returns result from doInbackground method, will eventually return JSON object or null???
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("index", this.get());//temp code to pass mock data array index to ProfileActivity
                context.startActivity(intent);
                loginSuccess = true;
            }
            else {//Dialog box warning when login in incorrect
                String warning = "The user name or password you entered is incorrect!!";
                new WarningDialog(context, warning);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return loginSuccess;
    }

    //Method executes prior to doInBackground, this method is not called directly
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE); //progress bar set visible while waiting on DB response
    }


    //Methods for testing Phone to Node script passing Url parameters (Was successful) (make sure the AsyncTask 3rd param is String)
    /*    protected String doInBackground(Void... params) {
            ServerRequest serverRequest = new ServerRequest();
            String urlParams = serverRequest.returnUrlParams();
            return urlParams;
        }


        protected void onPostExecute(String result) {//puts url params onto login screen, no password needed, just a test
            new WarningDialog(context, result);
        }*/

}
