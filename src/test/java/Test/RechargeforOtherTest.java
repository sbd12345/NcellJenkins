package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.RechargeforOtherPage;
import utility.EmailUtil;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class RechargeforOtherTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RechargeforOtherTest.class);

    @Test
    public void testRechargeForOther() {
        logger.info("Starting test: testRechargeForOther");
        try {
            RechargeforOtherPage page = new RechargeforOtherPage(driver);
            page.recharge1();
            page.recharge();
            logger.info("Recharge for Other test completed successfully");
        } catch (Exception e) {
            logger.error("Recharge for Other test failed: {}", e.getMessage(), e);
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

