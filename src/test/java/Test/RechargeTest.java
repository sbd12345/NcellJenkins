package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.HomePage;
import Pages.RechargePage;
import utility.EmailUtil;

public class RechargeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RechargeTest.class);

    @Test
    public void rechargeTest() {
        logger.info("Starting test: rechargeTest");
        try {
            HomePage homePage = new HomePage(driver);
            RechargePage recharge = new RechargePage(driver);

            recharge.rechargeClick();
            recharge.payOnline();
            recharge.rechargeCard();
            recharge.recentRecharge();
            homePage.goToHomePage();

            logger.info("Recharge test completed successfully");
        } catch (Exception e) {
            logger.error("Recharge test failed: {}", e.getMessage(), e);
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


