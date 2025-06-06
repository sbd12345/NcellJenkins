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
        System.out.println("Opening Police game...");
        clickGame(policeGameTile, "Police");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting in Police game: " + e.getMessage());
        }
        driver.navigate().back();
        System.out.println("Navigated back from Police game.");
    }

    public void openChangaChaitGame() {
        System.out.println("Opening Changa Chait game...");
        clickGame(changaChaitTile, "Changa Chait");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting in Changa Chait game: " + e.getMessage());
        }
        driver.navigate().back();
        System.out.println("Navigated back from Changa Chait game.");
    }

    public void openTimberGuyGame() {
        System.out.println("Opening Timber Guy game...");
        clickGame(timberGuyTile, "Timber Guy");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 40);
            MobileElement gameElement = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("TimberGuy")));
            if (gameElement.isDisplayed()) {
                System.out.println("Timber Guy game element is displayed, clicking...");
                gameElement.click();
                Thread.sleep(50000);
            }
        } catch (Exception e) {
            System.out.println("Error in Timber Guy Game: " + e.getMessage());
            takeScreenshot("TimberGuyGame_Error");
            Assert.fail("Timber Guy game failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
            System.out.println("Navigated back from Timber Guy game.");
        }
    }

    public void openStickyGooGame() {
        System.out.println("Opening Sticky Goo game...");
        clickGame(stickyGooTile, "Sticky Goo");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while waiting in Sticky Goo game: " + e.getMessage());
        }
        driver.navigate().back();
        System.out.println("Navigated back from Sticky Goo game.");
    }

    public void openLudoWithFriendsGame() {
        System.out.println("Opening Ludo with Friends game...");
        clickGame(ludoWithFriendsTile, "Ludo with Friends");
        try {
            Thread.sleep(20000);
            // Simulate failure and capture screenshot
            takeScreenshot("LudoWithFriendsGame_Error");
            Assert.fail("Ludo with Friends game failed to load correctly. Screenshot captured.");
        } catch (Exception e) {
            System.out.println("Error in Ludo with Friends Game: " + e.getMessage());
        } finally {
            driver.navigate().back();
            System.out.println("Navigated back from Ludo with Friends game.");
        }
    }

    public void openCarromHeroGame() {
        System.out.println("Opening Carrom Hero game...");
        clickGame(carromHeroTile, "Carrom Hero");
        try {
            Thread.sleep(20000);
            takeScreenshot("CarromHeroGame_Error");
            Assert.fail("Carrom Hero game failed to load correctly. Screenshot captured.");
        } catch (Exception e) {
            System.out.println("Error in Carrom Hero Game: " + e.getMessage());
        } finally {
            driver.navigate().back();
            System.out.println("Navigated back from Carrom Hero game.");
        }
    }

    public void openCandyFiestaGame() {
        System.out.println("Opening Candy Fiesta game...");
        clickGame(candyFiestaTile, "Candy Fiesta");
        try {
            Thread.sleep(20000);
            takeScreenshot("CandyFiestaGame_Error");
            Assert.fail("Candy Fiesta game failed to load correctly. Screenshot captured.");
        } catch (Exception e) {
            System.out.println("Error in Candy Fiesta Game: " + e.getMessage());
        } finally {
            driver.navigate().back();
            System.out.println("Navigated back from Candy Fiesta game.");
        }
    }

    private void clickGame(By locator, String gameName) {
        int maxSwipes = 7;
        boolean found = false;

        System.out.println("Attempting to find and click game: " + gameName);

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 30);
                MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element.isDisplayed()) {
                    element.click();
                    System.out.println("Clicked on game: " + gameName);
                    found = true;
                    break;
                }
            } catch (Exception e) {
                swipeUp();
                System.out.println("Swiping to find: " + gameName + " (attempt " + (i + 1) + ")");
            }
        }

        if (!found) {
            String errorMsg = gameName + " not found after swiping.";
            System.out.println(errorMsg);
            takeScreenshot(gameName + "_NotFound");
            Assert.fail(errorMsg);
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

        System.out.println("Performed swipe up");
    }

    private void takeScreenshot(String fileName) {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath + fileName + ".png");
            Files.createDirectories(destinationFile.getParentFile().toPath());
            Files.copy(screenshot.toPath(), destinationFile.toPath());
            System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}
