public class PrikazNapoveda implements Prikaz{

    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "napoveda";
    }

    @Override
    public String getPopis() {
        return "Zobrazí seznam všech dostupných příkazů";
    }

    public String getBarva(){
        return Barvicky.BLUE;
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        p.println("\n=== DOSTUPNÉ PŘÍKAZY ===");
        p.println("jdi <směr>       - Přesun do jiné místnosti");
        p.println("vezmi <předmět>  - Vezme předmět do inventáře");
        p.println("poloz <předmět>  - Položí předmět z inventáře");
        p.println("batoh            - Zobrazí obsah inventáře");
        p.println("prozkoumej       - Prozkoumá předmět");
        p.println("pouzij <předmět> - Použije předmět");
        p.println("mluv             - Promluví s postavou UI");
        p.println("pomoc <příkaz>   - Detailní nápověda k příkazu");
        p.println("konec            - Ukončí hru");
        p.println("\nPro detailní info použij: pomoc <název příkazu>");
    }
}
