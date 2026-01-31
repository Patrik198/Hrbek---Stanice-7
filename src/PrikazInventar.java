public class PrikazInventar implements Prikaz{
    @Override
    public String getNazev() {
        return "inventar";
    }

    @Override
    public String getPopis() {
        return "";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        System.out.println(hra.getInv().zobrazObsah());
    }
}
