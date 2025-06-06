package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.DoubleDataOfferPage;

import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class DoubleDataOfferTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(DoubleDataOfferTest.class);
    private DoubleDataOfferPage doubleDataOfferPage;

    @BeforeClass
    public void setUpPage() {
        doubleDataOfferPage = new DoubleDataOfferPage(driver);
    }

    @Test
    public void testActivate30DayRenewal() {
        try {
            logger.info("Starting test: testActivate30DayRenewal");
            doubleDataOfferPage.doubleData();
            logger.info("testActivate30DayRenewal completed successfully");
        } catch (Exception e) {
            logger.error("testActivate30DayRenewal failed: {}", e.getMessage(), e);
            throw e;
        }
    }
}


