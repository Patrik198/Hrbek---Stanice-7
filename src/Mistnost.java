import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Třída reprezentuje jednu lokalitu ve hře.
 */
public class Mistnost {

    private String id;
    private String popis; // Text, který se hráči zobrazí po příchodu
    private Map<String, String> vychody; // Směr -> ID cílové místnosti (<smer, mistnost>)
    private List<Predmet> predmety = new ArrayList<>();
    private List<Postava> postavy = new ArrayList<>();
    private Printovanitextu p;


    // Getter pro ID, abychom mohli vypsat, kde hráč je
    public String getId() {
        return id;
    }

    // Getter pro popis místnosti
    public String getPopis() {
        return popis;
    }

    public List<Predmet> getPredmety(){
        return predmety;
    }

    /**
     * Klíčová metoda pro pohyb.
     * Podívá se do mapy východů, jestli pro daný směr existuje cesta.
     * @param smer Směr, který hráč zadal (např. "sever")
     * @return ID cílové místnosti nebo null, pokud cesta neexistuje
     */
    public String getSmerVychodu(String smer) {
        if (vychody == null)
            return null;
        return vychody.get(smer);

    }

    public boolean pridejPredmet(Predmet p){
        return predmety.add(p);
    }

    public boolean odeberPredmet(Predmet p){
        return predmety.remove(p);
    }

    public Postava najdiPostavu(String jmeno) {
        for (Postava p : postavy) {
            if (p.getJmeno().equalsIgnoreCase(jmeno)) {
                return p;
            }
        }
        return null;
    }

    public Predmet najdiPredmet(String nazev) {
        for (Predmet p : predmety) {
            if (p.getNazev().equalsIgnoreCase(nazev)) {
                return p;
            }
        }
        return null;
    }

    public String zobrazPredmety(){
        if (predmety == null || predmety.isEmpty()){
            return "Neni tu nic";
        }

        List<String> nazvy = new ArrayList<>();
        for (Predmet p : predmety){
            nazvy.add(p.getNazev());
        }
        return String.join(", ", nazvy);
    }

    public String zobrazVychody() {
        if (vychody == null || vychody.isEmpty()) {
            return "Odtud nevede žádná cesta.";
        }

        String severjihzapadvychod = "\n";

        for (String smer : vychody.keySet()) {
            String mistnost = vychody.get(smer);
            severjihzapadvychod += smer + " → " + mistnost + "\n";
        }

        return severjihzapadvychod;
    }
}
