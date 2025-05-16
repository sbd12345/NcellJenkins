package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.wificallingPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class WificallingTest extends BaseTest {

    @Test
    public void testhwificalling() {
        wificallingPage Page = new wificallingPage(driver);
        Page.openwifi();
        Page.wifi();
    }
}
