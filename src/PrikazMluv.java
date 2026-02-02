public class PrikazMluv implements Prikaz{
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
            System.out.println("S kým mám mluvit?");
            return;
        }

        String jmenoPostavy = prikaz[1];
        Mistnost aktualni = hra.getAktualnimistnost();

        Postava p = aktualni.najdiPostavu(jmenoPostavy);

        if (p == null){
            System.out.println("Tady nikdo takový není.");
            return;
        }

        System.out.println(p.getJmeno() + ": " + p.getDialog());
    }
}
