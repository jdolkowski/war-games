import org.junit.Test;
import static org.junit.Assert.*;


public class SoldierTest {
    @Test
    public void createSoldierTest(){
        General general = new General();
        CorporalSoldier sut = new CorporalSoldier(general);
        assertNotNull(sut);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setExperienceFail(){
        General general = new General();
        CorporalSoldier sut = new CorporalSoldier(general);
        sut.setExperience(-2);
    }
    @Test
    public void addExperienceTest(){
        General general = new General();
        CorporalSoldier sut = new CorporalSoldier(general);
        sut.addExperience();
        assertEquals(2, sut.getExperience());
    }
    @Test
    public void canAdvanceTest(){
        General general = new General();
        CorporalSoldier sut = new CorporalSoldier(general);
        sut.setExperience(11);
        assertTrue(sut.canAdvance());
    }
    @Test
    public void deathTest(){
        General general = new General();
        CorporalSoldier sut = new CorporalSoldier(general);
        sut.setExperience(0);
        sut.executeDeath(sut.isDead());
        Soldier[] empty = {};
        assertArrayEquals(empty, general.getSoldiers().toArray());
    }
    @Test
    public void advanceTest(){
        General general = new General();
        CorporalSoldier sut = new CorporalSoldier(general);
        sut.setExperience(11);
        sut.executeAdvance(sut.canAdvance());
        Soldier[] arrayTest = {new CapitanSoldier(general)};
        assertEquals(arrayTest[0].getClass(), general.getSoldiers().toArray()[0].getClass());
    }
}
