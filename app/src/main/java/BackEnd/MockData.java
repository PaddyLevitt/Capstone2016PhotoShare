package BackEnd;

import java.util.ArrayList;

/**
 * Created by Lee Mills on 1/30/2016.
 * This class is for temporary Mock Data for testing purposes only and
 * will be replaced by data from the DB when active.
*/
    public class MockData {

    public ArrayList<UserProfile> tempProfileList;

    public MockData () {
        tempProfileList = new ArrayList<>();
        tempProfileList.add(new UserProfile("Bill", "B1", "bpw", "billy@yahoo.com"));
        tempProfileList.add(new UserProfile("Jill", "J1", "jpw", "jilly@yahoo.com"));
    }

}
