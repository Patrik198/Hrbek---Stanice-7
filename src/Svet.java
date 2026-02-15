import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Svet {
    private String startovnimistnost;
    private List<Mistnost> mistnosti;

    private Map<String, Mistnost> mapamistnosti = new HashMap<>();

    public static Svet nactijson(String cesta) throws Exception {
        Gson gson = new Gson();

        // NAJDE soubor v resources (src/test/resources nebo src/main/resources)
        InputStream stream = Svet.class.getClassLoader().getResourceAsStream(cesta);

        if (stream == null) {
            throw new RuntimeException("Soubor nenalezen: " + cesta);
        }

        InputStreamReader reader = new InputStreamReader(stream);

        Svet svet = gson.fromJson(reader, Svet.class);

        // naplníme mapu místností
        for (Mistnost m : svet.mistnosti) {
            svet.mapamistnosti.put(m.getId(), m);
        }

        return svet;
    }

    public Mistnost najdiMistnost(String id) {
        return mapamistnosti.get(id);
    }

    public Mistnost getPocatecniMistnost() {
        return mapamistnosti.get(startovnimistnost);
    }

    public Map<String, Mistnost> getMapamistnosti() {
        return mapamistnosti;
    }
}
