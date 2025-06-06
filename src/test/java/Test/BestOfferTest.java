package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import Base.BaseTest;
import Pages.BestOfferPage;
import utility.EmailUtil;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class BestOfferTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(BestOfferTest.class);

    @Test
    public void BestOfferTest() {
        try {
            logger.info("Starting test: bestoffer");
            BestOfferPage bestOfferPage = new BestOfferPage(driver);
            bestOfferPage.openBestOffer();
            bestOfferPage.bestoffer();
            logger.info("testbestoffer completed successfully");
        } catch (Exception e) {
            logger.error("testbestoffer failed: {}", e.getMessage(), e);
            Assert.fail("Exception during testbestoffer: " + e.getMessage());
        }
    }

    @AfterSuite
   public static void sendAutomationReports() {
        try {
            logger.info("Preparing to send test reports via email");
            EmailUtil.sendReportsWithLogs();
            logger.info("Test reports email sent successfully");
        } catch (Exception e) {
            logger.error("Failed to send test reports email", e);
        }
    }     
}



