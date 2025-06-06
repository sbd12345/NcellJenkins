package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.BaseTest;
import Pages.NcellPurpleLeaguePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class NcellPurpleLeagueTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(NcellPurpleLeagueTest.class);

    @Test(priority = 1)
    public void openNcellPurpleLeague() {
        try {
            logger.info("Starting test: openNcellPurpleLeague");
            NcellPurpleLeaguePage obj = new NcellPurpleLeaguePage(driver);
            obj.NcellPurpleLeague();
            logger.info("openNcellPurpleLeague completed successfully");
        } catch (Exception e) {
            logger.error("openNcellPurpleLeague test failed: {}", e.getMessage(), e);
            throw e;
        }
    }

}


