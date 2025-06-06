package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.RecommendedRenewelPackPage;
public class RecommendedRenewelPackTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RecommendedRenewelPackTest.class);

    @Test
    public void recommendedRenewalPackTest() throws InterruptedException {
        logger.info("Starting test: recommendedRenewalPackTest");
        try {
            RecommendedRenewelPackPage packsPage = new RecommendedRenewelPackPage(driver);      
            packsPage.scrollToBuyPlanAndClick();
            packsPage.selectSevenDayRenewalTab();
            packsPage.clickCancel();
            packsPage.clickDetails();
            logger.info("Recommended Renewal Pack test completed successfully");
        } catch (Exception e) {
            logger.error("Recommended Renewal Pack test failed: {}", e.getMessage(), e);
            throw e;
        }
    }

}


