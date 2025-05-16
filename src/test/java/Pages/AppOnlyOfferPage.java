package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class AppOnlyOfferPage {

    private AndroidDriver<MobileElement> driver;

    public AppOnlyOfferPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    // --- Locators ---
    private final By AppOnlyOfferLocator = MobileBy.AccessibilityId("App ONLY OFFER");
    private By buyplanfromtabFor7DaytimeLocator= By.xpath("//android.view.ViewGroup[@content-desc=\"App Only Offer, Rs. 150, 7 GB, DOUBLE MAJJA, 7 DAY, Validity\"]/android.view.View");
    private By buypack= By.xpath("(//android.view.ViewGroup[@content-desc=\"Buy Pack\"])[2]/android.view.ViewGroup");
    private By paymentmethodlocator= By.xpath("//android.view.ViewGroup[@content-desc=\"Pay By Balance\"]");
    private By confirmlocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Confirm\"]");
    private By cancellocator = By.xpath("//android.view.ViewGroup[@content-desc=\"NO\"]/android.view.ViewGroup");
    private By detaillocator = By.xpath("(//android.view.ViewGroup[@content-desc=\"Details\"])[2]");

    public void AppOnlyOffer() {
        int maxSwipes = 3;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 60);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(AppOnlyOfferLocator));
                if (element.isDisplayed()) {
                    element.click();
                    Thread.sleep(10000);

                    // Interacting with the buyplanfromtabFor7Daytime
                    MobileElement buyplanfromtabFor7DaytimeElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(buyplanfromtabFor7DaytimeLocator));
                    if (buyplanfromtabFor7DaytimeElement.isDisplayed()) {
                    	buyplanfromtabFor7DaytimeElement.click();
                        Thread.sleep(3000);
                    }

                    MobileElement buypackElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(buypack));
                    if (buypackElement.isDisplayed()) {
                    	buypackElement.click();
                        Thread.sleep(3000);
                    }

                    MobileElement paymentmethodElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(paymentmethodlocator));
                    if (paymentmethodElement.isDisplayed()) {
                    	paymentmethodElement.click();
                        Thread.sleep(3000);
                    }
                    
                    MobileElement confirmElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(confirmlocator));
                    if (confirmElement.isDisplayed()) {
                        confirmElement.click();
                        Thread.sleep(3000);
                    }
                    MobileElement cancelElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(cancellocator));
                    if (cancelElement.isDisplayed()) {
                        cancelElement.click();
                        Thread.sleep(3000);
                    }
                    MobileElement detailElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(detaillocator));
                    if (detailElement.isDisplayed()) {
                       detailElement.click();
                       buypackElement.click();
                       paymentmethodElement.click();
                       confirmElement.click();
                       cancelElement.click();
                        Thread.sleep(3000);
                    }

                    Thread.sleep(1500);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                swipeUp();
                System.out.println("Swiped up to look for NSL Live Match... [" + (i + 1) + "]");
            }
        }
    }

    private void swipeUp() {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;

        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.3);

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(400)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }
}



