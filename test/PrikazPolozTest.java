import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrikazPolozTest {

    @Test
    void testPolozPredmet() {
        // Vytvoření hry a místnosti
        Hra hra = new Hra();
        Mistnost m = new Mistnost();
        hra.setAktualnimistnost(m);

        // Vytvoření předmětu přes defaultní konstruktor a settery
        Predmet p = new Predmet();
        p.setNazev("klic");
        p.setPopis("");
        p.setVaha(1);
        p.setPrenosny(true);

        // Přidání předmětu do inventáře
        hra.getInv().pridejPredmet(p);

        // Vykonání příkazu položit
        PrikazPoloz poloz = new PrikazPoloz();
        poloz.proved(new String[]{"poloz", "klic"}, hra);

        // Ověření, že předmět je ve místnosti
        assertNotNull(m.najdiPredmet("klic"));
    }
}
