package org.example.lab2;

import java.io.*;
import java.util.ArrayList;

/*
*2. Fișierul cantec_in.txt conține versurile unui cântec la alegere. Să se scrie un
program care creează fișierul cantec_out.txt, care conține versurile cântecului original dar în
plus în dreptul fiecărui rând sunt afișate numărul de cuvinte de pe rând şi numărul de vocale
de pe fiecare rând. În dreptul rândurilor care se încheie cu o grupare de litere aleasă se va
pune o steluță (*). Rândurile pentru care un număr generat aleator este mai mic decât 0.1 vor
fi scrise cu majuscule (se vor genera aleator numere între 0 şi 1).
Se va defini o clasă Vers, care are o variabilă membră privată un șir de caractere
reprezentând versul și se va dezvolta câte un operator pentru fiecare cerință de mai sus (o
metodă care returnează numărul de cuvinte, o metodă care returnează numărul de vocale, etc).
Se va crea un vector de obiecte de tip Vers care va conține informația preluată din fișierul de
intrare.

*
* */
public class ex2 {

    public record Vers(String vers) {

        public int numarCuvinte() {
            return vers.split(" ").length;
        }

        public int numarVocale() {
            return vers.replaceAll("[^aeiouAEIOU]", "").length();
        }

        public String upperCase() {
            return vers.toUpperCase();
        }
    }

    public static void main(String[] args) {
        System.out.println("Muzca");
        ArrayList<Vers> versuri = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicol\\Desktop\\JAVA\\LAB_2\\src\\ex2\\cantec_in.txt")))
        {
            String linie;
            while ((linie = br.readLine()) != null) {
                // System.out.println(linie);

                versuri.add(new Vers(linie));
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("cantec_out.txt"));


            for(Vers v: versuri){

                double random = Math.random();
                if(random < 0.1){
                    bw.write(v.upperCase());
                }
                else {
                    bw.write(v.vers());
                }
                bw.write("| Cuvinte " + v.numarCuvinte() + " Vocale " + v.numarVocale());
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("Eroare la citirea fisierului");

        }


    }
}