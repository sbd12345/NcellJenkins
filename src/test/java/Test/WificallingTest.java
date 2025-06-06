package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.wificallingPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class WificallingTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(WificallingTest.class);

    @Test
    public void testhwificalling() {
        logger.info("Starting test: testhwificalling");
        try {
            wificallingPage Page = new wificallingPage(driver);
            Page.openwifi();
            Page.wifi();
            logger.info("Wifi Calling test completed successfully");
        } catch (Exception e) {
            logger.error("Wifi Calling test failed: {}", e.getMessage(), e);
            throw e; 
        }
    }
}

