public class PrikazInventar implements Prikaz{

    Printovanitextu p = new Printovanitextu();

    @Override
    public String getNazev() {
        return "inventar";
    }

    @Override
    public String getPopis() {
        return "";
    }

    public String getBarva(){
        return Barvicky.YELLOW;
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        p.println(hra.getInv().zobrazObsah());
    }
}
