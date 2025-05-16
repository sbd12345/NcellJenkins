package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class UtilitiesPage {

    private final AndroidDriver<MobileElement> driver;
    private final String screenshotPath;

    public UtilitiesPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.screenshotPath = System.getProperty("user.dir") + "/screenshots/";
    }

    // Locators
    private final By nepaliNewsLocator = MobileBy.AccessibilityId("Nepali News");
    private final By horoscopeLocator = MobileBy.AccessibilityId("Horoscope");
    private final By goldSilverPriceLocator = MobileBy.AccessibilityId("Gold/Silver Price");
    private final By shareMarketLiveLocator = MobileBy.AccessibilityId("Share Market Live");
    private final By exchangeRateLocator = MobileBy.AccessibilityId("Exchange Rate");
    private final By eplLiveScoreLocator = MobileBy.AccessibilityId("EPL Live Score");
    private final By weatherLocator = MobileBy.AccessibilityId("Weather");

    // Weather Tabs
    private final By weatherTodayLocator = By.xpath("//android.view.View[@resource-id=\"rc-tabs-1-tab-1\"]");
    private final By weatherTonightLocator = By.xpath("//android.view.View[@resource-id=\"rc-tabs-1-tab-2\"]");

    // Horoscope Tabs
    private final By horoscopeDailyTab = By.xpath("//android.view.View[@resource-id='rc-tabs-0-tab-1']");
    private final By horoscopeMonthlyTab = By.xpath("//android.view.View[@resource-id='rc-tabs-0-tab-2']");
    private final By horoscopeYearlyTab = By.xpath("//android.view.View[@resource-id='rc-tabs-0-tab-3']");

    // ------------------ Public Feature Methods ------------------

    public void openNepaliNews() {
        openUtility(nepaliNewsLocator, "Nepali News");
        sleep(8000);
        goBack();
    }

    public void openGoldSilverPrice() {
        openUtility(goldSilverPriceLocator, "Gold/Silver Price");
        sleep(12000);
        goBack();
    }

    public void openShareMarketLive() {
        openUtility(shareMarketLiveLocator, "Share Market Live");
        sleep(12000);
        goBack();
    }

    public void openExchangeRate() {
        openUtility(exchangeRateLocator, "Exchange Rate");
        sleep(8000);
        goBack();
    }

    public void openEplLiveScore() {
        try {
            System.out.println("[INFO] Attempting to open EPL Live Score");

            takeScreenshot("Before_Click_EPL");
            openUtility(eplLiveScoreLocator, "EPL Live Score");
            sleep(18000);
            takeScreenshot("After_Click_EPL");

            captureLog("EPL_Logcat_Output");

        } catch (Exception e) {
            takeScreenshot("EPL_Live_Score_Exception");
            Assert.fail("EPL Live Score failed: " + e.getMessage());
        } finally {
            goBack();
        }
    }

    public void openWeather() {
        try {
            clickElementWithSwipe(weatherLocator, "Weather");
            sleep(8000);
            clickElement(weatherTodayLocator, "Weather Today");
            clickElement(weatherTonightLocator, "Weather Tonight");
        } catch (Exception e) {
            takeScreenshot("WeatherError");
            Assert.fail("Failed in openWeather: " + e.getMessage());
        } finally {
            goBack();
        }
    }

    public void openHoroscope() {
        try {
            clickElementWithSwipe(horoscopeLocator, "Horoscope");
            sleep(3000);
            clickElement(horoscopeDailyTab, "Horoscope Daily");
            clickElement(horoscopeMonthlyTab, "Horoscope Monthly");
            clickElement(horoscopeYearlyTab, "Horoscope Yearly");

        } catch (Exception e) {
            takeScreenshot("HoroscopeError");
            Assert.fail("Failed in openHoroscope: " + e.getMessage());
        } finally {
            goBack();
        }
    }

    // ------------------ Helper Methods ------------------

    private void openUtility(By locator, String name) {
        try {
            clickElementWithSwipe(locator, name);
        } catch (Exception e) {
            takeScreenshot(name.replace(" ", "_") + "_Error");
            Assert.fail("Failed to open utility: " + name + " - " + e.getMessage());
        }
    }

    private void clickElementWithSwipe(By locator, String name) {
        int maxSwipes = 7;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                var elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    elements.get(0).click();
                    found = true;
                    System.out.println("Clicked on: " + name);
                    break;
                }
                swipeUp();
                waitAfterSwipe();
                System.out.println("Swiped " + (i + 1) + " times looking for: " + name);
            } catch (Exception swipeException) {
                System.out.println("Swipe attempt " + (i + 1) + " failed for: " + name);
            }
        }

        if (!found) {
            takeScreenshot(name.replace(" ", "_") + "_NotFound");
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 45);
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Clicked on: " + name);
        } catch (Exception e) {
            takeScreenshot("ClickError_" + name.replace(" ", "_"));
            Assert.fail("Failed to click on: " + name + " - " + e.getMessage());
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
    }

    private void waitAfterSwipe() {
        sleep(12000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void goBack() {
        try {
            sleep(8000);
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        } catch (Exception e) {
            System.out.println("Back navigation failed: " + e.getMessage());
        }
    }

    private void takeScreenshot(String fileName) {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath + fileName + ".png");
            Files.createDirectories(destinationFile.getParentFile().toPath());
            Files.copy(screenshot.toPath(), destinationFile.toPath());
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Screenshot save error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected screenshot error: " + e.getMessage());
        }
    }

    private void captureLog(String logName) {
        try {
            String logFilePath = screenshotPath + logName + ".txt";
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "adb logcat -d > \"" + logFilePath + "\"");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            process.waitFor();
            System.out.println("Log saved to: " + logFilePath);
        } catch (IOException | InterruptedException e) {
            System.out.println("Log capture failed: " + e.getMessage());
        }
    }
}



 