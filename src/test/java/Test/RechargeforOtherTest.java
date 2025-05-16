package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Listeners.CustomTestListener;
import Pages.RechargeforOtherPage;

@Listeners(CustomTestListener.class)
public class RechargeforOtherTest extends BaseTest {

    @Test
    public void testrecharge() {
    	RechargeforOtherPage Page = new RechargeforOtherPage(driver);
        Page.recharge1();
        Page.recharge();
    }
}
