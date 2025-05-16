package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.NcellPurpleLeaguePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class NcellPurpleLeagueTest extends BaseTest {

    @Test(priority = 1)
    public void openNcellPurpleLeague() {
        NcellPurpleLeaguePage obj = new NcellPurpleLeaguePage(driver);
        obj.NcellPurpleLeague();
    }
}

