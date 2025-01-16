package org.example.lab2;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
* 3. Să se insereze într-o anumită poziție a unui șir de caractere, un alt șir. Datele vor fi
preluate de la tastatură sau din fișier. Să se șteargă o porțiune a unui șir de caractere care
începe dintr-o anumită poziție și are un anumit număr de caractere. Se recomandă utilizarea
clasei StringBuilder
* */
public class ex3 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("src\\Teme\\lab2\\sir.txt"));
        StringBuilder sb = new StringBuilder(br.readLine());
        System.out.println(sb);
        br.close();
        //meniu
        System.out.println("1. Inserare");
        System.out.println("2. Stergere");
        System.out.println("3. Iesire");
        int optiune = scanner.nextInt();
        switch (optiune) {
            case 1:
                System.out.println("Introduceti sirul de inserat");
                String sirInserat = scanner.next();

                System.out.println("Introduceti pozitia");
                int pozitie = scanner.nextInt();
                sb.insert(pozitie, sirInserat);
                System.out.println(sb);
                break;
            case 2:
                System.out.println("Introduceti pozitia de inceput");
                int pozitieInceput =scanner.nextInt();
                System.out.println("Introduceti numarul de caractere");
                int numarCaractere = scanner.nextInt();
                sb.delete(pozitieInceput, pozitieInceput + numarCaractere);
                System.out.println(sb);
                break;
            case 3:
                System.out.println("Iesire");
                break;
            default:
                System.out.println("Optiune invalida");
        }

        scanner.close();
    }
}
