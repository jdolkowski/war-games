import org.junit.Test;
import static org.junit.Assert.*;
public class GeneralTest {
    @Test
    public void createGeneral(){
        General sut = new General();
        assertNotNull(sut);
    }
    @Test
    public void removeSoldier(){
        General sut = new General();
        CorporalSoldier corporal = new CorporalSoldier(sut);
        sut.removeSoldier(corporal);
        assertEquals(0, sut.getSoldiers().size());
    }
    @Test
    public void addArmyExperience(){
        General sut = new General();
        CorporalSoldier corporal = new CorporalSoldier(sut);
        MajorSoldier major = new MajorSoldier(sut);
        sut.addArmyExperience();
        assertEquals(2, sut.getSoldiers().get(0).getExperience());
        assertEquals(2, sut.getSoldiers().get(1).getExperience());
        assertEquals(18, sut.getBalance());
    }
    @Test
    public void addExperience(){
        General sut = new General();
        CorporalSoldier corporal = new CorporalSoldier(sut);
        MajorSoldier major = new MajorSoldier(sut);
        MajorSoldier major2 = new MajorSoldier(sut);
        sut.addSoldiersExperience(major.getClass(), 1);
        assertEquals(1, sut.getSoldiers().get(0).getExperience());
        assertEquals(2, sut.getSoldiers().get(1).getExperience());
        assertEquals(1, sut.getSoldiers().get(2).getExperience());
        assertEquals(19, sut.getBalance());
    }
    @Test(expected = IllegalArgumentException.class)
    public void tooMaySoldiersTest(){
        General sut = new General();
        CorporalSoldier corporal = new CorporalSoldier(sut);
        MajorSoldier major = new MajorSoldier(sut);
        MajorSoldier major2 = new MajorSoldier(sut);
        sut.addSoldiersExperience(major.getClass(), 4);
        assertEquals(1, sut.getSoldiers().get(0).getExperience());
        assertEquals(2, sut.getSoldiers().get(1).getExperience());
        assertEquals(1, sut.getSoldiers().get(2).getExperience());
        assertEquals(19, sut.getBalance());
    }
    @Test
    public void attackGeneralTest(){
        General sut = new General();
        CorporalSoldier corporal = new CorporalSoldier(sut);
        MajorSoldier major = new MajorSoldier(sut);
        MajorSoldier major2 = new MajorSoldier(sut);

        General sut1 = new General();
        PrivateSoldier ps1 = new PrivateSoldier(sut1);
        PrivateSoldier ps2 = new PrivateSoldier(sut1);
        PrivateSoldier ps3 = new PrivateSoldier(sut1);

        sut.attackGeneral(sut1);
        assertEquals(18, sut1.getBalance());
        assertEquals(22, sut.getBalance());
    }
    @Test
    public void drawTest(){
        General sut = new General();
        CorporalSoldier corporal = new CorporalSoldier(sut);
        MajorSoldier major = new MajorSoldier(sut);
        MajorSoldier major2 = new MajorSoldier(sut);

        General sut1 = new General();
        CorporalSoldier corporal3 = new CorporalSoldier(sut1);
        MajorSoldier major4 = new MajorSoldier(sut1);
        MajorSoldier major5 = new MajorSoldier(sut1);

        sut.attackGeneral(sut1);

        assertEquals(2, sut.getSoldiers().size());
        assertEquals(2, sut1.getSoldiers().size());
    }
    @Test
    public void calculateArmyStrengthTest(){
        General sut = new General();
        CorporalSoldier corporal = new CorporalSoldier(sut);
        MajorSoldier major = new MajorSoldier(sut);
        MajorSoldier major2 = new MajorSoldier(sut);

        int strength = sut.calculateArmyStrength(sut);
        assertEquals(10, strength);
    }
    @Test
    public void buySoldiersTest(){
        General sut = new General();
        sut.buySoldier("private", 2);
        assertEquals(2, sut.getSoldiers().size());
        assertEquals(0, sut.getBalance());
    }

}
