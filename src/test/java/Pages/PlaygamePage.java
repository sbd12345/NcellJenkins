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

public class PlaygamePage {

    private AndroidDriver<MobileElement> driver;
    private String screenshotPath;

    public PlaygamePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.screenshotPath = System.getProperty("user.dir") + "/screenshots/";
    }

    private final By policeGameTile = MobileBy.AccessibilityId("Police");
    private final By changaChaitTile = MobileBy.AccessibilityId("Changa Chait");
    private final By timberGuyTile = MobileBy.AccessibilityId("Timber Guy");
    private final By stickyGooTile = MobileBy.AccessibilityId("Sticky Goo");
    private final By ludoWithFriendsTile = MobileBy.AccessibilityId("Ludo with Friends");
    private final By carromHeroTile = MobileBy.AccessibilityId("Carrom Hero");
    private final By candyFiestaTile = MobileBy.AccessibilityId("Candy Fiesta");

    public void openPoliceGame() {
        clickGame(policeGameTile, "Police");
        try {
            Thread.sleep(20000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().back();
    }

    public void openChangaChaitGame() {
        clickGame(changaChaitTile, "Changa Chait");
        try {
            Thread.sleep(20000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().back();
    }

    public void openTimberGuyGame() {
        clickGame(timberGuyTile, "Timber Guy");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 40);
            MobileElement gameElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("TimberGuy")));
            if (gameElement.isDisplayed()) {
            	gameElement.click();
            	Thread.sleep(50000);
            }
        } catch (Exception e) {
            System.out.println("Error in Timber Guy Game: " + e.getMessage());
        } finally {           
        }
    }

    public void openStickyGooGame() {
        clickGame(stickyGooTile, "Sticky Goo");
        try {
            Thread.sleep(20000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().back();
    }

    public void openLudoWithFriendsGame() {
        clickGame(ludoWithFriendsTile, "Ludo with Friends");
        try {
        	Thread.sleep(20000);
            takeScreenshot("LudoWithFriendsGame error loading page");
            Assert.fail("Ludo with Friends game failed to load correctly. Screenshot captured.");
        } catch (Exception e) {
            System.out.println("Error in Ludo with Friends Game: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    public void openCarromHeroGame() {
        clickGame(carromHeroTile, "Carrom Hero");
        try {
        	Thread.sleep(20000);
            takeScreenshot("CarromHeroGame error loading page");
            Assert.fail("Carrom Hero game failed to load correctly. Screenshot captured.");
        } catch (Exception e) {
            System.out.println("Error in Carrom Hero Game: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    public void openCandyFiestaGame() {
        clickGame(candyFiestaTile, "Candy Fiesta");
        try {
        	Thread.sleep(20000);
            takeScreenshot("CandyFiestaGame error loading page");
            Assert.fail("Candy Fiesta game failed to load correctly. Screenshot captured.");
        } catch (Exception e) {
            System.out.println("Error in Candy Fiesta Game: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    private void clickGame(By locator, String gameName) {
        int maxSwipes = 7;
        boolean found = false;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 30);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    element.click();
                    found = true;
                    break;
                }
            } catch (Exception e) {
                swipeUp();
                System.out.println("Swiping to find: " + gameName + " (attempt " + (i + 1) + ")");
            }
        }

        if (!found) {
            Assert.fail(gameName + " not found after swiping.");
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
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
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
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
