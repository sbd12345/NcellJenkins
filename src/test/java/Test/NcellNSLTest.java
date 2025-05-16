package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import Base.BaseTest;
import Pages.NcellNSLPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class NcellNSLTest extends BaseTest {

    @Test(priority = 1)
    public void Spinandwin() {
        NcellNSLPage obj = new NcellNSLPage(driver);
        obj.Spinandwin();
    }

    @Test(priority = 2)
    public void NSLLiveMatch() {
        NcellNSLPage obj = new NcellNSLPage(driver);
        obj.NslLiveMatch();
    }

    @Test(priority = 4)
    public void FantasyLeague() {
        NcellNSLPage obj = new NcellNSLPage(driver);
        obj.FantasyLeague();
    }
    @Test(priority = 3)
    public void DigitalLeague() {
        NcellNSLPage obj = new NcellNSLPage(driver);
        obj.DigitalLeague();
    }
}
