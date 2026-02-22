import java.util.ArrayList;
import java.util.List;

public class Inventar {

    private int kapacita;
    private int aktualnikapacita;
    private List<Predmet> inventar;

    /**
     * Konstruktor
     */
    public Inventar() {
        this.kapacita = 3;
        this.aktualnikapacita = 0;
        this.inventar = new ArrayList<>();
    }

    /**
     * Metoda pro přidání předmětu do Listu
     * @param p - instance třídy Předmět
     * @return - vrácí false pokud je inventár plný jinak je vždy true
     */
    public boolean pridejPredmet(Predmet p) {
        if (inventar.size() >= kapacita) {
            return false;
        }
        inventar.add(p);
        aktualnikapacita += p.getVaha();
        return true;
    }

    /**
     * Odebere předmět z Listu
     * @param p - instance třídy Předmět
     * @return - vrací false pokud předmět neexistuje nebo je null jinak je vždy true
     */
    public boolean odeberPredmet(Predmet p){
        if (p == null){
            return false;
        }

        if(inventar.remove(p)) {
            aktualnikapacita -= p.getVaha();
            return true;
        }
        return false;
    }

    /**
     * Zobrazí obsah inventáře
     * @return - vrací chybnou hlášku pokud je inventár prázdný jinak vždy sepíše aktualní obsah inventáře
     */
    public String zobrazObsah(){
        if (inventar == null || inventar.isEmpty()){
            return "inventar je prazdny";
        }

        String inv = "V inventari mas: ";
        List<String> nazvy = new ArrayList<>();
        for (Predmet p : inventar){
            if (p != null) {
                nazvy.add(p.getNazev());
            }
        }

        inv += String.join(", ", nazvy);
        inv += "\nvolná kapacita: " + (kapacita - aktualnikapacita);
        return inv;
    }

    /**
     * Pomocná metoda pro nalezení předmetu v místnosti
     * @param nazevPredmetu - String
     * @return - vrací null pokud nebyl žádný předmět nalezen jinak vrací název předmětu
     */
    public Predmet najdiPredmet(String nazevPredmetu) {
        for (Predmet p : inventar){
            if (p != null && p.getNazev().equals(nazevPredmetu)){
                return p;
            }
        }
        return null;
    }

    public List<Predmet> getInventar() {
        return inventar;
    }
}
