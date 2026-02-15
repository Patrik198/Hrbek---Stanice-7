import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrikazVezmiTest {

    @Test
    void testVezmiPredmet() {
        Hra hra = new Hra();
        Mistnost m = new Mistnost();

        Predmet p = new Predmet();
        p.setNazev("klic");
        p.setPopis("");
        p.setVaha(1);
        p.setPrenosny(true);

        m.pridejPredmet(p);
        hra.setAktualnimistnost(m);

        PrikazVezmi vezmi = new PrikazVezmi();
        vezmi.proved(new String[]{"vezmi", "klic"}, hra);

        assertNotNull(hra.getInv().najdiPredmet("klic"));
        assertNull(m.najdiPredmet("klic"));
    }
}
