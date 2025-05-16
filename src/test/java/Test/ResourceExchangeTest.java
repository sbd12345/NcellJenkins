package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.ResourceExchangePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class ResourceExchangeTest extends BaseTest {

    @Test
    public void test() {
    	ResourceExchangePage Page = new ResourceExchangePage(driver);
        Page.rs();
        Page.s();
    }
}
