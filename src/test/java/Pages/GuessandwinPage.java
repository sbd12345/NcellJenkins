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
import utility.ConfigReader;

import java.time.Duration;
import java.util.List;

public class GuessandwinPage {

    private final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    public GuessandwinPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 40);
    }

    // --- Locators ---
    private final By downArrowLocator = MobileBy.xpath("//android.view.ViewGroup[@resource-id=\"card-container\"]");
    private final By guessAndWinLocator = MobileBy.AccessibilityId("Guess and Win");
  //  private final By enterPriceLocator = MobileBy.xpath("//android.widget.EditText[@resource-id='predicted-price']");
    private final By submitGuessLocator = MobileBy.xpath("//android.widget.Button[@text='Submit your Guess!']");

    // --- Public flow ---

    public void guessAndWin() {
        try {
        	clickElementWithSwipe(downArrowLocator, "Down Arrow");
        	Thread.sleep(2000);
        	 clickElementWithSwipe(guessAndWinLocator, "Guess and Win");
          //     enterPrice(ConfigReader.getProperty("a"));
            clickElementWithSwipe(submitGuessLocator, "Submit your Guess!");
        } catch (Exception e) {
            Assert.fail("Guess and Win flow failed: " + e.getMessage());
        }
    }

    // --- Input Methods ---

 /*   private void enterPrice(String value) {
        try {
            MobileElement priceField = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(enterPriceLocator));
            priceField.clear();
            priceField.sendKeys(value);
            System.out.println("Entered price: " + value);
        } catch (Exception e) {
            Assert.fail("Failed to enter price: " + e.getMessage());
        }
    }                        */

    // --- Utility Methods ---

    private void clickElementWithSwipe(By locator, String name) {
        int maxSwipes = 8;
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
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
