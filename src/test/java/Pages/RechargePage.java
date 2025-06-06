package Pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utility.ConfigReader;

public class RechargePage {
    private AndroidDriver<MobileElement> driver;
    private static final Logger logger = LogManager.getLogger(RechargePage.class);

    public RechargePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.error("Interrupted during wait", e);
            Thread.currentThread().interrupt();
        }
    }

    public void swipeUp() {
        try {
            int height = driver.manage().window().getSize().height;
            int width = driver.manage().window().getSize().width;
            int startY = (int) (height * 0.8);
            int endY = (int) (height * 0.2);
            int startX = width / 2;

            new TouchAction<>(driver)
                    .press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                    .moveTo(PointOption.point(startX, endY))
                    .release()
                    .perform();

            logger.info("swipeUp() performed successfully");
        } catch (Exception e) {
            logger.error("swipeUp() failed", e);
            takeScreenshot("swipeUpError");
        }
    }

    public void rechargeClick() {
        try {
            MobileElement rechargeButton = driver.findElementByXPath("(//android.widget.TextView[@text=\"Recharge\"])[1]");
            rechargeButton.click();
            logger.info("rechargeClick() - Recharge button clicked");
        } catch (Exception e) {
            logger.error("rechargeClick() failed: " + e.getMessage(), e);
            takeScreenshot("rechargeClickError");
        }
    }

    public void payOnline() {
        waitFor(2000);
        try {
            MobileElement crossIcon = driver.findElementByXPath("//android.widget.TextView[@text='']");
            if (crossIcon.isDisplayed()) {
                crossIcon.click();
                logger.info("payOnline() - Closed cross icon");
            }
        } catch (Exception e) {
            logger.warn("payOnline() - Cross icon not found or could not close: " + e.getMessage());
        }
        waitFor(1000);
        try {
            MobileElement payOnlineBtn = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Pay Online \"]/android.view.ViewGroup");
            payOnlineBtn.click();
            logger.info("payOnline() - Pay Online button clicked");
        } catch (Exception e) {
            logger.error("payOnline() - Pay Online button not found: " + e.getMessage(), e);
            takeScreenshot("payOnlineButtonNotFound");
        }
        waitFor(1000);
        try {
            MobileElement enterAmount = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Rs.30\"]");
            enterAmount.click();
            logger.info("payOnline() - Selected amount Rs.30");
        } catch (Exception e) {
            logger.error("payOnline() - Amount selection failed: " + e.getMessage(), e);
            takeScreenshot("payOnlineAmountSelectionError");
        }
        waitFor(1000);
        try {
            MobileElement recharge1 = driver.findElementByXPath("//android.widget.Button[@content-desc=\"Recharge\"]/android.view.ViewGroup/android.view.View");
            recharge1.click();
            MobileElement alert = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"YES\"]");
            alert.click();
            MobileElement d = driver.findElementByXPath( "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView");
            d.click();
            logger.info("payOnline() - Recharge process completed");
        } catch (Exception e) {
            logger.error("payOnline() - Recharge button failed: " + e.getMessage(), e);
            takeScreenshot("payOnlineRechargeError");
        }
    }

    public void rechargeCard() {
        waitFor(2000);
        try {
            MobileElement rechargeCard = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Recharge Card \"]/android.view.ViewGroup");
            rechargeCard.click();
            logger.info("rechargeCard() - Recharge Card clicked");

            waitFor(1000);
            MobileElement pinInput = driver.findElementByXPath("//android.widget.EditText[@text=\"Enter 16 digit PIN\"]");
            pinInput.sendKeys(ConfigReader.getProperty("PIN"));
            logger.info("rechargeCard() - PIN entered");

            waitFor(500);
            // MobileElement proceedBtn = driver.findElementByXPath("//android.widget.Button[@content-desc=\"Proceed\"]/android.view.ViewGroup/android.view.View");
            // proceedBtn.click();

        } catch (Exception e) {
            logger.error("rechargeCard() failed: " + e.getMessage(), e);
            takeScreenshot("rechargeCardError");
        }
    }

    public void recentRecharge() {
        waitFor(2000);
        try {
            MobileElement recentRecharge = driver.findElementByXPath("//android.widget.TextView[@text=\"Recent Recharges\"]");
            recentRecharge.click();
            logger.info("recentRecharge() - Recent Recharges clicked");

            MobileElement drop = driver.findElementByXPath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/com.horcrux.svg.SvgView");
            drop.click();
            logger.info("recentRecharge() - First drop element clicked");

            MobileElement drop2 = driver.findElementByXPath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView");
            drop2.click();
            logger.info("recentRecharge() - Second drop element clicked");
        } catch (Exception e) {
            logger.error("recentRecharge() failed: " + e.getMessage(), e);
            takeScreenshot("recentRechargeError");
        }
    }

    public void takeScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String dest = "screenshots/" + screenshotName + "_" + timestamp + ".png";

            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            Files.copy(src.toPath(), Paths.get(dest));
            logger.info("Screenshot saved: " + dest);
        } catch (IOException e) {
            logger.error("Failed to take screenshot: " + e.getMessage(), e);
        }
    }
}
