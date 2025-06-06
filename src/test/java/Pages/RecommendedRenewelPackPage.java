package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecommendedRenewelPackPage {

    private AndroidDriver<MobileElement> driver;
    private static final Logger logger = LogManager.getLogger(RecommendedRenewelPackPage.class);

    // Locators
    private By buyPlanTextLocator = By.xpath("//android.view.ViewGroup[@content-desc='Rs 200 |Unlimited GB']");
    private By sevenDayRenewalTabLocator = By.xpath("//android.view.ViewGroup[@content-desc='7 Day Renewal']");
    private By cancelLocator = By.xpath("//android.widget.Button[@content-desc='Cancel']/android.view.ViewGroup");
    private By detailsLocator = By.xpath("//android.view.ViewGroup[@content-desc='Details']");

    public RecommendedRenewelPackPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private MobileElement waitForElementVisibility(By locator, int timeoutInSeconds) {
        try {
            return (MobileElement) new WebDriverWait(driver, timeoutInSeconds)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not visible: " + locator.toString(), e);
            takeScreenshot("ElementNotVisible_" + locator.toString().replaceAll("[^a-zA-Z0-9]", "_"));
            throw e;
        }
    }

    public void scrollToBuyPlanAndClick() {
        try {
            MobileElement buyPlan = waitForElementVisibility(buyPlanTextLocator, 60);
            buyPlan.click();
            logger.info("Clicked on Buy Plan");
        } catch (Exception e) {
            logger.error("Failed to click Buy Plan", e);
            takeScreenshot("scrollToBuyPlanAndClickError");
            throw e;
        }
    }

    public void selectSevenDayRenewalTab() {
        try {
            waitForElementVisibility(sevenDayRenewalTabLocator, 40).click();
            logger.info("Selected 7 Day Renewal Tab");
        } catch (Exception e) {
            logger.error("Failed to select 7 Day Renewal Tab", e);
            takeScreenshot("selectSevenDayRenewalTabError");
            throw e;
        }
    }

    public void clickCancel() {
        try {
            waitForElementVisibility(cancelLocator, 10).click();
            logger.info("Clicked Cancel button");
        } catch (Exception e) {
            logger.error("Failed to click Cancel button", e);
            takeScreenshot("clickCancelError");
            throw e;
        }
    }

    public void clickDetails() {
        try {
            waitForElementVisibility(detailsLocator, 30).click();
            logger.info("Clicked Details button");
        } catch (Exception e) {
            logger.error("Failed to click Details button", e);
            takeScreenshot("clickDetailsError");
            throw e;
        }
    }

    // --- Screenshot utility ---
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
            logger.info("Screenshot saved at: " + destinationPath);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getMessage(), e);
        }
    }
}


