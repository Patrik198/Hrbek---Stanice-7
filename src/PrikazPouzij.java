import java.util.Scanner;

public class PrikazPouzij implements Prikaz{

    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "pouzij";
    }

    @Override
    public String getPopis() {
        return "Použij předmět v místnosti";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        if (prikaz.length < 2){
            p.println("Co mám použít?");
            return;
        }

        // Spojí všechna slova za "pouzij" do jednoho názvu
        String nazevPredmetu = "";
        for (int i = 1; i < prikaz.length; i++) {
            nazevPredmetu += prikaz[i];
            if (i < prikaz.length - 1) {
                nazevPredmetu += " ";
            }
        }

        Mistnost aktualni = hra.getAktualnimistnost();
        Inventar inv = hra.getInv();

        // Kontrola, zda má hráč předmět v inventáři
        Predmet predmet = null;
        for (Predmet p : inv.getInventar()) {
            if (p.getNazev().equalsIgnoreCase(nazevPredmetu)) {
                predmet = p;
                break;
            }
        }

        if (predmet == null) {
            p.println("Tento předmět nemáš v inventáři.");
            return;
        }

        switch (predmet.getNazev().toLowerCase()) {

            case "magneticky klic":
                if (aktualni.getId().equals("Kryokomora")) {
                    if (hra.isKryokomoraOdemknuta()) {
                        p.println("Dveře jsou už odemčené.");
                    } else {
                        p.println("┌────────────────────────────────────┐");
                        p.println("│ Magnetický klíč zabrousil do zámku │");
                        p.println("│ Dveře se s tiším syčením otevřely  │");
                        p.println("│ Cesta je volná!                    │");
                        p.println("└────────────────────────────────────┘");
                        hra.setKryokomoraOdemknuta(true);
                        inv.OdeberPredmet(predmet);
                    }
                } else {
                    p.println("Magnetický klíč se dá použít jen v Kryokomoře.");
                }
                break;

            case "energeticky clanek":
                if (aktualni.getId().equals("Strojovna")) {
                    if (hra.isEnergieObnovena()) {
                        p.println("Generátor už běží na plný výkon.");
                    } else {
                        p.println("┌────────────────────────────────────┐");
                        p.println("│ Vložil jsi článek do generátoru    │");
                        p.println("│ *BZZZZZT* Světla se rozsvítila!    │");
                        p.println("│ Stanice má opět plnou energii!     │");
                        p.println("└────────────────────────────────────┘");
                        hra.setEnergieObnovena(true);
                        inv.OdeberPredmet(predmet);
                        hra.zkontrolujVyhru();
                    }
                } else {
                    p.println("Energetický článek se dá použít jen ve Strojovně.");
                }
                break;

            case "multitool":
                if (aktualni.getId().equals("Mustek")) {
                    if (!hra.isEnergieObnovena()) {
                        p.println("Terminál nemá napájení. Nejdřív musíš obnovit energii ve Strojovně!");
                    } else if (hra.isTerminalOpraven()) {
                        p.println("Terminál už je opravený.");
                    } else {
                        p.println("┌────────────────────────────────────┐");
                        p.println("│ Pomocí multitoolu opravuješ vodič  │");
                        p.println("│ Terminál se rozsvítil!             │");
                        p.println("│ Systém je připravený k deaktivaci  │");
                        p.println("└────────────────────────────────────┘");
                        hra.setTerminalOpraven(true);
                    }
                } else {
                    p.println("Multitool se dá použít jen na Můstku.");
                }
                break;

            case "datovy cip":
                if (aktualni.getId().equals("Mustek")) {
                    if (!hra.isTerminalOpraven()) {
                        p.println("Terminál nefunguje! Nejdřív ho musíš opravit multitoolem.");
                    } else if (hra.isSystemDeaktivovan()) {
                        p.println("Záchranný systém už je deaktivovaný.");
                    } else {
                        p.println("┌────────────────────────────────────┐");
                        p.println("│ Vložil jsi datový čip do terminálu │");
                        p.println("│ Systém vyžaduje přístupový kód...  │");
                        p.println("│                                    │");
                        p.print("│ Zadej kód (6 číslic): ");

                        Scanner sc = new Scanner(System.in);
                        String kod = sc.nextLine().trim();

                        if (kod.equals("582136")) {
                            p.println("│ ✓ KÓD PŘIJAT                       │");
                            p.println("│ Záchranný systém deaktivován!      │");
                            p.println("└────────────────────────────────────┘");
                            hra.setSystemDeaktivovan(true);
                            inv.OdeberPredmet(predmet);
                            hra.zkontrolujVyhru();
                        } else {
                            p.println("│ ✗ CHYBNÝ KÓD                       │");
                            p.println("│ Hint: Promluv s UI a prozkoumej    │");
                            p.println("│       starý deník v Archivu        │");
                            p.println("└────────────────────────────────────┘");
                        }
                    }
                } else {
                    p.println("Datový čip se dá použít jen na Můstku.");
                }
                break;

            default:
                p.println("Tento předmět nelze použít.");
                break;
        }
    }
}
