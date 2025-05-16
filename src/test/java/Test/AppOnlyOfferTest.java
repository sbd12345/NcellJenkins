package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.AppOnlyOfferPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class AppOnlyOfferTest extends BaseTest {

    @Test
    public void AppOnlyOffer() {
        AppOnlyOfferPage obj = new AppOnlyOfferPage(driver);
        obj.AppOnlyOffer();
    }
}
