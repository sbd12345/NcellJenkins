package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.RoamingPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class RoamingTest extends BaseTest {

    private RoamingPage RoamingPage;

    @BeforeClass
    public void setUpPage() {
    	RoamingPage = new RoamingPage(driver);
    }

    @Test
    public void testActivate30DayRenewal() {
    	RoamingPage.performRoamingPackFlow();
    }
}
