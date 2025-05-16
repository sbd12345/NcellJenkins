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
import java.util.List;

public class TakeSaapatiPage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public TakeSaapatiPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    private final By saapatiLocator = MobileBy.AccessibilityId("Take Saapati");
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
  //  private final By voiceloanLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Voice Loan\"]/android.view.ViewGroup");
  //  private final By dataloanLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Data Loan\"]/android.view.ViewGroup");
  //  private final By confirmLocator = MobileBy.AccessibilityId("Confirm Loan");
    private final By cancelLocator = MobileBy.AccessibilityId("Cancel");

    // --- Public flows ---

    public void takesaapati() {
        try {
            clickElementWithSwipe(downArrowLocator, "downarrow");
        } catch (Exception e) {
        }
    }

    public void saapati() {
    	 clickElement(saapatiLocator, "saapati");
    	// clickElement(voiceloanLocator, "dataloan");
    	// clickElement(confirmLocator, "confirm");
        clickElement(cancelLocator, "cancel");
    }

    // --- Core utilities ---

    private void clickElementWithSwipe(By locator, String name) {
        int maxSwipes = 5;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                List<MobileElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    MobileElement element = elements.get(0);
                    if (element.isDisplayed()) {
                        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                        System.out.println("Clicked on " + name);
                        found = true;
                        break;
                    }
                }
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                System.out.println("Swipe " + (i + 1) + " for " + name + " failed: " + e.getMessage());
            }
        }

        if (!found) {
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Clicked on: " + name);
        } catch (Exception e) {
            Assert.fail("Failed to click on " + name + ": " + e.getMessage());
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
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    private void waitAfterSwipe() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


