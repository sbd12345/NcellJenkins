package Test;

import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.HistoryandFavouritePage;

public class HistoryandFavouriteTest extends BaseTest {

    @Test
    public void HistoryandFavouriteTest() {
       HistoryandFavouritePage hf= new HistoryandFavouritePage(driver); 
        hf.closeBanner();
        hf.History();
        hf.Favourite();        
    }
}


   
