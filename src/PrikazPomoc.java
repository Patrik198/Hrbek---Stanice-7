public class PrikazPomoc implements Prikaz{

    private Hra hra;

    @Override
    public String getNazev() {
        return "pomoc";
    }

    @Override
    public String getPopis() {
        return "Zobrazí detailní nápovědu k příkazu";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        this.hra = hra;

        if (prikaz.length < 2){
            System.out.println("Použití: pomoc <název příkazu>");
            System.out.println("Například: pomoc vezmi");
            System.out.println("\nPro seznam všech příkazů napiš: napoveda");
            return;
        }

        String nazevprikazu = prikaz[1];

        Prikaz p = hra.najdiPrikaz(nazevprikazu);

        if (p == null) {
            System.out.println("Příkaz '" + nazevprikazu + "' neznám.");
            System.out.println("Pro seznam příkazů napiš: napoveda");
            return;
        }

        System.out.println("\n=== NÁPOVĚDA: " + nazevprikazu.toUpperCase() + " ===");
        zobrazDetailniNapovedu(nazevprikazu);
    }

    private void zobrazDetailniNapovedu(String prikaz) {
        switch (prikaz) {
            case "vezmi":
                System.out.println("Použití: vezmi <název předmětu>");
                System.out.println("Popis: Vezme předmět z místnosti do inventáře");
                System.out.println("Příklad: vezmi magneticky klic");
                System.out.println("\nPozor: Inventář má omezenou nosnost!");
                break;

            case "poloz":
                System.out.println("Použití: poloz <název předmětu>");
                System.out.println("Popis: Položí předmět z inventáře do místnosti");
                System.out.println("Příklad: poloz magneticky klic");
                break;

            case "jdi":
                System.out.println("Použití: jdi <směr>");
                System.out.println("Popis: Přesune tě do vedlejší místnosti");
                System.out.println("Směry: sever, jih, vychod, zapad");
                System.out.println("Příklad: jdi sever");
                break;

            case "batoh":
                System.out.println("Použití: batoh");
                System.out.println("Popis: Zobrazí obsah tvého inventáře");
                System.out.println("Uvidíš všechny předměty a nosnost");
                break;

            case "prozkoumej":
                System.out.println("Použití: prozkoumej");
                System.out.println("Popis: Detailně prozkoumá aktuální místnost");
                System.out.println("Zobrazí předměty, východy a další detaily");
                break;

            case "pouzij":
                System.out.println("Použití: pouzij <předmět>");
                System.out.println("Popis: Použije předmět z inventáře");
                System.out.println("Příklad: pouzij magneticky klic");
                break;

            case "mluv":
                System.out.println("Použití: mluv");
                System.out.println("Popis: Promluví s postavou v místnosti");
                break;

            case "napoveda":
                System.out.println("Použití: napoveda");
                System.out.println("Popis: Zobrazí seznam všech dostupných příkazů");
                break;

            case "konec":
                System.out.println("Použití: konec");
                System.out.println("Popis: Ukončí hru");
                break;

            default:
                System.out.println("Pro tento příkaz není nápověda.");
                break;
        }
    }
}
