package Test;

import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.SearchBoxPage;

public class SearchBoxTest extends BaseTest {

    @Test
    public void searchBoxTest() {
        SearchBoxPage searchPage = new SearchBoxPage(driver); 
        searchPage.closeBanner();
        searchPage.searchForHistory();
    }
}

