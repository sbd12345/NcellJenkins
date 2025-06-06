package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.MyPackPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class MyPackTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(MyPackTest.class);

    @Test
    public void testMyPackFeatures() {
        logger.info("Starting test: testMyPackFeatures");
        try {
            MyPackPage packPage = new MyPackPage(driver);
            packPage.closeBanner();
            packPage.openMyPack();
            packPage.openDataSection();
            packPage.openVoiceAndSMSSection();
            logger.info("testMyPackFeatures completed successfully");
        } catch (Exception e) {
            logger.error("testMyPackFeatures failed", e);
            throw e;
        }
    }
}
