import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hra {

    private Svet svet;
    private Mistnost aktualnimistnost;
    private boolean bezi = true;
    private Inventar inv;

    Printovanitextu p = new Printovanitextu();

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
            p.println(Barvicky.CYAN + "--- VESMÍRNÁ STANICE AEGIS ---" + Barvicky.ANSI_RESET);

            p.print("\nseznam prikazu: [");
            int i = 0;
            for (String nazev : seznamprikazu.keySet()) {
                Prikaz prikaz = seznamprikazu.get(nazev);
                p.print(prikaz.getBarva() + nazev + Barvicky.ANSI_RESET);

                if (i < seznamprikazu.size() - 1) {
                    p.print(", ");
                }
                i++;
            }
            p.println("]");

            while (bezi){
                p.println("\nJsi v: " + Barvicky.ANSI_BOLD + Barvicky.CRYO_BLUE_BG + aktualnimistnost.getId() + Barvicky.ANSI_RESET);
                p.println(aktualnimistnost.getPopis() + " a nachazi se zde predmět: " + aktualnimistnost.zobrazPredmety());
                p.println("\nVedlejsi mistnosti: " + aktualnimistnost.zobrazVychody());
                p.print(">>> ");

                String radek = sc.nextLine().toLowerCase().trim();
                String[] slova = radek.split(" ");
                String prikaz = slova[0];

                if (seznamprikazu.containsKey(prikaz)){
                    Prikaz pr = seznamprikazu.get(prikaz);
                    pr.proved(slova, this);
                }else{
                    p.println("Tento prikaz neznam! zkus pouzit prikaz napoveda");
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
            p.println("\n╔════════════════════════════════════════╗");
            p.println("║    GRATULUJEME! ÚSPĚŠNĚ JSI UTEKL!    ║");
            p.println("║   Stanice AEGIS je opět funkční a     ║");
            p.println("║   záchranný systém je deaktivován.    ║");
            p.println("║         Mise dokončena!               ║");
            p.println("╚════════════════════════════════════════╝\n");
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
