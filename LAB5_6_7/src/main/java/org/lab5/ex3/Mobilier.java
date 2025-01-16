package org.lab5.ex3;

import java.util.List;

/*- Clasa Mobilier, cu variabilele membre
o nume (String)
o lista plăcilor care o compun List<Placa> placi;
*/
public class Mobilier {

    private String nume;
    private List<Placa> placi;

    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }

    public String getNume() {
        return nume;
    }

    public List<Placa> getPlaci() {
        return placi;
    }

    @Override
    public String toString() {
        return nume + ", " + placi;
    }
}
