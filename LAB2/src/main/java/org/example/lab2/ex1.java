package org.example.lab2;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


    public class ex1 {

        public static void main(String[] args) throws IOException {
            ArrayList<String> judete= new ArrayList<>();
            BufferedReader br= new BufferedReader(new FileReader("judete_in.txt"));
            String linie;

            while ((linie=br.readLine())!=null){
                judete.add(linie);
            }

            String[] judeteArray = judete.toArray(new String[0]);
            Arrays.sort(judeteArray);

            for (String judet : judeteArray) {
                System.out.println(judet);
            }

            System.out.println("Introduceti judetul cautat: ");
            Scanner scanner= new Scanner(System.in);
            String judetCautat= scanner.nextLine();
            int index= Arrays.binarySearch(judeteArray,judetCautat);
            System.out.println("Judetul cautat se afla pe pozitia: "+index);





        }

    }
