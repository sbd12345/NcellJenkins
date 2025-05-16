package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.creditmePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class creditmeTest extends BaseTest {

    @Test
    public void test() {
    	creditmePage Page = new creditmePage(driver);
        Page.creditme();
        Page.credit();
    }
}
