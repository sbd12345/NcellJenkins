package Test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Base.BaseTest;
import Pages.SearchBoxPage;
import utility.EmailUtil;


public class SearchBoxTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SearchBoxTest.class);

    @Test
    public void searchBoxTest() {
        logger.info("Test 'searchBoxTest' started.");
        try {
            SearchBoxPage searchPage = new SearchBoxPage(driver);
            logger.debug("SearchBoxPage initialized.");

            // searchPage.closeBanner();
            // logger.debug("closeBanner() executed.");

            searchPage.searchForHistory();
            logger.debug("searchForHistory() executed.");

            logger.info("Test 'searchBoxTest' completed successfully.");
        } catch (Exception e) {
            logger.error("Test 'searchBoxTest' failed due to exception: ", e);
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
