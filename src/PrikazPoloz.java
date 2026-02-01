public class PrikazPoloz implements Prikaz{
    @Override
    public String getNazev() {
        return "poloz";
    }

    @Override
    public String getPopis() {
        return "";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {

        if (prikaz.length < 2){
            System.out.println("Nemůžeš nic položit");
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
            System.out.println("Nemůžeš nic položit");
            return;
        }

        if (hra.getInv().odeberPredmet(predmet)){
            aktualni.pridejPredmet(predmet);
            System.out.println("Položil jsi: " + predmet.getNazev());
        }else{
            System.out.println("Inventář je prázdný");
        }
    }
}
