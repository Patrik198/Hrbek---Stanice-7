public class PrikazVezmi implements Prikaz{

    private Inventar inv;
    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "vezmi";
    }

    @Override
    public String getPopis() {
        return "";
    }

    public String getBarva(){
        return Barvicky.GREEN;
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {

        if (prikaz.length < 2){
            p.println("Nemůžeš nic vzít!");
            return;
        }

        String nazevPredmetu = "";
        for (int i = 1; i < prikaz.length; i++){
            nazevPredmetu += prikaz[i];
            if (i < prikaz.length - 1){
                nazevPredmetu += " ";
            }
        }

        Mistnost aktualni = hra.getAktualnimistnost();

        // Hledání předmětu v aktuální místnosti
        Predmet predmet = null;
        for (Predmet p : aktualni.getPredmety()){
            if (p.getNazev().equals(nazevPredmetu)){
                predmet = p;
                break;
            }
        }

        if (predmet == null){
            p.println("není tu žádný takový předmět");
            return;
        }

        if (!predmet.isPrenosny()){
            p.println("Nemůžeš vzít tento předmět!");
            return;
        }

        if (hra.getInv().pridejPredmet(predmet)){
            aktualni.odeberPredmet(predmet);
            p.println("sebral jsi: " + predmet.getNazev());
        }else{
            p.println("Nemáš kapacitu v inventari!");
        }


    }
}
