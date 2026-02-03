public class PrikazPoloz implements Prikaz{

    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "poloz";
    }

    @Override
    public String getPopis() {
        return "";
    }

    public String getBarva(){
        return Barvicky.BLUE;
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {

        if (prikaz.length < 2){
            p.println("Nemůžeš nic položit");
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

        Predmet predmet = hra.getInv().najdiPredmet(nazevPredmetu);

        if (predmet == null){
            p.println("Nemůžeš nic položit");
            return;
        }

        if (hra.getInv().odeberPredmet(predmet)){
            aktualni.pridejPredmet(predmet);
            p.println("Položil jsi: " + predmet.getNazev());
        }else{
            p.println("Inventář je prázdný");
        }
    }
}
