public class PrikazVezmi implements Prikaz{
    @Override
    public String getNazev() {
        return "vezmi";
    }

    @Override
    public String getPopis() {
        return "";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        if (prikaz.length < 2){
            System.out.println("Nemůžeš nic vzít!");
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

        Predmet predmet = null;
        for (Predmet p : aktualni.getPredmety()){
            if (p.getNazev().equals(nazevPredmetu)){
                predmet = p;
                break;
            }
        }

        if (predmet == null){
            System.out.println("není tu žádný předmět");
            return;
        }

        if (!predmet.isPrenosny()){
            System.out.println("Nemůžeš vzít tento předmět!");
            return;
        }

        if (hra.getInv().pridejPredmet(predmet)){
            aktualni.odeberPredmet(predmet);
            System.out.println("sebral jsi: " + predmet.getNazev());
        }else{
            System.out.println("Inventář je plný!");
        }


    }
}
