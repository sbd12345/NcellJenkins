package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.creditmePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class creditmeTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(creditmeTest.class);

    @Test
    public void testCreditMeFlow() {
        logger.info("Starting test: creditmeTest");
        try {
            creditmePage page = new creditmePage(driver);
            page.openCreditMeSection();
            page.performCreditRequest();
            logger.info("creditmeTest completed successfully");
        } catch (Exception e) {
            logger.error("creditmeTest failed", e);
            throw e;
        }
    }

   
}


