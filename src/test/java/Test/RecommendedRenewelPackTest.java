package Test;

import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.RecommendedRenewelPackPage;

public class RecommendedRenewelPackTest extends BaseTest {

    @Test
    public void recommendedRenewalPackTest() throws InterruptedException {
        RecommendedRenewelPackPage packsPage = new RecommendedRenewelPackPage(driver);      
         packsPage.scrollToBuyPlanAndClick();
         packsPage.selectSevenDayRenewalTab();
         packsPage.clickCancel();
         packsPage.clickDetails();
    }
}

