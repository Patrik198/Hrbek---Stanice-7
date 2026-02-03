public class PrikazPomoc implements Prikaz{

    private Hra hra;
    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "pomoc";
    }

    @Override
    public String getPopis() {
        return "Zobrazí detailní nápovědu k příkazu";
    }

    public String getBarva(){
        return Barvicky.BLUE;
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        this.hra = hra;

        if (prikaz.length < 2){
            p.println("Použití: pomoc <název příkazu>");
            p.println("Například: pomoc vezmi");
            p.println("\nPro seznam všech příkazů napiš: napoveda");
            return;
        }

        String nazevprikazu = prikaz[1];

        Prikaz po = hra.najdiPrikaz(nazevprikazu);

        if (po == null) {
            p.println("Příkaz '" + nazevprikazu + "' neznám.");
            p.println("Pro seznam příkazů napiš: napoveda");
            return;
        }

        p.println("\n=== NÁPOVĚDA: " + nazevprikazu.toUpperCase() + " ===");
        zobrazDetailniNapovedu(nazevprikazu);
    }

    private void zobrazDetailniNapovedu(String prikaz) {
        switch (prikaz) {
            case "vezmi":
                p.println("Použití: vezmi <název předmětu>");
                p.println("Popis: Vezme předmět z místnosti do inventáře");
                p.println("Příklad: vezmi magneticky klic");
                p.println("\nPozor: Inventář má omezenou nosnost!");
                break;

            case "poloz":
                p.println("Použití: poloz <název předmětu>");
                p.println("Popis: Položí předmět z inventáře do místnosti");
                p.println("Příklad: poloz magneticky klic");
                break;

            case "jdi":
                p.println("Použití: jdi <směr>");
                p.println("Popis: Přesune tě do vedlejší místnosti");
                p.println("Směry: sever, jih, vychod, zapad");
                p.println("Příklad: jdi sever");
                break;

            case "batoh":
                p.println("Použití: batoh");
                p.println("Popis: Zobrazí obsah tvého inventáře");
                p.println("Uvidíš všechny předměty a nosnost");
                break;

            case "prozkoumej":
                p.println("Použití: prozkoumej");
                p.println("Popis: Detailně popíše co item dělá a k čemu je");
                break;

            case "pouzij":
                p.println("Použití: pouzij <předmět>");
                p.println("Popis: Použije předmět z inventáře");
                p.println("Příklad: pouzij magneticky klic");
                break;

            case "mluv":
                p.println("Použití: mluv");
                p.println("Popis: Promluví s postavou v místnosti");
                break;

            case "napoveda":
                p.println("Použití: napoveda");
                p.println("Popis: Zobrazí seznam všech dostupných příkazů");
                break;

            case "konec":
                p.println("Použití: konec");
                p.println("Popis: Ukončí hru");
                break;

            default:
                p.println("Pro tento příkaz není nápověda.");
                break;
        }
    }
}
