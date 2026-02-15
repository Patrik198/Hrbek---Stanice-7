import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventarTest {

    @Test
    void testPridejPredmet() {
        Inventar inv = new Inventar();
        Predmet klic = new Predmet();

        assertTrue(inv.pridejPredmet(klic));
        assertEquals(1, inv.getInventar().size());
    }

    @Test
    void testKapacitaInventare() {
        Inventar inv = new Inventar();
        inv.pridejPredmet(new Predmet());
        inv.pridejPredmet(new Predmet());
        inv.pridejPredmet(new Predmet());

        assertFalse(inv.pridejPredmet(new Predmet()));
    }

    @Test
    void testOdeberPredmet() {
        Inventar inv = new Inventar();
        Predmet p = new Predmet();
        inv.pridejPredmet(p);

        assertTrue(inv.odeberPredmet(p));
        assertEquals(0, inv.getInventar().size());
    }
}
