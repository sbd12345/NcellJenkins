package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.DoubleDataOfferPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class DoubleDataOfferTest extends BaseTest {

    private DoubleDataOfferPage doubleDataOfferPage;

    @BeforeClass
    public void setUpPage() {
        doubleDataOfferPage = new DoubleDataOfferPage(driver);
    }

    @Test
    public void testActivate30DayRenewal() {
        doubleDataOfferPage.doubleData();
    }
}

