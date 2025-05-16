package Test;

import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.RechargePage;

public class RechargeTest extends BaseTest {

    @Test
    public void rechargeTest() {
        RechargePage recharge = new RechargePage(driver); 
        recharge.closeBanner();
        recharge.rechargeClick();
        recharge.payOnline();
        recharge.rechargeCard();
        recharge.recentRecharge();
    }
}

