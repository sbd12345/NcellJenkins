package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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

public class DoubleDataOfferPage {

    private final AndroidDriver<MobileElement> driver;
    private final String screenshotPath;

    public DoubleDataOfferPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.screenshotPath = System.getProperty("user.dir") + "/screenshots/";
    }

    private final By DoubleDataOfferLocator = MobileBy.AccessibilityId("Double Data Offer");
    private final By renewelLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"30 Day Renewal\"])[1]");
    private final By paymentMethodLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Pay By Balance\"]");
    private final By confirmLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Confirm\"]");
    private final By cancelLocator = MobileBy.xpath("//android.widget.Button[@content-desc=\"Cancel\"]/android.view.ViewGroup");
    private final By daysOneTimeLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"28 Day One Time\"])[1]/android.view.ViewGroup");
    private final By buyPackLocator = MobileBy.xpath("//android.widget.Button[@content-desc=\"Buy pack\"]/android.view.ViewGroup/android.view.View");

    public void doubleData() {
        clickDoubleData(DoubleDataOfferLocator, "Double Data Offer");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 35);

            MobileElement renewalBtn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(renewelLocator));
            renewalBtn.click();
            Thread.sleep(3000);

            MobileElement cancelBtn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(cancelLocator));
            cancelBtn.click();
            driver.navigate().back();
            
            MobileElement dayOneTimeBtn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(daysOneTimeLocator));
            dayOneTimeBtn.click();
            Thread.sleep(3000);

            MobileElement buyPackBtn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(buyPackLocator));
            buyPackBtn.click();

            MobileElement paymentBtn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodLocator));
            paymentBtn.click();

            MobileElement confirmBtn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(confirmLocator));
            confirmBtn.click();

        } catch (Exception e) {
            takeScreenshot("DoubleDataRenewalError");
            Assert.fail("Failed in doubleDataRenewal: " + e.getMessage());
        } finally {
        }
    }
    
    private void clickDoubleData(By locator, String name) {
        int maxSwipes = 8;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                var elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    MobileElement element = elements.get(0);
                    if (element.isDisplayed()) {
                        element.click();
                        found = true;
                        break;
                    }
                }
                System.out.println("Swipe " + (i + 1) + ": '" + name + "' not visible yet.");
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                System.out.println("Swipe failed at attempt " + (i + 1) + ": " + e.getMessage());
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

    private void takeScreenshot(String fileName) {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath + fileName + ".png");
            Files.createDirectories(destinationFile.getParentFile().toPath());
            Files.copy(screenshot.toPath(), destinationFile.toPath());
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Screenshot error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error while taking screenshot: " + e.getMessage());
        }
    }
}

