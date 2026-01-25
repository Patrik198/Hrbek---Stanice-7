import java.util.Locale;
import java.util.Scanner;

public class Hra {

    private Svet svet;
    private Mistnost aktualnimistnost;
    private boolean bezi = true;

    public void hraj(){
        try{
            svet = Svet.nactijson("mapa.json");
            aktualnimistnost = svet.getPocatecniMistnost();

            Scanner sc = new Scanner(System.in);
            System.out.println("--- VESMÍRNÁ STANICE AEGIS ---");

            while (bezi){
                System.out.println("\nJsi v: " + aktualnimistnost.getId());
                System.out.println(aktualnimistnost.getPopis());
                System.out.print(">>> ");

                String radek = sc.nextLine().toLowerCase().trim();
                String[] slova = radek.split(" ");
                String prikaz = slova[0];

                if (prikaz.equals("konec")){
                    bezi = false;
                } else if (prikaz.equals("jdi") && slova.length > 1) {
                    zpracujPohyb(slova[1]);
                }else{
                    System.out.println("Nerozumím. Zkus 'jdi [směr]' nebo 'konec'.");
                }
            }
        } catch (Exception e) {
            // Pokud např. chybí soubor nebo je v JSONu chyba, vypíše se varování
            System.out.println("Kritická chyba: " + e.getMessage());
        }
    }

    private void zpracujPohyb(String smer){

        // Zeptáme se aktuální místnosti: "Máš východ tímto směrem?"
        String IDvychodu = aktualnimistnost.getSmerVychodu(smer);

        if (IDvychodu != null){
            Mistnost nova = svet.najdiMistnost(IDvychodu);
            if (nova != null){
                aktualnimistnost = nova;
                System.out.println("Přešel jsi do lokace: " + aktualnimistnost.getId());
            }
        }
    }

    //TODO prikaz vezmi, prozkoumej, pomoc, napoveda, poloz, mluv, pouzij

    public void registrujPrikaz(){}
    public Mistnost getAktualniMistnost(){}
    public void setAktualniMistnost(Mistnost m){}
    public boolean pridejDoBatohu(Predmet p){}


}
