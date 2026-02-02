import java.util.Scanner;

public class PrikazPouzij implements Prikaz{
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
            System.out.println("Co mám použít?");
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
            System.out.println("Tento předmět nemáš v inventáři.");
            return;
        }

        switch (predmet.getNazev().toLowerCase()) {

            case "magneticky klic":
                if (aktualni.getId().equals("Kryokomora")) {
                    if (hra.isKryokomoraOdemknuta()) {
                        System.out.println("Dveře jsou už odemčené.");
                    } else {
                        System.out.println("┌────────────────────────────────────┐");
                        System.out.println("│ Magnetický klíč zabrousil do zámku │");
                        System.out.println("│ Dveře se s tiším syčením otevřely  │");
                        System.out.println("│ Cesta je volná!                    │");
                        System.out.println("└────────────────────────────────────┘");
                        hra.setKryokomoraOdemknuta(true);
                        inv.odeberPredmet(predmet);
                    }
                } else {
                    System.out.println("Magnetický klíč se dá použít jen v Kryokomoře.");
                }
                break;

            case "energeticky clanek":
                if (aktualni.getId().equals("Strojovna")) {
                    if (hra.isEnergieObnovena()) {
                        System.out.println("Generátor už běží na plný výkon.");
                    } else {
                        System.out.println("┌────────────────────────────────────┐");
                        System.out.println("│ Vložil jsi článek do generátoru    │");
                        System.out.println("│ *BZZZZZT* Světla se rozsvítila!    │");
                        System.out.println("│ Stanice má opět plnou energii!     │");
                        System.out.println("└────────────────────────────────────┘");
                        hra.setEnergieObnovena(true);
                        inv.odeberPredmet(predmet);
                        hra.zkontrolujVyhru();
                    }
                } else {
                    System.out.println("Energetický článek se dá použít jen ve Strojovně.");
                }
                break;

            case "multitool":
                if (aktualni.getId().equals("Mustek")) {
                    if (!hra.isEnergieObnovena()) {
                        System.out.println("Terminál nemá napájení. Nejdřív musíš obnovit energii ve Strojovně!");
                    } else if (hra.isTerminalOpraven()) {
                        System.out.println("Terminál už je opravený.");
                    } else {
                        System.out.println("┌────────────────────────────────────┐");
                        System.out.println("│ Pomocí multitoolu opravuješ vodič  │");
                        System.out.println("│ Terminál se rozsvítil!             │");
                        System.out.println("│ Systém je připravený k deaktivaci  │");
                        System.out.println("└────────────────────────────────────┘");
                        hra.setTerminalOpraven(true);
                    }
                } else {
                    System.out.println("Multitool se dá použít jen na Můstku.");
                }
                break;

            case "datovy cip":
                if (aktualni.getId().equals("Mustek")) {
                    if (!hra.isTerminalOpraven()) {
                        System.out.println("Terminál nefunguje! Nejdřív ho musíš opravit multitoolem.");
                    } else if (hra.isSystemDeaktivovan()) {
                        System.out.println("Záchranný systém už je deaktivovaný.");
                    } else {
                        System.out.println("┌────────────────────────────────────┐");
                        System.out.println("│ Vložil jsi datový čip do terminálu │");
                        System.out.println("│ Systém vyžaduje přístupový kód...  │");
                        System.out.println("│                                    │");
                        System.out.print("│ Zadej kód (6 číslic): ");

                        Scanner sc = new Scanner(System.in);
                        String kod = sc.nextLine().trim();

                        if (kod.equals("582136")) {
                            System.out.println("│ ✓ KÓD PŘIJAT                       │");
                            System.out.println("│ Záchranný systém deaktivován!      │");
                            System.out.println("└────────────────────────────────────┘");
                            hra.setSystemDeaktivovan(true);
                            inv.odeberPredmet(predmet);
                            hra.zkontrolujVyhru();
                        } else {
                            System.out.println("│ ✗ CHYBNÝ KÓD                       │");
                            System.out.println("│ Hint: Promluv s UI a prozkoumej    │");
                            System.out.println("│       starý deník v Archivu        │");
                            System.out.println("└────────────────────────────────────┘");
                        }
                    }
                } else {
                    System.out.println("Datový čip se dá použít jen na Můstku.");
                }
                break;

            default:
                System.out.println("Tento předmět nelze použít.");
                break;
        }
    }
}
