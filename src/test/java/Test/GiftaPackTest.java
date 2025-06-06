package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.GiftaPackPage;
import utility.EmailUtil;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class GiftaPackTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(GiftaPackTest.class);

    @Test
    public void test() {
        try {
            logger.info("Starting test: GiftaPackTest");
            GiftaPackPage Page = new GiftaPackPage(driver);
            Page.gift();
            Page.g();
            logger.info("GiftaPackTest completed successfully");
        } catch (Exception e) {
            logger.error("GiftaPackTest failed: {}", e.getMessage(), e);
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

