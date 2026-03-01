package Hra.prikaz;

import Hra.Hra;

public interface Prikaz {

     String getNazev();
     String getPopis();

    /**
     * Hlavní metoda pro vykonání příkazu
     * @param prikaz - příkaz (vezmi, jdi)
     * @param hra - předmět, místnost, UI
     */
     void proved(String[] prikaz, Hra hra);

     String getBarva();
}
