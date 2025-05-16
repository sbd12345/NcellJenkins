package Test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import Base.BaseTest;
import Pages.HDVoicePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class HDVoiceTest extends BaseTest {

    @Test
    public void testhdvoiceDirectly() {
        HDVoicePage hdVoicePage = new HDVoicePage(driver);
        hdVoicePage.openhdvoice();
        hdVoicePage.hdvoice();
    }
}

