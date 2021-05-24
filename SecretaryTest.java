import org.junit.Test;
import static org.junit.Assert.*;
public class SecretaryTest {
    @Test
    public void notificationTest(){
        General sut = new General();
        sut.buySoldier("private", 2);
        //assertEquals("zakupionych zostało 2 private",sut.getObserverList().get(0).getLogs().);
    }
}
