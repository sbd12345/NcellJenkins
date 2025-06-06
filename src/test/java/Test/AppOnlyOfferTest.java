package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.AppOnlyOfferPage;
import utility.EmailUtil;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class AppOnlyOfferTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(AppOnlyOfferTest.class);

    @Test
    public void appOnlyOffer() {
        logger.info("Starting test: appOnlyOffer");
        try {
            AppOnlyOfferPage appOnlyOfferPage = new AppOnlyOfferPage(driver);
            appOnlyOfferPage.appOnlyOffer();
            logger.info("Test appOnlyOffer completed successfully");
        } catch (Exception e) {
            logger.error("Test appOnlyOffer failed", e);
            throw e;  // Re-throw to mark the test as failed
        }
    }

    @AfterSuite
    public void sendTestReportEmail() {
        try {
            logger.info("Sending test report email after suite completion");
            EmailUtil.sendReportsWithLogs();
            logger.info("Test report email sent successfully");
        } catch (Exception e) {
            logger.error("Failed to send test report email", e);
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
