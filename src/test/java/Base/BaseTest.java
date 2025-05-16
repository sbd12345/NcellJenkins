package Base;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utility.ConfigReader;
import utility.ExtentReportManager;

public class BaseTest {

    protected AndroidDriver<MobileElement> driver;
    protected ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite
    public void initializeReport() {
        extent = ExtentReportManager.createInstance();
    }

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", ConfigReader.getProperty("PlatformName"));
        caps.setCapability("deviceName", ConfigReader.getProperty("deviceName"));
        caps.setCapability("appPackage", ConfigReader.getProperty("appPackage"));
        caps.setCapability("appActivity", ConfigReader.getProperty("appActivity"));
        caps.setCapability("noReset", Boolean.parseBoolean(ConfigReader.getProperty("noReset")));
        caps.setCapability("automationName", ConfigReader.getProperty("automationName"));

        URL serverUrl = new URL(ConfigReader.getProperty("appiumServer"));
        driver = new AndroidDriver<>(serverUrl, caps);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void closeReport() {
        extent.flush();
    }

    public ExtentTest getTest() {
        return test;
    }

    public void setTest(ExtentTest test) {
        this.test = test;
    }

    public ExtentReports getExtent() {
        return extent;
    }
}
