package org.lab5.ex3;
/*Informații privind necesarul de pal al mai multor piese de mobilier sunt păstrate într-un
fișierul mobilier.json. Palul este un material lemnos sub formă de placă obținut prin presarea de
lemn în combinație cu diferiți lianți. Pentru fiecare piesă de mobilier se memorează numele
piesei (birou, dulap, etajeră, etc) și informații privind plăcile de pal care o compun. Fiecare
placă de pal are o formă dreptunghiulară. Pentru fiecare placă de pal se memorează o descriere
a plăcii, lungimea şi lățimea exprimate in milimetri, orientarea fibrei, canturile pe care le are şi
numărul de bucăți din placa respectivă care intră în compoziția mobilierului. Sa se realizeze un
program care folosind un meniu interactiv realizează următoarele operații:
a) Citește datele despre piesele de mobilier din fișierul mobilier.json într-o listă de piese
de mobilier (List<Mobilier>) și le afișează
b) Afişează elementele de mobilier din colecție şi plăcile care le compun
c) Afişează caracteristicile plăcilor care compun o anumită piesă de mobilier
d) Afișează estimativ numărul colilor de pal necesare pentru realizarea unui anumit corp
de mobile știind că o coală de pal are dimensiunea 2800 x 2070 mm (pentru simplitate
se va ţine cont doar de arie, nu şi de posibilitatea de a realiza tăieturile)
*/

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TEMA2 {
    public static List<Mobilier> citire() {
        try {
            File file = new File("C:\\Users\\nicol\\Desktop\\JAVA\\Lab_JAVA_2\\src\\main\\resources\\mobileier.json");
            ObjectMapper mapper = new ObjectMapper();
            List<Mobilier> mobilier = mapper.readValue(file, new TypeReference<List<Mobilier>>() {
            });
            return mobilier;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void scriere(List<Mobilier> lista) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("C:\\Users\\nicol\\Desktop\\JAVA\\Lab_JAVA_2\\src\\main\\resources\\mobileier.json");
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Mobilier> mobilier = List.of();

        // mobilier.add(new Mobilier("Birou", List.of(new Placa("Placa1", 100, 200, Orientare.LUNGIME, new boolean[]{true, true, true, true}, 2))));

        // scriere(mobilier);

        do {
            //meniu
           /*a) Citește datele despre piesele de mobilier din fișierul mobilier.json într-o listă de piese
de mobilier (List<Mobilier>) și le afișează
b) Afişează elementele de mobilier din colecție şi plăcile care le compun
c) Afişează caracteristicile plăcilor care compun o anumită piesă de mobilier
d) Afișează estimativ numărul colilor de pal necesare pentru realizarea unui anumit corp
de mobile știind că o coală de pal are dimensiunea 2800 x 2070 mm (pentru simplitate
se va ţine cont doar de arie, nu şi de posibilitatea de a realiza tăieturile)
            */
            do {
                System.out.println("1. Citire date piese de mobilier");
                System.out.println("2. Afișare elemente de mobilier");
                System.out.println("3. Afișare caracteristicile plăcilor care compun o anumită piesă de mobilier");
                System.out.println("4. Afișare estimativ numărul colilor de pal necesare pentru realizarea unui anumit corp de mobile");
                System.out.println("5. Exit");

                System.out.println("Introduceti optiunea: ");
                int optiune = scanner.nextInt();
                scanner.nextLine();
                switch (optiune) {
                    case 1:
                        mobilier = citire();
                        System.out.println(mobilier);
                        break;
                    case 2:
                        for (Mobilier m : mobilier) {
                            System.out.println(m);
                        }
                        break;
                    case 3:
                        System.out.println("Introduceti numele piesei de mobilier: ");
                        String nume = scanner.nextLine();
                        for (Mobilier m : mobilier) {
                            if (m.getNume().equals(nume)) {
                                System.out.println(m.getPlaci());
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Introduceti numele piesei de mobilier: ");
                        String nume2 = scanner.nextLine();
                        for (Mobilier m : mobilier) {
                            if (m.getNume().equals(nume2)) {
                                int nrColi = 0;
                                for (Placa p : m.getPlaci()) {
                                    int ariePlaca = p.getLungime() * p.getLatime();
                                    int arieCol = 2800 * 2070;
                                    nrColi += ariePlaca / arieCol * p.getNr_bucati();
                                }
                                System.out.println("Numarul de coli necesare este: " + nrColi);
                            }
                        }
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Optiune invalida");
                        break;
                }


            }while (true);


        } while (true);


    }
}