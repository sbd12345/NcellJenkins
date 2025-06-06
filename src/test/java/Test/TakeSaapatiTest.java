package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.TakeSaapatiPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class TakeSaapatiTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TakeSaapatiTest.class);

    @Test
    public void testTakeSaapati() {
        logger.info("Starting test: testTakeSaapati");
        try {
            TakeSaapatiPage Page = new TakeSaapatiPage(driver);
            Page.takesaapati();
            Page.saapati();
            logger.info("Take Saapati test completed successfully");
        } catch (Exception e) {
            logger.error("Take Saapati test failed: {}", e.getMessage(), e);
            throw e;
        }
    }
}

