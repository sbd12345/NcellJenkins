package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.BuyvoicepackPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class buyvoicepackTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(buyvoicepackTest.class);
    private BuyvoicepackPage buyvoicepackPage;

    @BeforeClass
    public void setUpPage() {
        buyvoicepackPage = new BuyvoicepackPage(driver);
    }

    @Test(priority = 1)
    public void testAllProduct() {
        try {
            logger.info("Starting test: testAllProduct");
            buyvoicepackPage.allProductFlow();
            logger.info("Completed test: testAllProduct");
        } catch (Exception e) {
            logger.error("testAllProduct failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test(priority = 2)
    public void testSpecialOffer() {
        try {
            logger.info("Starting test: testSpecialOffer");
            buyvoicepackPage.specialOfferFlow();
            logger.info("Completed test: testSpecialOffer");
        } catch (Exception e) {
            logger.error("testSpecialOffer failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test(priority = 3)
    public void testHourlyOneDay() {
        try {
            logger.info("Starting test: testHourlyOneDay");
            buyvoicepackPage.hourlyOneDayFlow();
            logger.info("Completed test: testHourlyOneDay");
        } catch (Exception e) {
            logger.error("testHourlyOneDay failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test(priority = 4)
    public void testThreeToSevenDay() {
        try {
            logger.info("Starting test: testThreeToSevenDay");
            buyvoicepackPage.threeToSevenDayFlow();
            logger.info("Completed test: testThreeToSevenDay");
        } catch (Exception e) {
            logger.error("testThreeToSevenDay failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test(priority = 5)
    public void testTwentyEightDays() {
        try {
            logger.info("Starting test: testTwentyEightDays");
            buyvoicepackPage.twentyEightDaysFlow();
            logger.info("Completed test: testTwentyEightDays");
        } catch (Exception e) {
            logger.error("testTwentyEightDays failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test(priority = 6)
    public void testEightyFourDays() {
        try {
            logger.info("Starting test: testEightyFourDays");
            buyvoicepackPage.eightyFourDaysFlow();
            logger.info("Completed test: testEightyFourDays");
        } catch (Exception e) {
            logger.error("testEightyFourDays failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test(priority = 7)
    public void testIndiaIldDays() {
        try {
            logger.info("Starting test: testIndiaIldDays");
            buyvoicepackPage.indiaIldFlow();
            logger.info("Completed test: testIndiaIldDays");
        } catch (Exception e) {
            logger.error("testIndiaIldDays failed: {}", e.getMessage(), e);
            throw e;
        }
    }
}
