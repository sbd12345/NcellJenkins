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

public class GuessandwinPage {

    private static final Logger logger = LogManager.getLogger(GuessandwinPage.class);

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public GuessandwinPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
    private final By guessAndWinLocator = MobileBy.AccessibilityId("Guess and Win");
    // private final By enterPriceLocator = MobileBy.xpath("//android.widget.EditText[@resource-id='predicted-price']");
    private final By submitGuessLocator = MobileBy.xpath("//android.widget.Button[@text='Submit your Guess!']");


    public void guessAndWin() {
        try {
            clickElementWithSwipe(downArrowLocator, "Down Arrow");
            Thread.sleep(2000);
            clickElementWithSwipe(guessAndWinLocator, "Guess and Win");
            // enterPrice(ConfigReader.getProperty("a"));  // Uncomment and implement if needed
            clickElementWithSwipe(submitGuessLocator, "Submit your Guess!");
        } catch (Exception e) {
            logger.error("Guess and Win flow failed: {}", e.getMessage(), e);
            takeScreenshot("GuessAndWin_Failure");
            Assert.fail("Guess and Win flow failed: " + e.getMessage());
        }
    }

    // --- Input Methods ---
    /*
    private void enterPrice(String value) {
        try {
            MobileElement priceField = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(enterPriceLocator));
            priceField.clear();
            priceField.sendKeys(value);
            logger.info("Entered price: {}", value);
        } catch (Exception e) {
            logger.error("Failed to enter price: {}", e.getMessage(), e);
            takeScreenshot("EnterPrice_Failure");
            Assert.fail("Failed to enter price: " + e.getMessage());
        }
    }
    */

    private void clickElementWithSwipe(By locator, String name) {
        int maxSwipes = 8;
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
            takeScreenshot(name + "_NotFound");
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on {}", name);
        } catch (Exception e) {
            logger.error("Failed to click on {}: {}", name, e.getMessage(), e);
            takeScreenshot(name + "_ClickFailed");
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

        try {
            Files.copy(srcFile.toPath(), Paths.get(destinationPath));
            logger.info("Screenshot saved at: {}", destinationPath);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", e.getMessage());
        }
    }
}

