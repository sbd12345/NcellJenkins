package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.BestOfferPage;
import Listeners.CustomTestListener;
import org.testng.Assert;

@Listeners(CustomTestListener.class)
public class BestOfferTest extends BaseTest {

    @Test
    public void testBuyPackDirectly() {
        BestOfferPage bestOfferPage = new BestOfferPage(driver);
        bestOfferPage.openBestOffer();
        bestOfferPage.bestoffer();
        
    }
}


