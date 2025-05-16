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

public class NcellPurpleLeaguePage {

    private AndroidDriver<MobileElement> driver;

    public NcellPurpleLeaguePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    // --- Locators ---
    private final By ncellPurpleLeagueLocator = MobileBy.AccessibilityId("Ncell Purple League");
    private final By scoreboardLocator = MobileBy.xpath("//android.widget.TextView[@text='SCOREBOARD']");
    private final By top10Locator = MobileBy.AccessibilityId("Top 10");
    private final By rulesAndPrizeLocator = MobileBy.AccessibilityId("Rules & Prize");

    public void NcellPurpleLeague() {
        int maxSwipes = 3;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 60);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(ncellPurpleLeagueLocator));
                if (element.isDisplayed()) {
                    element.click();
                    Thread.sleep(10000);

                    // Interacting with the Scoreboard
                    MobileElement scoreboardElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(scoreboardLocator));
                    if (scoreboardElement.isDisplayed()) {
                        scoreboardElement.click();
                        Thread.sleep(5000);
                    }

                    // Interacting with Top 10
                    MobileElement top10Element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(top10Locator));
                    if (top10Element.isDisplayed()) {
                        top10Element.click();
                        Thread.sleep(5000);
                    }

                    // Interacting with Rules & Prize
                    MobileElement rulesAndPrizeElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(rulesAndPrizeLocator));
                    if (rulesAndPrizeElement.isDisplayed()) {
                        rulesAndPrizeElement.click();
                        Thread.sleep(5000);
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
    }

    private void swipeUp() {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;

        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.3);

        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(400)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }
}


