package Pages;

import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utility.ConfigReader;

public class SearchBoxPage {
    private AndroidDriver<MobileElement> driver;
    private By crossIconLocator = By.xpath("//android.widget.TextView[@text='']");
    private By searchBoxLocator = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.ImageView");
    private By searchInputLocator = By.xpath("//android.widget.EditText[@text=\"Search features, packs & issues\"]");
    private By historyButtonLocator = By.xpath("//android.widget.TextView[@text=\"History\"]");

    public SearchBoxPage(AndroidDriver<MobileElement> driver) {
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

    public void searchForHistory() {
        MobileElement searchBox = waitForElement(searchBoxLocator, 20);
        searchBox.click();
        MobileElement searchInput = waitForElement(searchInputLocator, 5);
        searchInput.sendKeys(ConfigReader.getProperty("name"));
        MobileElement historyButton = waitForElement(historyButtonLocator, 5);
        historyButton.click();
    }
}
