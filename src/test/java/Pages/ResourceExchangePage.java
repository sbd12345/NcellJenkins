package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ResourceExchangePage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public ResourceExchangePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    private final By rescourceexchangeLocator = MobileBy.AccessibilityId("Resource Exchange");
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
   // private final By numberLocator = MobileBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[13]/android.widget.EditText");
  //  private final By textLocator = MobileBy.xpath("//android.widget.EditText[@text=\"160 characters remaining\"]");
  //  private final By sendLocator = MobileBy.xpath("//android.widget.Button[@content-desc=\"Send\"]/android.view.ViewGroup/android.view.View");

    // --- Public flows ---

    public void rs() {
        try {
            clickElementWithSwipe(downArrowLocator, "Down Arrow");
        } catch (Exception e) {
        }
    }

    public void s() {
    	try {
    	 clickElement(rescourceexchangeLocator, "resource");
    	 Thread.sleep(15000);
    	  } catch (Exception e) {
          }
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




