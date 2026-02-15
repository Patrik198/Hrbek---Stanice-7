import java.util.ArrayList;

public class Predmet {

        private int vaha;
        private String nazev;
        private String popis;
        private boolean prenosny;
        private ArrayList<String> veci = new ArrayList<String>();

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public int getVaha() {
        return vaha;
    }

    public boolean isPrenosny() {
        return prenosny;
    }

    public void setVaha(int vaha) {
        this.vaha = vaha;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setPrenosny(boolean prenosny) {
        this.prenosny = prenosny;
    }

    public boolean pridejveci(Predmet p) {
        return veci.add(String.valueOf(p));
    }
}
