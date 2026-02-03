public class PrikazMluv implements Prikaz{

    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "mluv";
    }

    @Override
    public String getPopis() {
        return "Promluv s postavou v místnosti";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        if (prikaz.length < 2){
            p.println("S kým mám mluvit?");
            return;
        }

        String jmenoPostavy = prikaz[1];
        Mistnost aktualni = hra.getAktualnimistnost();

        Postava po = aktualni.najdiPostavu(jmenoPostavy);

        if (po == null){
            p.println("Tady nikdo takový není.");
            return;
        }

        p.println(po.getJmeno() + ": " + po.getDialog());
    }
}
