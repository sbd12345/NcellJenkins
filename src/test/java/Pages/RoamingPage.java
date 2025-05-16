package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class RoamingPage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public RoamingPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    // --- Locators ---
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
    private final By roamingLocator = MobileBy.AccessibilityId("Roaming");
    private final By closeLocator = MobileBy.AccessibilityId("Close");
    private final By indiaLocator = MobileBy.AccessibilityId("India");
    private final By sortByPriceLocator = MobileBy.AccessibilityId("Price");
    private final By lowToHighLocator = MobileBy.AccessibilityId("Low to high");
    private final By validityLocator = MobileBy.AccessibilityId("Validity");
    private final By daysLocator = MobileBy.AccessibilityId("4-7 days");
    private final By paymentMethodLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Pay By Balance\"]");
    private final By confirmLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Confirm\"]");
    private final By buyPackLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Buy Pack\"])[1]/android.view.ViewGroup");
    private final By detailLocator = MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Details\"])[1]");
    private final By buyPackLocator1 = MobileBy.xpath("//android.widget.Button[@content-desc=\"Buy pack\"]/android.view.ViewGroup/android.view.View");
  //  private final By faqLocator = MobileBy.AccessibilityId("FAQ");
    private final By noLocator = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"NO\"]/android.view.ViewGroup");
    // --- Public flow ---
    public void performRoamingPackFlow() {
        try {
        	clickElementWithSwipe(downArrowLocator, "Down Arrow");
            clickElementWithSwipe(roamingLocator, "Roaming Tab");
            clickElement(closeLocator, "close");
            Thread.sleep(5000);
            clickElement(indiaLocator, "India Button");
            Thread.sleep(15000);
            clickElement(sortByPriceLocator, "Sort by Price");
            clickElement(lowToHighLocator, "Low to High");
            clickElement(validityLocator, "Validity Filter");
            clickElement(daysLocator, "4-7 Days Filter");
            Thread.sleep(10000);
            clickElement(buyPackLocator, "Buy Pack");
            clickElement(paymentMethodLocator, "Pay By Balance");
            clickElement(confirmLocator, "Confirm Payment");
            clickElement(noLocator, "No Button to Close Confirmation");
            clickElement(detailLocator, "Details");
            clickElement(buyPackLocator1, "Buy Button in Details");
            reuse();
        //    clickElement(faqLocator , "faq");

        } catch (Exception e) {
            Assert.fail("Roaming pack flow failed: " + e.getMessage());
        }
    }

    public void reuse() {
    	 clickElement(paymentMethodLocator, "Pay By Balance");
         clickElement(confirmLocator, "Confirm Payment");
         clickElement(noLocator, "No Button to Close Confirmation");
    }

    // --- Utility methods ---

    private void clickElementWithSwipe(By locator, String name) {
        int maxSwipes = 6;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                List<MobileElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    wait.until(ExpectedConditions.elementToBeClickable(elements.get(0))).click();
                    System.out.println("Clicked on: " + name);
                    found = true;
                    break;
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
