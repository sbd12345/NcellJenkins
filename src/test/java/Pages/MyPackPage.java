package Pages;

import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utility.ConfigReader;

public class MyPackPage {
    private AndroidDriver<MobileElement> driver;
    private By crossIconLocator = By.xpath("//android.widget.TextView[@text='']");
    private By myPackLocator = By.xpath("//android.widget.TextView[@text=\"MY PACKS \"]");
    private By dataLocator = By.xpath("//android.widget.TextView[@text=\"Data\"]");
    private By voiceLocator = By.xpath("//android.widget.TextView[@text=\"Voice\"]");
    
    public MyPackPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    private MobileElement waitForElement(By locator, int timeoutInSeconds) {
        return (MobileElement) new org.openqa.selenium.support.ui.WebDriverWait(driver, timeoutInSeconds)
                .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void closeBanner() {
        MobileElement crossIcon = waitForElement(crossIconLocator, 55);
        crossIcon.click();
    }

    public void MyPack() {
        MobileElement pack = waitForElement(myPackLocator, 15);
        pack.click();
    }
    
    public void Data() {
        MobileElement data = waitForElement(dataLocator, 5);
        data.click();
        driver.navigate().back(); 
        waitForElement(myPackLocator, 30);
    }     
    
    public void VoiceandSMS() {
        MobileElement voice = waitForElement(voiceLocator, 20);
       voice.click();
       driver.navigate().back(); 
       waitForElement(myPackLocator, 20);
    }
    
    }


