package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.ResourceExchangePage;
import utility.EmailUtil;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class ResourceExchangeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ResourceExchangeTest.class);

    @Test
    public void testResourceExchange() {
        logger.info("Starting test: testResourceExchange");
        try {
            ResourceExchangePage Page = new ResourceExchangePage(driver);
            Page.rs();
            Page.s();
            logger.info("Resource Exchange test completed successfully");
        } catch (Exception e) {
            logger.error("Resource Exchange test failed: {}", e.getMessage(), e);
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

