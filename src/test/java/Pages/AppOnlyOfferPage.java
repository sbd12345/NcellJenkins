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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppOnlyOfferPage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(AppOnlyOfferPage.class);

    public AppOnlyOfferPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 60);
    }

    // --- Locators ---
    private final By AppOnlyOfferLocator = MobileBy.AccessibilityId("App ONLY OFFER");
    private final By buyplanfromtabFor7DaytimeLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"App Only Offer, Rs. 150, 7 GB, DOUBLE MAJJA, 7 DAY, Validity\"]/android.view.View");
    private final By buypack = By.xpath("(//android.view.ViewGroup[@content-desc=\"Buy Pack\"])[2]/android.view.ViewGroup");
    private final By paymentmethodlocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Pay By Balance\"]");
    private final By confirmlocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Confirm\"]");
    private final By cancellocator = By.xpath("//android.view.ViewGroup[@content-desc=\"NO\"]/android.view.ViewGroup");
    private final By detaillocator = By.xpath("(//android.view.ViewGroup[@content-desc=\"Details\"])[2]");

    public void appOnlyOffer() {
        int maxSwipes = 3;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(AppOnlyOfferLocator));
                if (element.isDisplayed()) {
                    logger.info("Found 'App ONLY OFFER' element. Clicking...");
                    element.click();
                    Thread.sleep(10000);

                    clickIfDisplayed(buyplanfromtabFor7DaytimeLocator, "Buy plan for 7 Daytime");
                    clickIfDisplayed(buypack, "Buy Pack");
                    clickIfDisplayed(paymentmethodlocator, "Pay By Balance");
                    clickIfDisplayed(confirmlocator, "Confirm");
                    clickIfDisplayed(cancellocator, "Cancel (NO)");

                    MobileElement detailElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(detaillocator));
                    if (detailElement.isDisplayed()) {
                        logger.info("Clicking Details");
                        detailElement.click();

                        clickIfDisplayed(buypack, "Buy Pack (Details)");
                        clickIfDisplayed(paymentmethodlocator, "Pay By Balance (Details)");
                        clickIfDisplayed(confirmlocator, "Confirm (Details)");
                        clickIfDisplayed(cancellocator, "Cancel (NO) (Details)");
                    }

                    Thread.sleep(1500);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                logger.warn("Attempt {}: 'App ONLY OFFER' not found, swiping up. Exception: {}", i + 1, e.getMessage());
                swipeUp();
            }
        }

        if (!found) {
            logger.error("'App ONLY OFFER' not found after {} swipes.", maxSwipes);
            takeScreenshot("AppOnlyOffer_not_found");
            Assert.fail("'App ONLY OFFER' not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickIfDisplayed(By locator, String elementName) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            if (element.isDisplayed()) {
                element.click();
                logger.info("Clicked on {}", elementName);
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            logger.warn("Could not click on {}: {}", elementName, e.getMessage());
            takeScreenshot(elementName.replaceAll(" ", "_") + "_click_fail");
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

        logger.info("Performed swipe up");
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
