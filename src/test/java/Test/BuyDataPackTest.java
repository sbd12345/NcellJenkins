package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.BuyDataPackPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class BuyDataPackTest extends BaseTest {

    private BuyDataPackPage buyDataPackPage;

    @BeforeClass
    public void setUpPage() {
        buyDataPackPage = new BuyDataPackPage(driver);
    }

    @Test(priority = 1)
    public void testRenewalPack() {
        buyDataPackPage.renewelpack();
    }

    @Test(priority = 2)
    public void testDoubleData() {
        buyDataPackPage.doubleData();
    }

    @Test(priority = 6)
    public void testSevenDay() {
        buyDataPackPage.sevenday();
    }

    @Test(priority = 3)
    public void testTopSellers() {
        buyDataPackPage.Topsellers();
    }
    
    @Test(priority = 4)
    public void sidhaon() {
        buyDataPackPage.sidhaon();
    }
    
    @Test(priority = 5)
    public void onethreedays() {
        buyDataPackPage.onethreedays();
    }

    @Test(priority = 7)
    public void testTwentyEightDays() {
        buyDataPackPage.twentyeightdays();
    }

    @Test(priority = 8)
    public void testEightyFourDays() {
        buyDataPackPage.eightyfourdays();
    }

}

