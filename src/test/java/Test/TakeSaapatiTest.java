package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.TakeSaapatiPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class TakeSaapatiTest extends BaseTest {

    @Test
    public void testhwificalling() {
        TakeSaapatiPage Page = new TakeSaapatiPage(driver);
        Page.takesaapati();
        Page.saapati();
    }
}
