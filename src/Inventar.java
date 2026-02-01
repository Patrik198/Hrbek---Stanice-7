import java.util.ArrayList;
import java.util.List;

public class Inventar {

    private int kapacita;
    private int aktualnikapacita;
    private List<Predmet> inventar;

    public Inventar() {
        this.kapacita = 3;
        this.aktualnikapacita = 0;
        this.inventar = new ArrayList<>();
    }

    public boolean pridejPredmet(Predmet p) {
        if (aktualnikapacita + p.getVaha() > kapacita) {
            return false;  // Inventář je plný
        }
        inventar.add(p);
        aktualnikapacita += p.getVaha();
        return true;
    }

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

    public Predmet najdiPredmet(String nazevPredmetu) {
        for (Predmet p : inventar){
            if (p != null && p.getNazev().equals(nazevPredmetu)){
                return p;
            }
        }
        return null;
    }
}
