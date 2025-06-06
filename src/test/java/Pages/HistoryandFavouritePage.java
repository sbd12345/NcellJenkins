package Pages;

import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HistoryandFavouritePage {
    private AndroidDriver<MobileElement> driver;
    private String screenshotPath = System.getProperty("user.dir") + "/screenshots/";

    private By crossIconLocator = By.xpath("//android.widget.TextView[@text='']");
    private By historyLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"HISTORY \"]/android.view.ViewGroup");
    private By favouriteLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"FAVOURITES \"]/android.view.ViewGroup");

    public HistoryandFavouritePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private MobileElement waitForElement(By locator, int timeoutInSeconds) {
        return (MobileElement) new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void takeScreenshot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath + fileName + ".png");
            Files.createDirectories(destinationFile.getParentFile().toPath());
            Files.copy(screenshot.toPath(), destinationFile.toPath());
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    public void closeBanner() {
        try {
            MobileElement crossIcon = waitForElement(crossIconLocator, 55);
            crossIcon.click();
            System.out.println("closeBanner(): Banner closed successfully.");
        } catch (Exception e) {
            System.out.println("closeBanner(): Failed to close banner - " + e.getMessage());
            takeScreenshot("closeBanner_Failure");
            throw e;
        }
    }

    public void History() {
        try {
            MobileElement history = waitForElement(historyLocator, 15);
            history.click();
            System.out.println("History(): Clicked on History tab.");
        } catch (Exception e) {
            System.out.println("History(): Failed to click History - " + e.getMessage());
            takeScreenshot("History_Failure");
            throw e;
        }
    }

    public void Favourite() {
        try {
            MobileElement favourite = waitForElement(favouriteLocator, 15);
            favourite.click();
            System.out.println("Favourite(): Clicked on Favourite tab.");
        } catch (Exception e) {
            System.out.println("Favourite(): Failed to click Favourite - " + e.getMessage());
            takeScreenshot("Favourite_Failure");
            throw e;
        }
    }
}

