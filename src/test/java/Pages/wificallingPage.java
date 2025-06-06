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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

public class wificallingPage {

    private static final Logger logger = LogManager.getLogger(wificallingPage.class);

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public wificallingPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    private final By wificallingLocator = MobileBy.AccessibilityId("WiFi Calling");
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
    private final By elegibilityLocator = MobileBy.xpath("//android.widget.Button[@content-desc=\"Check Eligibility\"]/android.view.ViewGroup/android.view.View");
    private final By checkcapabledeviceLocator = MobileBy.xpath("//android.widget.Button[@content-desc=\"Check the Capable Devices\"]/android.view.ViewGroup/android.view.View");

    // --- Public flows ---

    public void openwifi() {
        try {
            logger.info("Attempting to open WiFi calling by clicking down arrow with swipe.");
            clickElementWithSwipe(downArrowLocator, "Down Arrow");
            takeScreenshot("openwifi_after_click");
        } catch (Exception e) {
            logger.error("Exception in openwifi(): {}", e.getMessage(), e);
            Assert.fail("Failed in openwifi(): " + e.getMessage());
        }
    }

    public void wifi() {
        try {
            logger.info("Starting wifi() flow.");
            clickElement(wificallingLocator, "WiFi Calling");
            takeScreenshot("wifi_after_clicking_wificalling");
            
            clickElement(elegibilityLocator, "Eligibility");
            takeScreenshot("wifi_after_clicking_eligibility");
            
            clickElement(checkcapabledeviceLocator, "Check Capable Device");
            takeScreenshot("wifi_after_clicking_checkcapabledevice");
            
            logger.info("Completed wifi() flow.");
        } catch (Exception e) {
            logger.error("Exception in wifi(): {}", e.getMessage(), e);
            Assert.fail("Failed in wifi(): " + e.getMessage());
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
                        logger.info("Clicked on {}", name);
                        found = true;
                        break;
                    }
                }
                logger.debug("Element '{}' not found, swiping up... (Attempt {})", name, i + 1);
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                logger.warn("Swipe {} for {} failed: {}", i + 1, name, e.getMessage(), e);
            }
        }

        if (!found) {
            logger.error("{} not found after {} swipes.", name, maxSwipes);
            takeScreenshot("failure_" + name.replace(" ", "_"));
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on: {}", name);
        } catch (Exception e) {
            logger.error("Failed to click on {}: {}", name, e.getMessage(), e);
            takeScreenshot("failure_" + name.replace(" ", "_"));
            Assert.fail("Failed to click on " + name + ": " + e.getMessage());
        }
    }

    private void swipeUp() {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.3);

        logger.debug("Performing swipe up gesture.");
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    private void waitAfterSwipe() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            logger.warn("Interrupted during swipe wait", e);
            Thread.currentThread().interrupt();
        }
    }

    public void takeScreenshot(String screenshotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String destinationPath = "screenshots/" + screenshotName + "_" + timestamp + ".png";

            File screenshotDirectory = new File("screenshots");
            if (!screenshotDirectory.exists()) {
                boolean dirCreated = screenshotDirectory.mkdirs();
                if (dirCreated) {
                    logger.info("Screenshots directory created.");
                } else {
                    logger.warn("Failed to create screenshots directory.");
                }
            }

            Files.copy(srcFile.toPath(), Paths.get(destinationPath));
            logger.info("Screenshot saved at: {}", destinationPath);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error during screenshot capture: {}", e.getMessage(), e);
        }
    }
}


