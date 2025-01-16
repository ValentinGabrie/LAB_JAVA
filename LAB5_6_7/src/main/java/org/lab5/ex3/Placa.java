package org.lab5.ex3;
/*
* Clasa Placa
o descriere (String cu valori precum usa, capac, laterală, raft mobil, raft fix, etc)
o lungime în milimetri (întreg)
o laţime în milimetri (întreg)
o orientare– enumerare cu valorile posibile LUNGIME, LATIME, ORICARE
o canturi (vector de 4 elemente boolean). Fiecare piesă de pal care face parte dintr-un
corp de mobilă, are 4 muchii. O anumită valoare booleană indică prezența sau
absența cantului pe muchia corespunzătoare.
o nr_bucati (int)
*/
enum Orientare{
    LUNGIME, LATIME, ORICARE;
}

public class Placa {
    private String descriere;
    private int lungime;
    private int latime;
    private Orientare orientare;
    private boolean[] canturi;
    private int nr_bucati;

    public Placa(String descriere, int lungime, int latime, Orientare orientare, boolean[] canturi, int nr_bucati) {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.canturi = canturi;
        this.nr_bucati = nr_bucati;
    }

    public String getDescriere() {
        return descriere;
    }

    public int getLungime() {
        return lungime;
    }

    public int getLatime() {
        return latime;
    }

    public Orientare getOrientare() {
        return orientare;
    }

    public boolean[] getCanturi() {
        return canturi;
    }

    public int getNr_bucati() {
        return nr_bucati;
    }

    @Override
    public String toString() {
        return descriere + ", " + lungime + ", " + latime + ", " + orientare + ", " + canturi + ", " + nr_bucati;
    }

}
