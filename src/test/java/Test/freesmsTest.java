package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.FreesmsPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class freesmsTest extends BaseTest {

    @Test
    public void test() {
    	FreesmsPage Page = new FreesmsPage(driver);
        Page.freesms();
        Page.sms();
    }
}
