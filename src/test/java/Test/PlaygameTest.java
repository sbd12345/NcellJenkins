package Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.PlaygamePage;
import Listeners.CustomTestListener;

@Listeners(CustomTestListener.class)
public class PlaygameTest extends BaseTest {

    private PlaygamePage playgamePage;

    @BeforeClass
    public void setUpPage() {
        playgamePage = new PlaygamePage(driver);
    }

    @Test(priority = 1)
    public void testOpenPoliceGame() {
        playgamePage.openPoliceGame();
    }

    @Test(priority = 2)
    public void testOpenChangaChaitGame() {
        playgamePage.openChangaChaitGame();
    }

    @Test(priority = 7)
    public void testOpenTimberGuyGame() {
        playgamePage.openTimberGuyGame();
    }

    @Test(priority = 4)
    public void testOpenStickyGooGame() {
        playgamePage.openStickyGooGame();
    }

    @Test(priority = 5)
    public void testOpenLudoWithFriendsGame() {
        playgamePage.openLudoWithFriendsGame();
    }

    @Test(priority = 6)
    public void testOpenCarromHeroGame() {
        playgamePage.openCarromHeroGame();
    }

    @Test(priority = 3)
    public void testOpenCandyFiestaGame() {
        playgamePage.openCandyFiestaGame();
    }
}

