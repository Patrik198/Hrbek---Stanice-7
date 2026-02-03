public class PrikazProzkoumej implements Prikaz{

    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "prozkoumej";
    }

    @Override
    public String getPopis() {
        return "prozkoumá item";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {

        if (prikaz.length < 2) {
            p.println("Co mám prozkoumat?");
            return;
        }

        // Spojí všechna slova za "prozkoumej" do jednoho názvu
        String nazevPredmetu = "";
        for (int i = 1; i < prikaz.length; i++) {
            nazevPredmetu += prikaz[i];
            if (i < prikaz.length - 1) {
                nazevPredmetu += " ";
            }
        }

        Mistnost aktualni = hra.getAktualnimistnost();

        // Najde předmět v místnosti
        Predmet predmet = aktualni.najdiPredmet(nazevPredmetu);

        if (predmet == null) {
            p.println("Tento předmět se zde nenachází.");
            return;
        }

        // Vypíše popis předmětu
        p.println(predmet.getPopis());
    }
}
