package org.example.lab2;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;


public class ex4 {

    public static class persoana {
        String nume;
        String cnp;

        public persoana(String nume, String cnp) {
            this.nume = nume;
            this.cnp = cnp;
        }

        public String getNume() {
            return nume;
        }

        public String getCnp() {
            return cnp;
        }
        //getVarsta() care va calcula şi va returna vârsta persoanei extrăgând data nașterii din CNP şi
        //citind din sistem data curentă. Se va utiliza clasa LocalDate.

        public int getVarsta(){
            int an =2000+ Integer.parseInt(cnp.substring(1, 3));
            int luna = Integer.parseInt(cnp.substring(3, 5));
            int zi = Integer.parseInt(cnp.substring(5, 7));
            //Se va utiliza clasa LocalDate.
            LocalDate dataNasterii = LocalDate.of(an, luna, zi);
            LocalDate dataCurenta = LocalDate.now();
            return Period.between(dataNasterii, dataCurenta).getYears();


        }
    }

    //functie validare CNP
    public static boolean validareCNP(String cnp) {
        if (cnp.length() != 13) {
            return false;
        }
        for (int i = 0; i < cnp.length(); i++) {
            if (!Character.isDigit(cnp.charAt(i))) {
                return false;
            }
        }
        if (cnp.charAt(0) != '1' && cnp.charAt(0) != '2' && cnp.charAt(0) != '5' && cnp.charAt(0) != '6') {
            return false;
        }
        int[] cifre = new int[13];
        for (int i = 0; i < cnp.length(); i++) {
            cifre[i] = cnp.charAt(i) - '0';
        }
        int s = cifre[0] * 2 + cifre[1] * 7 + cifre[2] * 9 + cifre[3] + cifre[4] * 4 + cifre[5] * 6 + cifre[6] * 3 + cifre[7] * 5 + cifre[8] * 8 + cifre[9] * 2 + cifre[10] * 7 + cifre[11] * 9;
        int cifraControl = s % 11;
        if (cifraControl == 10) {
            cifraControl = 1;
        }
        return cifraControl == cifre[12];
    }

    public static void main(String[] args) {
        System.out.println("Viata e grele");
        ArrayList<persoana> persoanaList = new ArrayList<>();
        StringBuilder cnp;
        StringBuilder nume;

        Scanner scanner = new Scanner(System.in);
        // BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicol\\Desktop\\JAVA\\LAB_2\\src\\ex4\\cnp.txt"));

        System.out.println("Introduceti numarul de oamnei: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Introduceti numele: ");
            nume = new StringBuilder(scanner.next());
            do {
                System.out.println("Introduceti CNP-ul: ");
                cnp = new StringBuilder(scanner.next());
                if (!validareCNP(String.valueOf(cnp))) {
                    System.out.println("CNP-ul introdus este gresit");
                }
            } while (!validareCNP(String.valueOf(cnp)));
            persoanaList.add(new persoana(String.valueOf(nume), String.valueOf(cnp)));
        }

        for (persoana p : persoanaList) {
            System.out.println(p.getNume() + " " + p.getCnp()+ " " + p.getVarsta());
        }
    }

}
