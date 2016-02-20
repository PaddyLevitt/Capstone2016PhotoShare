package BackEnd;

/**
 * Created by Lee Mills on 2/13/2016.
 * Interface for listing UrlRoutes
 */
public interface UrlRoutes {

    //Urls for Lee Mills local setup
    String myIP = "192.168.1.66"; //Leave empty until testing at home cause this is public on Github.
    String UrlUserLogin = "http://" + myIP + ":8080/users/login";
    String UrlRegister = "http://" + myIP + ":8080/users/register";
}
