### **STANICE 7**

#### **Hra**

Stanice 7 je textová hra napsaná v Javě
Hráč se probouzí v kryokomoře na poškozené vesmírné stanici a jeho cílem je:
odemknout přístup ze startovní místnosti
obnovit energii stanice
opravit terminál na můstku
deaktivovat záchranný systém pomocí přístupového kódu
Teprve po splnění všech podmínek je hra úspěšně dokončena.

#### **Mapa**

Herní svět se načítá z JSON souboru (resources/mapa.json) pomocí knihovny Gson.


##### **Lokace:**

- Kryokomora

- Strojovna

- Laboratoř

- Archiv

- Můstek

- Hlavní chodba

- Sklad

- Osetrovna


#### **Ovládání**


##### **Příkazy:**

- jdi <směr>	Pohyb mezi místnostmi

- vezmi <předmět>	Sebere předmět

- poloz <předmět>	Položí předmět

- inventar	Zobrazí obsah inventáře

- prozkoumej <předmět>	Zobrazí detail předmětu

- pouzij <předmět>	Použije předmět

- mluv <postava>	Interakce s postavou

- napoveda	Seznam příkazů

- pomoc <příkaz>	Detailní nápověda příkazu

- konec	Ukončí hru


#### **Inventář**


- Maximální kapacita: 3

- Každý předmět má váhu

- Některé předměty nejsou přenosné

- Inventář sleduje aktuální nosnost


#### **Podmínky pro dokončení hry**


1) Použít magnetický klíč

2) Obnovit energii ve Strojovně

3) Opravit terminál na Můstku

4) Zadat správný 6místný kód

5) Deaktivovat záchranný systém


#### **Struktura projektu**


Main -> Spouští hru

Hra	Hlavní -> herní logika

Svet -> Načítá mapu z JSON

Mistnost -> Reprezentace lokace

Predmet	-> Herní předměty

Postava	-> Herní postavy

Inventar -> Správa inventáře

Prikaz -> Rozhraní příkazů

Prikaz Jdi, Vezmi, ... -> Implementace konkrétních příkazů

Barvicky -> ANSI barvy pro výstup

Printovanitextu	-> Oddělení výpisu textu


#### **Spuštění programu**


Ujisti se, že máš nainstalovanou JDK 17+

Přidej knihovnu Gson

Ujisti se, že mapa.json je ve složce resources

Spusť třídu: Main.java



