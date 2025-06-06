package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import Base.BaseTest;
import Pages.BestOfferPage;
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

    }



