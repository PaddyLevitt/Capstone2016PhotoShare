package BackEndUnitTests;

import org.junit.Test;
import BackEnd.UserProfile;
import static org.junit.Assert.*;

/**
 * Created by Lee Mills on 1/25/2016.
 * UserProfile class test
 */
public class UserProfileTest {

    @Test
    public void testConstructor() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        assertNotNull(instance);
    }

    @Test
    public void testGetName() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        assertEquals(instance.getName(), "Bill Bobby");
    }

    @Test
    public void testSetName() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        instance.setName("anything");
        assertEquals(instance.getName(), "anything");
    }

    @Test
    public void testGetUserName() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        assertEquals(instance.getUserName(), "userName");
    }

    @Test
    public void testSetUserName() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        instance.setUserName("anything");
        assertEquals(instance.getUserName(), "anything");
    }

    @Test
    public void testGetPassword() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        assertEquals(instance.getPassword(), "password");
    }

    @Test
    public void testSetPassword() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        instance.setPassword("anything");
        assertEquals(instance.getPassword(), "anything");
    }

    @Test
    public void testGetEmailAddress() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        assertEquals(instance.getEmailAddress(), "bbobby@yahoo.com");
    }

    @Test
    public void testSetEmailAddress() throws Exception {
        UserProfile instance = new UserProfile("Bill Bobby", "userName", "password", "bbobby@yahoo.com");
        instance.setEmailAddress("anything");
        assertEquals(instance.getEmailAddress(), "anything");
    }
}