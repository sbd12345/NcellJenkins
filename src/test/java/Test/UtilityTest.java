package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.UtilitiesPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class UtilityTest extends BaseTest {

    private UtilitiesPage utilitiesPage;

    @Test(priority = 1)
    public void openNepaliNewsTest() {
        utilitiesPage = new UtilitiesPage(driver);
        utilitiesPage.openNepaliNews();
    }

    @Test(priority = 2)
    public void openHoroscopeTest() {
        utilitiesPage = new UtilitiesPage(driver);
        utilitiesPage.openHoroscope();
    }

    @Test(priority = 3)
    public void openGoldSilverPriceTest() {
        utilitiesPage = new UtilitiesPage(driver);
        utilitiesPage.openGoldSilverPrice();
    }

    @Test(priority = 4)
    public void openShareMarketLiveTest() {
        utilitiesPage = new UtilitiesPage(driver);
        utilitiesPage.openShareMarketLive();
    }

    @Test(priority = 7)
    public void openExchangeRateTest() {
        utilitiesPage = new UtilitiesPage(driver);
        utilitiesPage.openExchangeRate();
    }

    @Test(priority = 5)
    public void openEplLiveScoreTest() {
        utilitiesPage = new UtilitiesPage(driver);
        utilitiesPage.openEplLiveScore();
    }

    @Test(priority = 6)
    public void openWeatherTest() {
        utilitiesPage = new UtilitiesPage(driver);
        utilitiesPage.openWeather();
    }
}

