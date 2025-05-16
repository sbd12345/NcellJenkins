package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.GuessandwinPage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class GuessandwinTest extends BaseTest {

    private GuessandwinPage guessandwinPage;

    @BeforeClass
    public void setUpPage() {
        guessandwinPage = new GuessandwinPage(driver);
    }

    @Test
    public void testGuessAndWinSubmission() throws InterruptedException {
        guessandwinPage.guessAndWin();
    }
}
