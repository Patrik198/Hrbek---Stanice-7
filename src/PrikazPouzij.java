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

    public String getBarva(){
        return Barvicky.CYAN;
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
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN +"┌────────────────────────────────────┐" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN +"│ Magnetický klíč zabrousil do zámku │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN +"│ Dveře se s tiším syčením otevřely  │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN +"│ Cesta je volná!                    │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN +"└────────────────────────────────────┘" + Barvicky.ANSI_RESET);
                        hra.setKryokomoraOdemknuta(true);
                        inv.odeberPredmet(predmet);

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
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "┌────────────────────────────────────┐" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Vložil jsi článek do generátoru    │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ *BZZZZZT* Světla se rozsvítila!    │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Stanice má opět plnou energii!     │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "└────────────────────────────────────┘" + Barvicky.ANSI_RESET);
                        hra.setEnergieObnovena(true);
                        inv.odeberPredmet(predmet);
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
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "┌────────────────────────────────────┐" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Pomocí multitoolu opravuješ vodič  │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Terminál se rozsvítil!             │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Systém je připravený k deaktivaci  │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "└────────────────────────────────────┘" + Barvicky.ANSI_RESET);
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
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "┌────────────────────────────────────┐" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Vložil jsi datový čip do terminálu │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Systém vyžaduje přístupový kód...  │" + Barvicky.ANSI_RESET);
                        p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│                                    │" + Barvicky.ANSI_RESET);
                        p.print(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Zadej kód (6 číslic): " + Barvicky.ANSI_RESET);

                        Scanner sc = new Scanner(System.in);
                        String kod = sc.nextLine().trim();

                        if (kod.equals("582136")) {
                            p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ ✓ KÓD PŘIJAT                       │" + Barvicky.ANSI_RESET);
                            p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Záchranný systém deaktivován!      │" + Barvicky.ANSI_RESET);
                            p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "└────────────────────────────────────┘" + Barvicky.ANSI_RESET);
                            hra.setSystemDeaktivovan(true);
                            inv.odeberPredmet(predmet);
                            hra.zkontrolujVyhru();
                        } else {
                            p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ ✗ CHYBNÝ KÓD                       │" + Barvicky.ANSI_RESET);
                            p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│ Hint: Promluv s UI a prozkoumej    │" + Barvicky.ANSI_RESET);
                            p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "│       starý deník v Archivu        │" + Barvicky.ANSI_RESET);
                            p.println(Barvicky.ANSI_BOLD + Barvicky.CYAN + "└────────────────────────────────────┘" + Barvicky.ANSI_RESET);
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
