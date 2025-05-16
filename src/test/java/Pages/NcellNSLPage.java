package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.ConfigReader;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class NcellNSLPage {

    private AndroidDriver<MobileElement> driver;
    private String screenshotPath;

    public NcellNSLPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.screenshotPath = System.getProperty("user.dir") + "/screenshots/";
        File screenshotDir = new File(this.screenshotPath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
    }

    private final By nsllivematchLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"NSL Live Match\"]");
    private final By clickLocator = MobileBy.xpath("//android.view.View[@resource-id=\"player-control-overlay\"]/android.view.View[1]/android.widget.TextView[2]");
    private final By spinandwinLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Spin and Win\"]");
    private final By spinandwinbannerLocator = MobileBy.xpath("//android.widget.Button[@text=\"Close\"]");
    private final By spinandwinclickLocator = MobileBy.xpath("//android.widget.Button[@text=\"Spin to Win\"]");
    private final By fantasyLeagueLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Fantasy League\"]");
    private final By DigitalLeagueLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Digital League\"]");
    private final By PlanLocator= MobileBy.xpath("//android.widget.Button[@resource-id=\"submit\" and @text=\"6 MONTHS PACK @ RS.756 / 6 MONTHS\"]");
    private final By phonenoLocator = MobileBy.xpath("//android.widget.EditText[@resource-id=\"msisdnInput\"]");
    private final By submitLocator = MobileBy.xpath("//android.widget.Button[@resource-id=\"mySubmit\"]");

    public void NslLiveMatch() {
        int maxSwipes = 3;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 60);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(nsllivematchLocator));
                if (element.isDisplayed()) {
                    element.click();
                    MobileElement element2 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(clickLocator));
                    if (element2.isDisplayed()) {
                        element2.click();
                        Thread.sleep(2000);
                        driver.navigate().back();
                    }
                    Thread.sleep(1500);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                swipeUp();
                System.out.println("Swiped up to look for NSL Live Match... [" + (i + 1) + "]");
            }
        }

        if (!found) {
            System.out.println("NSL Live Match not found after " + maxSwipes + " swipes.");
            takeScreenshot("NSLLiveMatch_NotFound");
            Assert.fail("NSL Live Match not found after " + maxSwipes + " swipes.");
        }
    }

    public void Spinandwin() {
        int maxSwipes = 3;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 50);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(spinandwinLocator));
                if (element.isDisplayed()) {
                    element.click();
                    MobileElement element2 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(spinandwinbannerLocator));
                    if (element2.isDisplayed()) {
                        element2.click();
                    }

                    MobileElement element3 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(spinandwinclickLocator));
                    if (element3.isDisplayed()) {
                        element3.click();
                        driver.navigate().back();
                        Thread.sleep(2000);
                    }
                    Thread.sleep(1500);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                swipeUp();
                System.out.println("Swiped up to look for Spin and Win... [" + (i + 1) + "]");
            }
        }

        if (!found) {
            System.out.println("Spin and Win not found after " + maxSwipes + " swipes.");
            takeScreenshot("SpinAndWin_NotFound");
            Assert.fail("Spin and Win not found after " + maxSwipes + " swipes.");
        }
    }

    public void FantasyLeague() {
        int maxSwipes = 3;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 40);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(fantasyLeagueLocator));

                if (element.isDisplayed()) {
                    element.click();
                    Thread.sleep(20000); 
                    takeScreenshot("FantasyLeague_Error_Page"); 
                    throw new AssertionError("Test failed: Error page appears after clicking Fantasy League.");
                }
            } catch (Exception e) {
                swipeUp(); 
            }
        }

        if (!found) {
            takeScreenshot("FantasyLeague_NotFound");
            throw new AssertionError("Fantasy League not found after " + maxSwipes + " swipes.");
        }
    }

    public void DigitalLeague() {
        int maxSwipes = 3;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 50);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(DigitalLeagueLocator));
                if (element.isDisplayed()) {
                    element.click();
                    MobileElement element2 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(PlanLocator));
                    if (element2.isDisplayed()) {
                        element2.click();
                    }
                    MobileElement element3 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(phonenoLocator));
                    if (element3.isDisplayed()) {
                        element3.sendKeys(ConfigReader.getProperty("number"));
                    }

                  MobileElement element4 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(submitLocator));
                    if (element4.isDisplayed()) {
                        element4.click();
                        driver.navigate().back();
                        Thread.sleep(2000);
                    }            
                    Thread.sleep(1500);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                swipeUp();
                System.out.println("Swiped up to look for DigitalLeague... [" + (i + 1) + "]");
            }
        }

        if (!found) {
            System.out.println("DigitalLeague not found after " + maxSwipes + " swipes.");
            takeScreenshot("DigitalLeague_NotFound");
            Assert.fail("DigitalLeague not found after " + maxSwipes + " swipes.");
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
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }


    private void takeScreenshot(String fileName) {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath + fileName + ".png");
            Files.copy(screenshot.toPath(), destinationFile.toPath());
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot.");
            e.printStackTrace();
        }
    }
}

