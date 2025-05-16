package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.BuyvoicepackPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class buyvoicepackTest extends BaseTest {

    private BuyvoicepackPage buyvoicepackPage;

    @BeforeClass
    public void setUpPage() {
        buyvoicepackPage = new BuyvoicepackPage(driver);
    }

    @Test(priority = 1)
    public void testAllProduct() {
        buyvoicepackPage.allproduct();
    }

    @Test(priority = 2)
    public void testSpecialOffer() {
        buyvoicepackPage.specialoffer();
    }

    @Test(priority = 3)
    public void testHourlyOneDay() {
        buyvoicepackPage.hourlyoneday();
    }

    @Test(priority = 4)
    public void testThreeToSevenDay() {
        buyvoicepackPage.threetosevenday();
    }

    @Test(priority = 5)
    public void testTwentyEightDays() {
        buyvoicepackPage.twentyeightdays();
    }

    @Test(priority = 6)
    public void testEightyFourDays() {
        buyvoicepackPage.eightyfourdays();
    }

    @Test(priority = 7)
    public void testIndiaIldDays() {
        buyvoicepackPage.indiailddays();
    }
}

