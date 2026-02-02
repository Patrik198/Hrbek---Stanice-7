public class PrikazKonec implements Prikaz{
    @Override
    public String getNazev() {
        return "konec";
    }

    @Override
    public String getPopis() {
        return "Ukončí hru";
    }

    @Override
    public void proved(String[] prikaz, Hra hra) {
        hra.setBezi(false);
    }
}
