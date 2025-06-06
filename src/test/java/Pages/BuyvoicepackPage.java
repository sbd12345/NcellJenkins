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

public class BuyvoicepackPage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;
    private final String screenshotPath;
    private static final Logger logger = LoggerFactory.getLogger(BuyvoicepackPage.class);

    public BuyvoicepackPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        this.screenshotPath = System.getProperty("user.dir") + "/screenshots/";
    }

    private final By voiceLocator = MobileBy.AccessibilityId("Buy Voice Packs");
    private final By allProductLocator = MobileBy.AccessibilityId("All Products");
    private final By specialOfferLocator = MobileBy.AccessibilityId("Special Offer");
    private final By hourlyOneDayLocator = MobileBy.AccessibilityId("Hourly - 1 Day");
    private final By threeToSevenDayLocator = MobileBy.AccessibilityId("3 - 7 Days");
    private final By twentyEightDayLocator = MobileBy.AccessibilityId("28 Days");
    private final By eightyFourDayLocator = MobileBy.AccessibilityId("84 Days");
    private final By indiaIldLocator = MobileBy.AccessibilityId("India + ILD");
    private final By renewalLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"28 Day Renewal\"])[1]");
    private final By paymentMethodLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Pay By Balance\"]");
    private final By confirmLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Confirm\"]");
    private final By cancelLocator = MobileBy.xpath("//android.widget.Button[@content-desc=\"Cancel\"]/android.view.ViewGroup");
    private final By twentyEightDayOneTimeLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"28 Day One Time\"])[1]/android.view.ViewGroup");
    private final By buyPackLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Buy Pack\"])[1]/android.view.ViewGroup");
    private final By buyPackLocatorDetails = MobileBy.xpath("//android.widget.Button[@content-desc=\"Buy pack\"]/android.view.ViewGroup/android.view.View");
    private final By noLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"NO\"]/android.view.ViewGroup");
    private final By detailLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Details\"])[1]");
    private final By buyPackLocatorSimple = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Buy Pack\"]/android.view.ViewGroup");
    private final By detailSimpleLocator = MobileBy.AccessibilityId("Details");

    // --- Public flows ---

    public void allProductFlow() {
        clickElementWithSwipe(voiceLocator, "Buy Voice Packs");
        try {
            clickElement(allProductLocator, "All Products");
            clickElement(renewalLocator, "28 Day Renewal");
            Thread.sleep(3000);
            clickElement(cancelLocator, "Cancel");
            clickElement(twentyEightDayOneTimeLocator, "28 Day One Time");
            Thread.sleep(3000);
            clickElement(buyPackLocatorDetails, "Buy Button");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button");
        } catch (Exception e) {
            takeScreenshot("allProductFlowError");
            Assert.fail("Failed in allProductFlow: " + e.getMessage());
        }
    }

    public void specialOfferFlow() {
        try {
            clickElement(specialOfferLocator, "Special Offer");
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow();
        } catch (Exception e) {
            takeScreenshot("specialOfferFlowError");
            Assert.fail("Failed in specialOfferFlow: " + e.getMessage());
        }
    }

    public void hourlyOneDayFlow() {
        try {
            Thread.sleep(5000);
            clickElement(hourlyOneDayLocator, "Hourly - 1 Day");
            Thread.sleep(7000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow();
        } catch (Exception e) {
            takeScreenshot("hourlyOneDayFlowError");
            Assert.fail("Failed in hourlyOneDayFlow: " + e.getMessage());
        }
    }

    public void threeToSevenDayFlow() {
        try {
            clickElement(threeToSevenDayLocator, "3 - 7 Days");
            Thread.sleep(8000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow();
        } catch (Exception e) {
            takeScreenshot("threeToSevenDayFlowError");
            Assert.fail("Failed in threeToSevenDayFlow: " + e.getMessage());
        }
    }

    public void twentyEightDaysFlow() {
        try {
            clickElement(twentyEightDayLocator, "28 Days");
            Thread.sleep(8000);
            clickElement(renewalLocator, "28 Day Renewal");
            Thread.sleep(3000);
            clickElement(cancelLocator, "Cancel");
            clickElement(twentyEightDayOneTimeLocator, "28 Day One Time");
            Thread.sleep(3000);
            completePurchaseFlow();
        } catch (Exception e) {
            takeScreenshot("twentyEightDaysFlowError");
            Assert.fail("Failed in twentyEightDaysFlow: " + e.getMessage());
        }
    }

    public void eightyFourDaysFlow() {
        try {
            clickElement(eightyFourDayLocator, "84 Days");
            Thread.sleep(8000);
            clickElement(buyPackLocatorSimple, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailSimpleLocator, "Details");
            completePurchaseFlow();
        } catch (Exception e) {
            takeScreenshot("eightyFourDaysFlowError");
            Assert.fail("Failed in eightyFourDaysFlow: " + e.getMessage());
        }
    }

    public void indiaIldFlow() {
        try {
            clickElement(indiaIldLocator, "India + ILD");
            Thread.sleep(8000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow();
        } catch (Exception e) {
            takeScreenshot("indiaIldFlowError");
            Assert.fail("Failed in indiaIldFlow: " + e.getMessage());
        }
    }

    // --- Private utility methods ---

    private void completePurchaseFlow() throws InterruptedException {
        clickElement(buyPackLocatorDetails, "Buy Button in Details");
        clickElement(paymentMethodLocator, "Pay By Balance");
        clickElement(confirmLocator, "Confirm");
        clickElement(noLocator, "No Button");
    }

    private void clickElementWithSwipe(By locator, String name) {
        int maxSwipes = 8;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                var elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    MobileElement element = elements.get(0);
                    if (element.isDisplayed()) {
                        element.click();
                        logger.info("Clicked on {}", name);
                        found = true;
                        break;
                    }
                }
                logger.info("Swipe {}: '{}' not visible yet.", (i + 1), name);
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                logger.warn("Swipe failed at attempt {}: {}", (i + 1), e.getMessage());
            }
        }

        if (!found) {
            takeScreenshot(name + "_NotFound");
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
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
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
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

    public void takeScreenshot(String screenshotName) {
        try {
            File srcFile = driver.getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String destinationPath = screenshotPath + screenshotName + "_" + timestamp + ".png";

            File screenshotDirectory = new File(screenshotPath);
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
            logger.error("Unexpected error while taking screenshot: {}", e.getMessage());
        }
    }

    private void clickElement(By locator, String elementName) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked: {}", elementName);
        } catch (Exception e) {
            takeScreenshot("Error_" + elementName.replace(" ", "_"));
            Assert.fail("Failed to click on: " + elementName + " - " + e.getMessage());
        }
    }
}
  