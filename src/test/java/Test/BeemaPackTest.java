package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.BeemaPackPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class BeemaPackTest extends BaseTest {

    @Test
    public void testbeemapackcalling() {
    	BeemaPackPage Page = new BeemaPackPage(driver);
        Page.beemapack();
        Page.beema();
    }
}
