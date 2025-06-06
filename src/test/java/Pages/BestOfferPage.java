package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BestOfferPage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BestOfferPage.class);

    public BestOfferPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 80);
    }

    private final By bestOfferCardLocator = MobileBy.AccessibilityId("Best Offer");
    private final By buyPackLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"BUY\"])[1]/android.view.ViewGroup");
    private final By paymentMethodLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Pay By Balance\"]");
    private final By confirmLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Confirm\"]");
    private final By noLocator = MobileBy.AccessibilityId("NO");
    private final By detailLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Details\"])[1]");
    private final By buyPackLocator1 = MobileBy.xpath("//android.widget.Button[@content-desc=\"BUY\"]/android.view.ViewGroup/android.view.View");
    private final By back = MobileBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView");
    public void openBestOffer() {
        try {
        	Thread.sleep(10000);
        	clickElementWithSwipe(bestOfferCardLocator, "Best Offer Card");
        } catch (Exception e) {
            logger.error("Failed during Best Offer flow: {}", e.getMessage());
            takeScreenshot("openBestOffer_error");
            Assert.fail("Failed during Best Offer flow: " + e.getMessage());
        }
    }

    public void bestoffer() {
        try {
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button to Close Confirmation");
            clickElement(detailLocator, "Details");
            clickElement(buyPackLocator1, "Buy Button in Details");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button to Close Confirmation");
            clickElement(back, "Back");
            reuse();
        } catch (Exception e) {
            logger.error("Error in bestoffer flow: {}", e.getMessage());
            takeScreenshot("bestoffer_error");
            Assert.fail("Error in bestoffer flow: " + e.getMessage());
        }
    }

    public void reuse() {
        try {
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button to Close Confirmation");
        } catch (Exception e) {
            logger.error("Error in reuse flow: {}", e.getMessage());
            takeScreenshot("reuse_error");
            Assert.fail("Error in reuse flow: " + e.getMessage());
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
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                logger.warn("Swipe {} for {} failed: {}", (i + 1), name, e.getMessage());
            }
        }

        if (!found) {
            logger.error("{} not found after {} swipes.", name, maxSwipes);
            takeScreenshot(name.replaceAll(" ", "_") + "_not_found");
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on: {}", name);
        } catch (Exception e) {
            logger.error("Failed to click on {}: {}", name, e.getMessage());
            takeScreenshot(name.replaceAll(" ", "_") + "_click_fail");
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

        logger.info("Performed swipe up");
    }

    private void waitAfterSwipe() {
        try {
            Thread.sleep(8000);  
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Wait after swipe interrupted");
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
            logger.error("Failed to save screenshot: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during screenshot capture: {}", e.getMessage());
        }
    }
}

