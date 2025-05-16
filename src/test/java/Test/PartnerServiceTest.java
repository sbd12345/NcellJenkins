package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.PartneredServicePages;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class PartnerServiceTest extends BaseTest {

    private PartneredServicePages partneredServicePages;

    @Test(priority = 1)
    public void openHungamaTest() {
        partneredServicePages = new PartneredServicePages(driver);
        partneredServicePages.openHungama();
    }

    @Test(priority = 2)
    public void openUnlimitedContentsTest() {
        partneredServicePages = new PartneredServicePages(driver);
        partneredServicePages.openUnlimitedContents();
    }

    @Test(priority = 3)
    public void openMeroSchoolTest() {
        partneredServicePages = new PartneredServicePages(driver);
        partneredServicePages.openMeroSchool();
    }
}

