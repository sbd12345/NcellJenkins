package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.BeemaPackPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class BeemaPackTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(BeemaPackTest.class);

    @Test
    public void testbeemapackcalling() throws InterruptedException {
        logger.info("Starting test: testbeemapackcalling");
        try {
            BeemaPackPage Page = new BeemaPackPage(driver);
            Page.beemapack();
            Page.beema();
            logger.info("Beema pack test completed successfully");
        } catch (Exception e) {
            logger.error("Beema pack test failed: {}", e.getMessage(), e);
            throw e; 
        }
    }
}
