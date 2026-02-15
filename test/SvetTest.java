import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SvetTest {

    @Test
    void testNacteniJson() throws Exception {
        // Načteme JSON
        Svet svet = Svet.nactijson("resources/mapa.json");

        // Ověříme, že objekt není null
        assertNotNull(svet, "Svet by nemel byt null");

        // Ověříme, že počáteční místnost existuje
        Mistnost pocatecni = svet.getPocatecniMistnost();
        assertNotNull(pocatecni, "Pocatecni mistnost by nemela byt null");

        // Ověříme ID počáteční místnosti podle JSONu
        assertEquals("Kryokomora", pocatecni.getId(), "ID počáteční místnosti nesouhlasí");
    }

    @Test
    void testNajdiMistnost() throws Exception {
        Svet svet = Svet.nactijson("resources/mapa.json");

        // Vyhledáme konkrétní místnost
        Mistnost sklad = svet.najdiMistnost("Sklad");

        // Ověříme, že místnost existuje a má správné ID
        assertNotNull(sklad, "Mistnost Sklad by mela existovat");
        assertEquals("Sklad", sklad.getId(), "ID mistnosti nesouhlasi");
    }
}