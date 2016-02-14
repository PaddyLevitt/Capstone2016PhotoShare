package BackEndUnitTests;

import android.content.Context;
import android.widget.ProgressBar;
import org.junit.Test;
import BackEnd.Login;
import static org.junit.Assert.*;

/**
 * Created by Lee Mills on 2/13/2016.
 * Login class Test
 */
public class LoginTest {

    @Test
    public void testConstructor() throws Exception {
        Context context = null;
        Login instance = new Login("username", "password", new ProgressBar(context));
        assertNotNull(instance);
    }
}