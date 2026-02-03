public class PrikazJdi implements Prikaz{

    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "jdi";
    }

    @Override
    public String getPopis() {
        return "Umožňuje přesun mezi místnostmi. Použití: jdi [směr] (např. jdi sever).";
    }

    public String getBarva(){
        return Barvicky.GREEN;
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        if (prikaz.length < 2){
            p.println("Kam mám jít? (Musíš zadat směr)");
            return;
        }

        String smer = prikaz[1];
        Mistnost aktualni = hra.getAktualnimistnost();
        String vychod = aktualni.getSmerVychodu(smer);

        if (vychod == null){
            p.println("Nemas kam jit!");
        }

        if (hra.isKryokomoraOdemknuta()) {
                Mistnost nova = hra.getSvet().najdiMistnost(vychod);
                hra.setAktualnimistnost(nova);
                hra.setKryokomoraOdemknuta(true);
                p.println("Přešel jsi do: " + nova.getId());
                p.println(nova.getPopis());
        }else{
            hra.setKryokomoraOdemknuta(false);
            p.println("Přístup zamítnut, nemáš magnetický klíč");
        }
    }
}
