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

public class PartneredServicePages {

    private static final Logger logger = LogManager.getLogger(PartneredServicePages.class);

    private AndroidDriver<MobileElement> driver;

    public PartneredServicePages(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    // --- Locators ---
    private final By hungamaLocator = MobileBy.AccessibilityId("Hungama");
    private final By BuyPlanLocator = MobileBy.xpath("//android.view.View[@resource-id=\"plan_218\"]");
    private final By numberLocator = MobileBy.id("consentform");
    private final By submitLocator = MobileBy.id("mySubmit");
    private final By unlimitedContentsLocator = MobileBy.AccessibilityId("Unlimited Contents");
    private final By planLocator = MobileBy.AccessibilityId("Subscribe Now SMS Guru 6 months @648 NPR");
    private final By meroSchoolLocator = MobileBy.AccessibilityId("Mero School");
    private final By courseLocator = MobileBy.AccessibilityId("216");

    // --- Public Methods ---
    public void openHungama() {
        logger.info("Opening Hungama service...");
        clickPartnerService(hungamaLocator, "Hungama");

        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            MobileElement buyPlanElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(BuyPlanLocator));
            if (buyPlanElement.isDisplayed()) {
                logger.info("Buy Plan found for Hungama, clicking Buy Plan.");
                buyPlanElement.click();
                Thread.sleep(5000);
            }
            MobileElement numberElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(numberLocator));
            if (numberElement.isDisplayed()) {
                String number = ConfigReader.getProperty("number");
                logger.info("Entering number: {}", number);
                numberElement.sendKeys(number);
            }
            MobileElement submitElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(submitLocator));
            if (submitElement.isDisplayed()) {
                logger.info("Clicking submit button.");
                submitElement.click();
            }
        } catch (Exception e) {
            logger.error("Buy Plan not found or clickable for Hungama.", e);
            takeScreenshot("Hungama_BuyPlan_Error");
        }

        driver.navigate().back();
        logger.info("Navigated back after Hungama.");
    }

    public void openUnlimitedContents() {
        logger.info("Opening Unlimited Contents service...");
        clickPartnerService(unlimitedContentsLocator, "Unlimited Contents");
        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            MobileElement buyPlanElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(planLocator));
            if (buyPlanElement.isDisplayed()) {
                logger.info("Buy Plan found for Unlimited Contents, clicking it.");
                buyPlanElement.click();
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            logger.error("Buy Plan not found or clickable for Unlimited Contents.", e);
            takeScreenshot("UnlimitedContents_BuyPlan_Error");
        }
        driver.navigate().back();
        logger.info("Navigated back after Unlimited Contents.");
    }

    public void openMeroSchool() {
        logger.info("Opening Mero School service...");
        clickPartnerService(meroSchoolLocator, "Mero School");
        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            MobileElement buyPlanElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(courseLocator));
            if (buyPlanElement.isDisplayed()) {
                logger.info("Buy Plan found for Mero School, clicking it.");
                buyPlanElement.click();
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            logger.error("Buy Plan not found or clickable for Mero School.", e);
            takeScreenshot("MeroSchool_BuyPlan_Error");
        }
        driver.navigate().back();
        logger.info("Navigated back after Mero School.");
    }

    private void clickPartnerService(By locator, String serviceName) {
        int maxSwipes = 6;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 60);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    element.click();
                    logger.info("Clicked on: {}", serviceName);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                logger.warn("Swiping to find: {} (attempt {})", serviceName, (i + 1));
                swipeUp();
            }
        }

        if (!found) {
            logger.error("{} not found after {} swipes.", serviceName, maxSwipes);
            takeScreenshot(serviceName.replaceAll("\\s+", "") + "_NotFound");
            Assert.fail(serviceName + " not found after swiping.");
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
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
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
        }
    }
}
