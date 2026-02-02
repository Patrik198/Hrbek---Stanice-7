public class PrikazJdi implements Prikaz{

    @Override
    public String getNazev() {
        return "jdi";
    }

    @Override
    public String getPopis() {
        return "Umožňuje přesun mezi místnostmi. Použití: jdi [směr] (např. jdi sever).";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        if (prikaz.length < 2){
            System.out.println("Kam mám jít? (Musíš zadat směr)");
            return;
        }

        String smer = prikaz[1];
        Mistnost aktualni = hra.getAktualnimistnost();
        String vychod = aktualni.getSmerVychodu(smer);

        if (vychod == null){
            System.out.println("Nemas kam jit!");
        }else {

            boolean muzesprojit = false;

            for (Predmet p : hra.getInv().getInventar()) {
                if (p.getNazev().contains("magneticky klic")) {
                    muzesprojit = true;
                    break;
                }
            }
                    if (muzesprojit) {
                        Mistnost nova = hra.getSvet().najdiMistnost(vychod);
                        hra.setAktualnimistnost(nova);
                        System.out.println("Přešel jsi do: " + nova.getId());
                        System.out.println(nova.getPopis());
                    } else {
                        System.out.println("Nemůžeš projít – nemáš magnetický klíč.");
                    }
                }
            }
        }
