package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

public class creditmePage {

    private static final Logger logger = LogManager.getLogger(creditmePage.class);

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;
    private final String screenshotPath;

    public creditmePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
        this.screenshotPath = System.getProperty("user.dir") + "/screenshots/";
    }

    private final By creditMeLocator = MobileBy.AccessibilityId("Credit Me");
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
    private final By numberLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Request To:, Enter Amount (NPR), This service is subjected to service charges + government taxes\"]/android.view.ViewGroup[1]/android.widget.EditText");
    private final By enterAmountLocator = MobileBy.AccessibilityId("Rs. 100");
    private final By requestLocator = MobileBy.xpath("//android.widget.TextView[@text=\"Request\"]");

    // --- Public flows ---

    public void openCreditMeSection() {
        try {
            clickElementWithSwipe(downArrowLocator, "Down Arrow");
            logger.info("Opened Credit Me section by clicking down arrow.");
        } catch (Exception e) {
            logger.error("Failed to open Credit Me section: {}", e.getMessage(), e);
            takeScreenshot("CreditMe_OpenSectionError");
            Assert.fail("Failed to open Credit Me section: " + e.getMessage());
        }
    }

    public void performCreditRequest() {
        try {
            clickElement(creditMeLocator, "Credit Me");
            enterMobileNumber(ConfigReader.getProperty("number"));
            clickElement(enterAmountLocator, "Enter Amount");
            clickElement(requestLocator, "Request");
            logger.info("Performed credit request successfully.");
        } catch (Exception e) {
            logger.error("Credit request failed: {}", e.getMessage(), e);
            takeScreenshot("CreditMe_RequestError");
            Assert.fail("Credit request failed: " + e.getMessage());
        }
    }

    private void enterMobileNumber(String phoneNumber) {
        MobileElement numberField = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(numberLocator));
        numberField.clear();
        numberField.sendKeys(phoneNumber);
        logger.info("Entered mobile number: {}", phoneNumber);
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
                        logger.info("Clicked on '{}'", name);
                        found = true;
                        break;
                    }
                }
                logger.debug("Swipe {}: '{}' not visible yet, performing swipe up.", i + 1, name);
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                logger.warn("Swipe attempt {} for '{}' failed: {}", i + 1, name, e.getMessage(), e);
            }
        }

        if (!found) {
            logger.error("'{}' not found after {} swipes.", name, maxSwipes);
            takeScreenshot(name + "_NotFound");
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on '{}'", name);
        } catch (Exception e) {
            logger.error("Failed to click on '{}': {}", name, e.getMessage(), e);
            takeScreenshot(name + "_ClickError");
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
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            logger.warn("Interrupted during waitAfterSwipe", e);
            Thread.currentThread().interrupt();
        }
    }

    private void takeScreenshot(String fileName) {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath + fileName + ".png");
            Files.createDirectories(destinationFile.getParentFile().toPath());
            Files.copy(screenshot.toPath(), destinationFile.toPath());
            logger.info("Screenshot saved at: {}", destinationFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Screenshot save failed: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while taking screenshot: {}", e.getMessage(), e);
        }
    }
}
