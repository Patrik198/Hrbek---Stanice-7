import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hra {

    private Svet svet;
    private Mistnost aktualnimistnost;
    private boolean bezi = true;
    private Inventar inv;

    private Map<String, Prikaz> seznamprikazu = new HashMap<>();

    private boolean kryokomoraOdemknuta = false;
    private boolean energieObnovena = false;
    private boolean terminalOpraven = false;
    private boolean systemDeaktivovan = false;

    public void hraj(){
        try{
            svet = Svet.nactijson("resource/mapa.json");
            aktualnimistnost = svet.getPocatecniMistnost();
            inv = new Inventar();

            registrujprikaz(new PrikazJdi());
            registrujprikaz(new PrikazKonec());
            registrujprikaz(new PrikazMluv());
            registrujprikaz(new PrikazNapoveda());
            registrujprikaz(new PrikazPomoc());
            registrujprikaz(new PrikazPouzij());
            registrujprikaz(new PrikazProzkoumej());
            registrujprikaz(new PrikazVezmi());
            registrujprikaz(new PrikazPoloz());
            registrujprikaz(new PrikazInventar());



            Scanner sc = new Scanner(System.in);
            System.out.println("--- VESMÍRNÁ STANICE AEGIS ---");

            System.out.println("\nseznam prikazu: " + seznamprikazu.keySet());

            while (bezi){
                System.out.println("\nJsi v: " + aktualnimistnost.getId());
                System.out.println(aktualnimistnost.getPopis() + " a nachazi se zde predmět: " + aktualnimistnost.zobrazPredmety());
                System.out.println("\nVedlejsi mistnosti: " + aktualnimistnost.zobrazVychody());
                System.out.print(">>> ");

                String radek = sc.nextLine().toLowerCase().trim();
                String[] slova = radek.split(" ");
                String prikaz = slova[0];

                if (seznamprikazu.containsKey(prikaz)){
                    Prikaz p = seznamprikazu.get(prikaz);
                    p.proved(slova, this);
                }else{
                    System.out.println("Tento prikaz neznam! zkus pouzit prikaz napoveda");
                }
            }
        } catch (Exception e) {
            // Pokud např. chybí soubor nebo je v JSONu chyba, vypíše se varování
            System.out.println("Kritická chyba: " + e.getMessage());
            }
        }

        public void registrujprikaz(Prikaz p){
            seznamprikazu.put(p.getNazev(), p);
        }

        public void zkontrolujVyhru() {
        if (terminalOpraven && systemDeaktivovan && energieObnovena) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║    GRATULUJEME! ÚSPĚŠNĚ JSI UTEKL!    ║");
            System.out.println("║   Stanice AEGIS je opět funkční a     ║");
            System.out.println("║   záchranný systém je deaktivován.    ║");
            System.out.println("║         Mise dokončena!               ║");
            System.out.println("╚════════════════════════════════════════╝\n");
            setBezi(false);
        }
    }

        public Mistnost getAktualnimistnost(){
            return aktualnimistnost;
        }

        public void setAktualnimistnost(Mistnost m){
            this.aktualnimistnost = m;
        }

        public void setBezi(boolean stav) {
            this.bezi = stav;
        }

        public Prikaz najdiPrikaz(String nazev){
            return seznamprikazu.get(nazev);
        }

        public Svet getSvet(){
            return svet;
        }

        public Inventar getInv() {
        return inv;
    }

        public boolean isKryokomoraOdemknuta() {
        return kryokomoraOdemknuta;
    }

        public void setKryokomoraOdemknuta(boolean stav) {
        this.kryokomoraOdemknuta = stav;
    }

        public boolean isEnergieObnovena() {
        return energieObnovena;
    }

        public void setEnergieObnovena(boolean stav) {
        this.energieObnovena = stav;
    }

        public boolean isTerminalOpraven() {
        return terminalOpraven;
    }

        public void setTerminalOpraven(boolean stav) {
        this.terminalOpraven = stav;
    }

        public boolean isSystemDeaktivovan() {
        return systemDeaktivovan;
    }

        public void setSystemDeaktivovan(boolean stav) {
        this.systemDeaktivovan = stav;
    }

}

    //TODO prikaz vezmi, prozkoumej, pomoc, napoveda, poloz, mluv, pouzij

//    public void registrujPrikaz(){}
//    public Mistnost getAktualniMistnost(){}
//    public void setAktualniMistnost(Mistnost m){}
//    public boolean pridejDoBatohu(Predmet p){}


//}
