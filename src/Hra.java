import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hra {

    private Svet svet;
    private Mistnost aktualnimistnost;
    private boolean bezi = true;

    private Map<String, Prikaz> seznamprikazu = new HashMap<>();

    public void hraj(){
        try{
            svet = Svet.nactijson("resource/mapa.json");
            aktualnimistnost = svet.getPocatecniMistnost();

            registrujprikaz(new PrikazJdi());
            registrujprikaz(new PrikazKonec());
            registrujprikaz(new PrikazMluv());
            registrujprikaz(new PrikazNapoveda());
            registrujprikaz(new PrikazPomoc());
            registrujprikaz(new PrikazPouzij());
            registrujprikaz(new PrikazProzkoumej());
            registrujprikaz(new PrikazVezmi());
            registrujprikaz(new PrikazPoloz());

            Scanner sc = new Scanner(System.in);
            System.out.println("--- VESMÍRNÁ STANICE AEGIS ---");

            while (bezi){
                System.out.println("\nJsi v: " + aktualnimistnost.getId());
                System.out.println(aktualnimistnost.getPopis());
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

        public Mistnost getAktualnimistnost(){
            return aktualnimistnost;
        }

        public void setAktualnimistnost(Mistnost m){
            this.aktualnimistnost = m;
        }

        public void setBezi(boolean stav) {
            this.bezi = stav;
        }

        public Svet getSvet(){
            return svet;
        }
}

    //TODO prikaz vezmi, prozkoumej, pomoc, napoveda, poloz, mluv, pouzij

//    public void registrujPrikaz(){}
//    public Mistnost getAktualniMistnost(){}
//    public void setAktualniMistnost(Mistnost m){}
//    public boolean pridejDoBatohu(Predmet p){}


//}
