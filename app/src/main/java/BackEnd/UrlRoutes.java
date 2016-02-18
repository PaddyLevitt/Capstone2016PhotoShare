package BackEnd;

/**
 * Created by Lee Mills on 2/13/2016.
 * Interface for listing UrlRoutes
 */
public interface UrlRoutes {

    //Urls for Lee Mills local setup
    String myIP = "192.168.1.66"; //Leave empty until testing at home cause this is public on Github.
    String UrlparamTest = "http://" + myIP + ":8080/users/urltest?id=1&token=leemills"; //parameters passing thru Node only no DB involved
    String UrlUserList = "http://" + myIP + ":8080/users/userlist"; //Currently returns a collection on local database with only 1 object
}
