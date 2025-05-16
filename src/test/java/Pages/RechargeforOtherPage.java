package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utility.ConfigReader;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class RechargeforOtherPage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public RechargeforOtherPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    private final By rechargeforotherLocator = MobileBy.AccessibilityId("Recharge For Others");
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
    private final By crossIconLocator = By.xpath("//android.widget.TextView[@text='']");
    private final By payOnlineLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Pay Online \"]/android.view.ViewGroup");
	private final By enterAmountLocator= By.xpath("//android.view.ViewGroup[@content-desc=\"Rs.30\"]");
	private final By  MOBILE_NUMBER= By.xpath("//android.widget.EditText[@text=\"Mobile Number\"]");
//	private final By recharge1Locator= By.xpath("//android.widget.Button[@content-desc=\"Recharge\"]/android.view.ViewGroup/android.view.View");
	private final By rechargeCardLocator= By.xpath("//android.view.ViewGroup[@content-desc=\"Recharge Card \"]/android.view.ViewGroup");
	private final By PINLocator = By.xpath("//android.widget.EditText[@text=\"Enter 16 digit PIN\"]");
	private final By proceedLocator = By.xpath("//android.widget.Button[@content-desc=\"Proceed\"]/android.view.ViewGroup/android.view.View");
	private final By recentRechargeLocator = By.xpath("//android.widget.TextView[@text=\"Recent Recharges\"]");

    // --- Public flows ---

    public void recharge1() {
        try {
            clickElementWithSwipe(downArrowLocator, "Down Arrow");
        } catch (Exception e) {
        }
    }

    public void recharge() {
    	 clickElement(rechargeforotherLocator, "rechargeforother");
    	 clickElement(crossIconLocator, "crossIcon");
    	 clickElement(payOnlineLocator, "payOnline");
    	 clickElement(enterAmountLocator, "enterAmount");
    	 enterMobileNumber(ConfigReader.getProperty("number"));
    //	 clickElement(recharge1Locator, "recharge1");
    	 clickElement(rechargeCardLocator, "rechargeCard");
    	 enterMobileNumber(ConfigReader.getProperty("number"));
    	 enterPin(ConfigReader.getProperty("PIN"));
    	 clickElement(proceedLocator, "proceed");
    	 clickElement(recentRechargeLocator, "recentRecharge");
    }
    
    private void enterMobileNumber(String phoneNumber) {
        MobileElement numberField = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(MOBILE_NUMBER));
        numberField.sendKeys(phoneNumber);
    }
    
    private void enterPin(String pin) {
        MobileElement pinField = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(PINLocator));
        pinField.sendKeys(pin);
    }

    // --- Core utilities ---

    private void clickElementWithSwipe(By locator, String name) {
        int maxSwipes = 5;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                List<MobileElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    MobileElement element = elements.get(0);
                    if (element.isDisplayed()) {
                        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                        System.out.println("Clicked on " + name);
                        found = true;
                        break;
                    }
                }
                swipeUp();
                waitAfterSwipe();
            } catch (Exception e) {
                System.out.println("Swipe " + (i + 1) + " for " + name + " failed: " + e.getMessage());
            }
        }

        if (!found) {
            Assert.fail(name + " not found after " + maxSwipes + " swipes.");
        }
    }

    private void clickElement(By locator, String name) {
        try {
            MobileElement element = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Clicked on: " + name);
        } catch (Exception e) {
            Assert.fail("Failed to click on " + name + ": " + e.getMessage());
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
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


