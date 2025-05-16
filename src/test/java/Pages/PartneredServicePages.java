package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.ConfigReader;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class PartneredServicePages {

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
        clickPartnerService(hungamaLocator, "Hungama");
        
        try {
        	   Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            MobileElement buyPlanElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(BuyPlanLocator));
            if (buyPlanElement.isDisplayed()) {
                buyPlanElement.click();
                Thread.sleep(5000);
                }
               MobileElement element2 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(numberLocator));
                if (element2.isDisplayed()) {
                    element2.sendKeys(ConfigReader.getProperty("number"));
            }
                MobileElement element3 = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(submitLocator));
                if (element3.isDisplayed()) {
                    element3.click();
            }
        } catch (Exception e) {
            System.out.println("Buy Plan not found or clickable for Hungama.");
        }

        driver.navigate().back(); 
    }

    public void openUnlimitedContents() {
        clickPartnerService(unlimitedContentsLocator, "Unlimited Contents");
        try {
     	   Thread.sleep(3000);
         WebDriverWait wait = new WebDriverWait(driver, 30);
         MobileElement buyPlanElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(planLocator));
         if (buyPlanElement.isDisplayed()) {
             buyPlanElement.click();
             Thread.sleep(5000);
         }
     } catch (Exception e) {
     }

     driver.navigate().back(); 
 }

    public void openMeroSchool() {
        clickPartnerService(meroSchoolLocator, "Mero School");
        try {
     	   Thread.sleep(3000);
         WebDriverWait wait = new WebDriverWait(driver, 30);
         MobileElement buyPlanElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(courseLocator));
         if (buyPlanElement.isDisplayed()) {
             buyPlanElement.click();
             Thread.sleep(5000);
             }
     } catch (Exception e) {
         System.out.println("Buy Plan not found or clickable for Hungama.");
     }

     driver.navigate().back(); 
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
                    found = true;
                    break;
                }
            } catch (Exception e) {
                swipeUp();
                System.out.println("Swiping to find: " + serviceName + " (attempt " + (i + 1) + ")");
            }
        }

        if (!found) {
            Assert.fail(serviceName + " not found after swiping.");
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
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }
}

