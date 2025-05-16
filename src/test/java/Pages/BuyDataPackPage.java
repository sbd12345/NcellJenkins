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

public class BuyDataPackPage {

    private final AndroidDriver<MobileElement> driver;
    private final String screenshotPath;

    public BuyDataPackPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.screenshotPath = System.getProperty("user.dir") + "/screenshots/";
    }

    private final By DatapackLocator = MobileBy.AccessibilityId("Buy Data Packs");
    private final By renewelpackLocator = MobileBy.AccessibilityId("Renewal Packs");
    private final By DoubleDataOfferLocator = MobileBy.AccessibilityId("DOUBLE DATA");
    private final By sevendaysrenewelpackLocator = MobileBy.AccessibilityId("7 Day Renewal");
    private final By sevendaysonetimerenewelpackLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"7 Day One Time\"]/android.view.ViewGroup");
    private final By thirtydayrenewelLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"30 Day Renewal\"])[1]");
    private final By thirtydayonetimeLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"28 Day One Time\"])[1]/android.view.ViewGroup");
    private final By topsellersLocator = MobileBy.AccessibilityId("Top Sellers");
    private final By sidhaonLocator = MobileBy.AccessibilityId("Sadhain ON");
    private final By onetothreedayLocator = MobileBy.AccessibilityId("1 - 3 Days");
    private final By sevendayLocator = MobileBy.AccessibilityId("7 Days");
    private final By twentyeightdayLocator = MobileBy.AccessibilityId("28 Days");
    private final By eightyfourdayLocator = MobileBy.AccessibilityId("84 Days");
    private final By paymentMethodLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Pay By Balance\"]");
    private final By confirmLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Confirm\"]");
    private final By cancelLocator = MobileBy.xpath("//android.widget.Button[@content-desc=\"Cancel\"]/android.view.ViewGroup");
    private final By buyPackLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Buy Pack\"])[1]/android.view.ViewGroup");
    private final By buyPackLocator1 = MobileBy.xpath("//android.widget.Button[@content-desc=\"Buy pack\"]/android.view.ViewGroup/android.view.View");
    private final By noLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"NO\"]/android.view.ViewGroup");
    private final By detailLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Details\"])[1]");
 
    public void renewelpack() {
        ClickDataPack(DatapackLocator, "Datapack");
        try {
            clickElement(renewelpackLocator, "renewelpack");
            Thread.sleep(8000);
            clickElement(sevendaysrenewelpackLocator, "sevendaysrenewelpack");
            clickElement(cancelLocator, "Cancel Button");
            clickElement(sevendaysonetimerenewelpackLocator, "sevendaysonetimerenewel");
            clickElement(buyPackLocator1,"buyPack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button");

        } catch (Exception e) {
            takeScreenshot("renewelpackError");
            Assert.fail("Failed in renewelpack: " + e.getMessage());
        }
    }

    public void doubleData() {
        try {
            clickElement(DoubleDataOfferLocator, "DoubleDataOffer");
            Thread.sleep(8000);
            clickElement(thirtydayrenewelLocator, "thirtydayrenewel");
            clickElement(cancelLocator, "Cancel Button");
            clickElement(thirtydayonetimeLocator, "thirtydayonetimeLocator");
            clickElement(buyPackLocator1,"buyPack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button");
            
        } catch (Exception e) {
            takeScreenshot(" doubleDataError");
            Assert.fail("Failed in doubleData: " + e.getMessage());
        }
    }

    public void Topsellers() {
        try {
            clickElement(topsellersLocator , "topsellers");
            Thread.sleep(8000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow1();
        } catch (Exception e) {
            takeScreenshot(" Topsellers");
            Assert.fail("Failed in  Topsellers: " + e.getMessage());
        }
    }
    
    public void sidhaon() {
        try {
            clickElement(sidhaonLocator, "sidhaon");
            Thread.sleep(8000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow1();
        } catch (Exception e) {
            takeScreenshot("sidhaon");
            Assert.fail("Failed in  sidhaon: " + e.getMessage());
        }
    }
    
    public void onethreedays() {
        try {
            clickElement(onetothreedayLocator, "onetothreeday");
            Thread.sleep(8000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow1();
        } catch (Exception e) {
            takeScreenshot("onethreedays");
            Assert.fail("Failed in  onethreedays: " + e.getMessage());
        }
    }

    public void sevenday() {
        try {

            clickElement(sevendayLocator, "seven");
        	Thread.sleep(8000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow1();
        } catch (Exception e) {
            takeScreenshot("threetosevenError");
            Assert.fail("Failed in threetosevenday: " + e.getMessage());
        }
    }

    public void twentyeightdays() {
        try {
            clickElement(twentyeightdayLocator, "28 Days");
           	Thread.sleep(8000);
           	clickElement(thirtydayrenewelLocator, "thirtydayrenewel");
            clickElement(cancelLocator, "Cancel Button");
            Thread.sleep(4000);
            clickElement(thirtydayonetimeLocator, "thirtydayonetimeLocator");
            clickElement(buyPackLocator1,"buyPack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button");
        } catch (Exception e) {
            takeScreenshot("voice28daysError");
            Assert.fail("Failed in voice28days: " + e.getMessage());
        }
    }

    public void eightyfourdays() {
        try {
            clickElement(eightyfourdayLocator, "84 Days");
        	Thread.sleep(8000);
        	 clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm");
            clickElement(noLocator, "No Button");
            clickElement(detailLocator, "Details");
            completePurchaseFlow1();
        } catch (Exception e) {
            takeScreenshot("eightyfourError");
            Assert.fail("Failed in eightyfourday: " + e.getMessage());
        }
    }


    private void completePurchaseFlow1() throws InterruptedException {
        clickElement(buyPackLocator1, "Buy Button in Details");
        clickElement(paymentMethodLocator, "Pay By Balance");
        clickElement(confirmLocator, "Confirm");
        clickElement(noLocator, "No Button");
    }

    private void ClickDataPack(By locator, String name) {
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

    private void clickElement(By locator, String elementName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.click();
            System.out.println("Clicked: " + elementName);
        } catch (Exception e) {
            takeScreenshot("Error_" + elementName.replace(" ", "_"));
            Assert.fail("Failed to click on: " + elementName + " - " + e.getMessage());
        }
    }
}

   
