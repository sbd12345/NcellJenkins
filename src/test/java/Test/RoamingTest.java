package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Base.BaseTest;
import Pages.RoamingPage;
import utility.EmailUtil;

import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class RoamingTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RoamingTest.class);
    private RoamingPage RoamingPage;

    @BeforeClass
    public void setUpPage() {
        logger.info("Initializing RoamingPage object");
        RoamingPage = new RoamingPage(driver);
    }

    @Test
    public void testActivate30DayRenewal() {
        logger.info("Test 'testActivate30DayRenewal' started.");
        try {
            RoamingPage.performRoamingPackFlow();
            logger.info("Roaming pack flow executed successfully.");
        } catch (Exception e) {
            logger.error("Test 'testActivate30DayRenewal' failed due to exception: ", e);
            throw e;
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
