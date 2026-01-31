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
        if (aktualnikapacita >= kapacita) {
            return false;  // Inventář je plný
        }
        inventar.add(p);
        aktualnikapacita += p.getVaha();
        return true;
    }

    public String zobrazObsah(){
        if (inventar == null || inventar.isEmpty()){
            return "inventar je prazdny";
        }

        String inv = "V inventari mas: ";
        List<String> nazvy = new ArrayList<>();
        for (Predmet p : inventar){
            nazvy.add(p.getNazev());
        }

        inv += String.join(", ", nazvy);
        inv += "\nvolná kapacita: " + (kapacita - aktualnikapacita);
        return inv;
    }

    public List<Predmet> getInventar() {
        return inventar;
    }

}
