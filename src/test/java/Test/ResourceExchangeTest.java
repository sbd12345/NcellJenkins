package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.ResourceExchangePage;
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
}

