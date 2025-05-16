package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.MyNcellServicePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class MyNcellServiceTest extends BaseTest {

    @Test(priority = 1)
    public void testPinPukQuery() {
        MyNcellServicePage obj = new MyNcellServicePage(driver);
        obj.PinPukQuery();
    }

    @Test(priority = 4)
    public void testEsim() {
        MyNcellServicePage obj = new MyNcellServicePage(driver);
        obj.Esim();
    }

    @Test(priority = 2)
    public void testEmergencyServices() {
        MyNcellServicePage obj = new MyNcellServicePage(driver);
        obj.EmergencyServices();
    }

    @Test(priority = 3)
    public void testTollFreeNumbers() {
        MyNcellServicePage obj = new MyNcellServicePage(driver);
        obj.TollFreeNumbers();
    }
}

