public class PrikazNapoveda implements Prikaz{
    @Override
    public String getNazev() {
        return "napoveda";
    }

    @Override
    public String getPopis() {
        return "Zobrazí seznam všech dostupných příkazů";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        System.out.println("\n=== DOSTUPNÉ PŘÍKAZY ===");
        System.out.println("jdi <směr>       - Přesun do jiné místnosti");
        System.out.println("vezmi <předmět>  - Vezme předmět do inventáře");
        System.out.println("poloz <předmět>  - Položí předmět z inventáře");
        System.out.println("batoh            - Zobrazí obsah inventáře");
        System.out.println("prozkoumej       - Prozkoumá předmět");
        System.out.println("pouzij <předmět> - Použije předmět");
        System.out.println("mluv             - Promluví s postavou UI");
        System.out.println("pomoc <příkaz>   - Detailní nápověda k příkazu");
        System.out.println("konec            - Ukončí hru");
        System.out.println("\nPro detailní info použij: pomoc <název příkazu>");
    }
}
