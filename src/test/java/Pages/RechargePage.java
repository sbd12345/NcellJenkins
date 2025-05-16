package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utility.ConfigReader;

public class RechargePage {
	private AndroidDriver<MobileElement> driver;

	private By crossIconLocator = By.xpath("//android.widget.TextView[@text='']");
	private By rechargeButtonLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Recharge\"]/android.view.View");
	private By payOnlineLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Pay Online \"]/android.view.ViewGroup");
	private By enterAmountLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Rs.30\"]");
	private By recharge1Locator = By.xpath("//android.widget.Button[@content-desc=\"Recharge\"]/android.view.ViewGroup/android.view.View");
	private By rechargeCardLocator = By.xpath("//android.view.ViewGroup[@content-desc=\"Recharge Card \"]/android.view.ViewGroup");
	private By PINLocator = By.xpath("//android.widget.EditText[@text=\"Enter 16 digit PIN\"]");
	private By proceedLocator = By.xpath("//android.widget.Button[@content-desc=\"Proceed\"]/android.view.ViewGroup/android.view.View");
	private By recentRechargeLocator = By.xpath("//android.widget.TextView[@text=\"Recent Recharges\"]");

	public RechargePage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
	}

	private MobileElement waitForElement(By locator, int timeoutInSeconds) {
		return (MobileElement) new WebDriverWait(driver, timeoutInSeconds)
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	private void clickElement(By locator, int timeoutInSeconds) {
		new WebDriverWait(driver, timeoutInSeconds)
				.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void closeBanner() {
		clickElement(crossIconLocator, 60);
	}

	public void rechargeClick() {
		clickElement(rechargeButtonLocator, 15);
	}

	public void payOnline() {
		closeBanner();
		clickElement(payOnlineLocator, 5);
		clickElement(enterAmountLocator, 5);
		//clickElement(recharge1Locator, 5);
	}

	public void rechargeCard() {
		clickElement(rechargeCardLocator, 5);
		waitForElement(PINLocator, 5).sendKeys(ConfigReader.getProperty("PIN"));
		clickElement(proceedLocator, 5);
	}

	public void recentRecharge() {
		clickElement(recentRechargeLocator, 5);
	}
}
