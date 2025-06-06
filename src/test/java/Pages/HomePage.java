package Pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private AndroidDriver<MobileElement> driver;
    private WebDriverWait wait;
    private By homeTabLocator = By.xpath("//android.view.ViewGroup[@content-desc='Home']");
    
  //  private By homeTabLocator = By.id("com.mventus.ncell:id/nav_home");

    public HomePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

  
    public void goToHomePage() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homeTabLocator)).click();
            System.out.println("✅ Navigated to Home Page.");
        } catch (Exception e) {
            System.out.println("❌ Failed to navigate to Home Page: " + e.getMessage());
        }
    }
}

