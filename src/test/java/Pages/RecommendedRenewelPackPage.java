package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecommendedRenewelPackPage {

    private AndroidDriver<MobileElement> driver;

    // Locators
    private By buyPlanTextLocator = By.xpath("//android.view.ViewGroup[@content-desc='Rs 200 |Unlimited GB']");
    private By sevenDayRenewalTabLocator = By.xpath("//android.view.ViewGroup[@content-desc='7 Day Renewal']");
    private By cancelLocator = By.xpath("//android.widget.Button[@content-desc='Cancel']/android.view.ViewGroup");
    private By detailsLocator = By.xpath("//android.view.ViewGroup[@content-desc='Details']");
   

    public RecommendedRenewelPackPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private MobileElement waitForElementVisibility(By locator, int timeoutInSeconds) {
        return (MobileElement) new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public void scrollToBuyPlanAndClick() {
        MobileElement buyPlan = waitForElementVisibility(buyPlanTextLocator, 20);
        buyPlan.click();
    }
    
    public void selectSevenDayRenewalTab() {
        waitForElementVisibility(sevenDayRenewalTabLocator, 40).click();
    }

    public void clickCancel() {
        waitForElementVisibility(cancelLocator, 10).click();
    }
    
   public void clickDetails() {
        waitForElementVisibility(detailsLocator, 30).click();
    }           

}

