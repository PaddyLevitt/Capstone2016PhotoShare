package BackEnd;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Lee Mills on 2/1/2016.
 * This class is designed to create a new user profile and do some error checking prior to trying to push the profile to the DB
 */
public class Registration extends AsyncTask<Void, Void, String> {

    //Represents minimum password length
    private static final int passwordLength = 4;

    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String emailAddress;

    //Constructor
    public Registration(String name, String username, String password, String confirmPassword, String emailAddress) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.emailAddress = emailAddress;
    }

    //Attempts to push registration to DB and returns results
    protected String doInBackground(Void... params) {

        ServerRequest serverRequest = new ServerRequest(name, username, password, emailAddress);
        return serverRequest.pushRegister();
    }

    //Checks if password and confirmed password are equal
    private boolean confirmPassword() {
        return password.equals(confirmPassword) && !password.equals("") && !confirmPassword.equals("");
    }

    //Checks for spaces in userName
    private boolean userNameHasSpaces() {
        return username.contains(" ");
    }

    //Checks for spaces in Password
    private boolean passwordHasSpaces() {
        return password.contains(" ");
    }

    //Checks if password meets minimum length set in static variable
    private boolean passwordLength() {
        return password.length() < passwordLength;
    }

    //Checks if name and username fields are empty
    private boolean fullFields () {
        return name.equals("") || username.equals("");
    }

    //Checks if email format is correct
    private boolean emailFormat () {
        //This top line of code works on app, but breaks unit test for some reason
        return emailAddress != null && android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();

       // return emailAddress != null && emailAddress.contains("@") && emailAddress.contains(".com");//this code and unit test are friends
    }

    /*
    Generate applicable warning dialog based on invalid registration info, in sequence with text fields
    Context is the host Activity calling this method
    */
    public boolean generateRegisterWarning(Context context) {

        if (fullFields()) {
            String warning = "Name and User Name are Required!!";
            new WarningDialog(context, warning);
            return true;
        }
        else if (userNameHasSpaces()) {
            String warning = "User Name cannot contain spaces!!";
            new WarningDialog(context, warning);
            return true;
        }
        else if (passwordHasSpaces()) {
            String warning = "Password cannot contain spaces!!";
            new WarningDialog(context, warning);
            return true;
        }
        else if (passwordLength()) {
            String warning = "Password should be atleast " + passwordLength + " characters!!";
            new WarningDialog(context, warning);
            return true;
        }
        else if (!confirmPassword()) {
            String warning = "Password and Confirmed Password DO NOT match!!";
            new WarningDialog(context, warning);
            return true;
        }
        else if (!emailFormat()) {
            String warning = "Incorrect email format!!";
            new WarningDialog(context, warning);
            return true;
        }
        else
            return false;
    }
}
