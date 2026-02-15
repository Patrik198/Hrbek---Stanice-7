import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PrikazJdiTest {

    @Test
    void testJdi() {
        Hra hra = new Hra();
        Svet svet = new Svet();

        Mistnost a = new Mistnost();
        a.setId("A");
        Mistnost b = new Mistnost();
        b.setId("B");

        Map<String, String> vychody = new HashMap<>();
        vychody.put("sever", "B");
        a.setVychody(vychody);

        svet.getMapamistnosti().put("A", a);
        svet.getMapamistnosti().put("B", b);

        hra.setSvet(svet);
        hra.setAktualnimistnost(a);
        hra.setKryokomoraOdemknuta(true);

        PrikazJdi jdi = new PrikazJdi();
        jdi.proved(new String[]{"jdi", "sever"}, hra);

        assertEquals("B", hra.getAktualnimistnost().getId());
    }

}
