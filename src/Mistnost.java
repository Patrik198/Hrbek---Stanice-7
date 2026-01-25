import java.util.Map;

/**
 * Třída reprezentuje jednu lokalitu ve hře.
 */
public class Mistnost {

    private String id;
    private String popis; // Text, který se hráči zobrazí po příchodu
    private Map<String, String> vychody; // Směr -> ID cílové místnosti

    // Getter pro ID, abychom mohli vypsat, kde hráč je
    public String getId() { return id; }

    // Getter pro popis místnosti
    public String getPopis() { return popis; }

    /**
     * Klíčová metoda pro pohyb.
     * Podívá se do mapy východů, jestli pro daný směr existuje cesta.
     * @param smer Směr, který hráč zadal (např. "sever")
     * @return ID cílové místnosti nebo null, pokud cesta neexistuje
     */
    public String getSmerVychodu(String smer) {
        if (vychody == null) return null;
        return vychody.get(smer);

    }
}
