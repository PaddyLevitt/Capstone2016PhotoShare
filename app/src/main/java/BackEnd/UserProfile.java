package BackEnd;


/**
 * Created by Lee K. Mills on 1/25/2016.
 * This class is designed to represent a user profile entity
 * with applicable attributes
 */
public class UserProfile {

    private String name;
    private String userName;
    private String password;
    private String emailAddress;

    public UserProfile(String name, String userName, String password, String emailAddress){
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
