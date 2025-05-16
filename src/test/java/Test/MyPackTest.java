package Test;

import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.MyPackPage;

public class MyPackTest extends BaseTest {

    @Test
    public void searchBoxTest() {
        MyPackPage packPage = new MyPackPage(driver); 
        packPage.closeBanner();
        packPage.MyPack();
        packPage.Data();
        packPage.VoiceandSMS();        
    }
}
