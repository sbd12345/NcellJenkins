package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.GuessandwinPage;
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
}

