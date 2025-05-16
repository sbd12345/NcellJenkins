package Pages;

import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utility.ConfigReader;

public class HistoryandFavouritePage {
    private AndroidDriver<MobileElement> driver;
    private By crossIconLocator = By.xpath("//android.widget.TextView[@text='']");
    private By historyLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"HISTORY \"]/android.view.ViewGroup");
    private By favouriteLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"FAVOURITES \"]/android.view.ViewGroup");
    
    public HistoryandFavouritePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private MobileElement waitForElement(By locator, int timeoutInSeconds) {
        return (MobileElement) new org.openqa.selenium.support.ui.WebDriverWait(driver, timeoutInSeconds)
                .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void closeBanner() {
        MobileElement crossIcon = waitForElement(crossIconLocator, 55);
        crossIcon.click();
    }

    public void History() {
        MobileElement history = waitForElement(historyLocator, 15);
        history.click();
    }
    
    public void Favourite() {
        MobileElement favourite = waitForElement(favouriteLocator, 15);
        favourite.click();
    }    
    
    }
