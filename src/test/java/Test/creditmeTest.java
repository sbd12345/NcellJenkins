package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.creditmePage;
import utility.EmailUtil;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class creditmeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(creditmeTest.class);

    @Test
    public void testCreditMeFlow() {
        logger.info("Starting test: creditmeTest");
        try {
            creditmePage page = new creditmePage(driver);
            page.openCreditMeSection();
            page.performCreditRequest();
            logger.info("creditmeTest completed successfully");
        } catch (Exception e) {
            logger.error("creditmeTest failed", e);
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


