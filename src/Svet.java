import com.google.gson.Gson;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Svet {
    private String startovnimistnost; //mistnost kde hrac zacina
    private List<Mistnost> mistnosti; //seznam vsech mistnosti nactenych z Jsonu

    private Map <String, Mistnost> mapamistnosti = new HashMap<>(); // Pomocná mapa pro rychlé hledání podle ID (naplníme po načtení)

    /**
     * Statická metoda, která vytvoří instanci světa ze souboru.
     */
    public static Svet nactijson(String cesta) throws Exception{
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(cesta)){

            // GSON přečte JSON a vytvoří objekty tříd Svet a Mistnost
            Svet svet = gson.fromJson(reader, Svet.class);

            // Projdeme seznam místností a uložíme je do mapy (indexu)
            // To nám umožní později hledat místnosti podle jména bez procházení celého seznamu
            for (Mistnost m : svet.mistnosti){
                svet.mapamistnosti.put(m.getId(), m);
            }
            return svet;
        }
    }

    // Najde v mapě skutečný objekt místnosti podle textového ID
    public Mistnost najdiMistnost(String id) {
        return mapamistnosti.get(id);
    }

    // Vrátí objekt místnosti, ve které má hráč začít
    public Mistnost getPocatecniMistnost() {
        return mapamistnosti.get(startovnimistnost);
    }
}
