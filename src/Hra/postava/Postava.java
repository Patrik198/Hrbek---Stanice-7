public class Postava {

    private String jmeno;
    private String dialog;

    /**
     * Konstruktor
     * @param jmeno - jmeno postavy
     * @param dialog - dialog který postava vypráví
     */
    public Postava(String jmeno, String dialog) {
        this.jmeno = jmeno;
        this.dialog = dialog;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getDialog() {
        return dialog;
    }
}
