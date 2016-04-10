package BackEnd;

/**
 * Created by Lee Mills on 2/13/2016.
 * Interface for listing UrlRoutes that route to Node.js framework scripts
 */
public interface UrlRoutes {

    //Urls for Lee Mills local setup
    String myIP = "192.168.1.66";
    String UrlUserLogin = "http://" + myIP + ":8080/users/login";
    String UrlRegister = "http://" + myIP + ":8080/users/register";
    String UrlPic = "http://" + myIP + ":8080/users/picToApp";
    String Urlcollection = "http://" + myIP + ":8080/collection";
    String UrlcreateAlbum = "http://" + myIP + ":8080/users/createAlbum";
}
