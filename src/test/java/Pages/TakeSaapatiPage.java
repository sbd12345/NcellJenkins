package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class TakeSaapatiPage {

    private static final Logger logger = LogManager.getLogger(TakeSaapatiPage.class);

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public TakeSaapatiPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    private final By saapatiLocator = MobileBy.AccessibilityId("Take Saapati");
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
    // Uncomment and adjust as needed
    // private final By voiceloanLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Voice Loan\"]/android.view.ViewGroup");
    // private final By dataloanLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Data Loan\"]/android.view.ViewGroup");
    // private final By confirmLocator = MobileBy.AccessibilityId("Confirm Loan");
    private final By cancelLocator = MobileBy.AccessibilityId("Cancel");

    // --- Public flows ---

    public void takesaapati() {
        try {
            clickElementWithSwipe(downArrowLocator, "downarrow");
        } catch (Exception e) {
            logger.error("Error in takesaapati flow: {}", e.getMessage(), e);
            // Optionally take a screenshot here
            // takeScreenshot("takesaapati_Error");
        }
    }

    public void saapati() {
        clickElement(saapatiLocator, "saapati");
        // clickElement(voiceloanLocator, "voiceloan");
        // clickElement(dataloanLocator, "dataloan");
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
                        logger.info("Clicked on {}", name);
                        found = true;
                        break;
                    }
                }
                logger.debug("Element '{}' not found, swiping up... (Attempt {})", name, i + 1);
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                logger.warn("Swipe {} for '{}' failed: {}", i + 1, name, e.getMessage());
            }
        }

        if (!found) {
            logger.error("{} not found after {} swipes.", name, maxSwipes);
            // Optionally take a screenshot here
            // takeScreenshot(name + "_NotFound");
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on: {}", name);
        } catch (Exception e) {
            logger.error("Failed to click on '{}': {}", name, e.getMessage(), e);
            // Optionally take a screenshot here
            // takeScreenshot("ClickError_" + name);
            Assert.fail("Failed to click on " + name + ": " + e.getMessage());
        }
    }

    private void swipeUp() {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.3);

        logger.debug("Performing swipe up gesture");
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
            logger.warn("Sleep interrupted during waitAfterSwipe", e);
        }
    }

    // Optional screenshot helper method - implement your own or reuse from UtilitiesPage
    /*
    private void takeScreenshot(String fileName) {
        // Implement screenshot logic here if needed
    }
    */
}
