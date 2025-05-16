package androidjava.app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class NewTest {
    public static void main(String[] args) throws Exception{
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Medium_Phone_API_36");  
        caps.setCapability("app", "C:\\att\\ApiDemos-debug.apk"); 
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("uiautomator2ServerInstallTimeout", 60000);
      
        AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
          
        	 MobileElement launchButton = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Animation\"]"));
                      launchButton.click();
               
        Thread.sleep(5000);
      //  driver.quit();
    }
}

