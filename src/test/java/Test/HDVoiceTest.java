package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.HDVoicePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class HDVoiceTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(HDVoiceTest.class);

    @Test
    public void testhdvoiceDirectly() {
        try {
            logger.info("Starting test: testhdvoiceDirectly");
            HDVoicePage hdVoicePage = new HDVoicePage(driver);
            hdVoicePage.openhdvoice();
            hdVoicePage.hdvoice();
            logger.info("testhdvoiceDirectly completed successfully");
        } catch (Exception e) {
            logger.error("testhdvoiceDirectly failed: {}", e.getMessage(), e);
            throw e;
        }
    }

}


