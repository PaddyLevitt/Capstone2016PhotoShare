package BackEndUnitTests;
import android.content.Context;
import org.junit.Test;
import BackEnd.Registration;
import static org.junit.Assert.*;

/**
 * Created by Lee Mills on 2/13/2016.
 * Registration class Test
 */
public class RegistrationTest {

    @Test
    public void testConstructor() throws Exception {
        Context context = null;
        Registration instance = new Registration("Bill", "B1", "password", "password", "billy@Yahoo.com");
        assertNotNull(instance);
    }

    @Test //Checks that all Registration parameters are correct
    public void testAllfields() throws Exception {
        Context context = null;
        Registration instance = new Registration("Bill", "B1", "password", "password", "billy@Yahoo.com");
        assertFalse(instance.generateRegisterWarning(context));
    }
}