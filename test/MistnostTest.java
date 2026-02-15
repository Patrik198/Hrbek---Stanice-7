import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MistnostTest {

    @Test
    void testVytvoreniMistnosti() {
        Mistnost m = new Mistnost("chodba", "Hlavní chodba");

        assertEquals("chodba", m.getId());
        assertEquals("Hlavní chodba", m.getPopis());
    }

    @Test
    void testPridaniVychodu() {
        Mistnost m = new Mistnost("chodba", "Hlavní chodba");

        m.pridejVychod("sever", "kuchyn");

        assertEquals("kuchyn", m.getSmerVychodu("sever"));
    }

    @Test
    void testNenalezenyVychod() {
        Mistnost m = new Mistnost("chodba", "Hlavní chodba");

        assertNull(m.getSmerVychodu("jih"));
    }
}
