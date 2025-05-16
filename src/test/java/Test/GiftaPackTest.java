package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.GiftaPackPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class GiftaPackTest extends BaseTest {

    @Test
    public void test() {
    	GiftaPackPage Page = new GiftaPackPage(driver);
        Page.gift();
        Page.g();
    }
}
