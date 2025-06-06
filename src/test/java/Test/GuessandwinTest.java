package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.GuessandwinPage;
import utility.EmailUtil;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class GuessandwinTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(GuessandwinTest.class);
    private GuessandwinPage guessandwinPage;

    @BeforeClass
    public void setUpPage() {
        guessandwinPage = new GuessandwinPage(driver);
    }

    @Test
    public void testGuessAndWinSubmission() throws InterruptedException {
        try {
            logger.info("Starting test: testGuessAndWinSubmission");
            guessandwinPage.guessAndWin();
            logger.info("testGuessAndWinSubmission completed successfully");
        } catch (Exception e) {
            logger.error("testGuessAndWinSubmission failed: {}", e.getMessage(), e);
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

