package lab6;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.list;
import static java.util.Collections.reverseOrder;


public class ex1 {
    static void scriere(List<Angajat> lista) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        File file = new File("C:\\Users\\nicol\\Desktop\\JAVA\\Lab_JAVA_2\\src\\main\\resources\\angajati.json");
        mapper.writeValue(file, lista);
    }

    static List<Angajat> citire() throws IOException {
        File file = new File("C:\\Users\\nicol\\Desktop\\JAVA\\Lab_JAVA_2\\src\\main\\resources\\angajati.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        List<Angajat> lista = mapper.readValue(file, new TypeReference<List<Angajat>>() {
        });
        return lista;
    }

    public static void main(String[] args) throws IOException {

        //primul lucru de facut

        //List<Angajat> lista = new ArrayList<>();
        //lista.add(new Angajat("Virgil","director",LocalDate.of(2013,12,10),8271));
        // scriere(lista);

        // dupa comentam ce e sus si citim
        List<Angajat> lista = citire();

        //subpunctul 1
        System.out.println("Afisarea angajatilor: ");
        lista.forEach(System.out::println);
        System.out.println("\n");

        //subpunctul 2
        System.out.println("Afisarea angajatilor care au peste 2500 RON: \n");
        lista.stream()
                .filter(a -> a.getSalariul() > 2500)
                .forEach(System.out::println);

        //subpunctul 3
        int anul_curent = LocalDate.now().getYear();
        int anul_trecut = anul_curent - 1;
        System.out.println("\nLista angajaților din luna aprilie, a anului trecut, care au funcție de\n" +
                "conducere (postul conține unul din cuvintele sef sau director): ");
        List<Angajat> aprilie = lista.stream()
                .filter(a -> a.getPostul().equalsIgnoreCase("sef") || a.getPostul().equalsIgnoreCase("director"))
                .filter(a -> a.getData_angajarii().getMonth() == Month.APRIL && a.getData_angajarii().getYear() == anul_trecut)
                .collect(Collectors.toList());
        aprilie.forEach(System.out::println);

        //subpunctul 4
        System.out.println("\nAfișarea angajaților care nu au funcție de conducere (postul lor nu conține cuvintele\n" +
                "director sau șef), în ordine descrescătoare a salariilor: \n");
        lista.stream()
                .filter(a -> !(a.getPostul().equalsIgnoreCase("sef") || a.getPostul().equalsIgnoreCase("director")))
                .sorted(Comparator.comparing(Angajat::getSalariul).reversed())
                .forEach(System.out::println);

        //subpunctul 5
        System.out.println("\nLista de String-uri care conține numele angajaților\n" +
                "scrise cu majuscule: \n");
        List<String> num = lista.stream()
                .map(a -> a.getNume().toUpperCase())
                .collect(Collectors.toList());
        num.forEach(System.out::println);

        //subpunctul 6
        System.out.println("\nAfișarea salariilor mai mici de 3000 de RON (doar salariile, fără alte informații): \n");
        lista.stream()
                .filter(a -> a.getSalariul() < 3000)
                .map(a -> a.getSalariul())
                .forEach(System.out::println);

        //subpunctul 7
        System.out.println("\nAfisarea primului angajat din firma: \n");
        lista.stream()
                .min(Comparator.comparing(Angajat::getData_angajarii)) // Comparați după data angajării
                // Verificăm dacă există un rezultat și îl afișăm
                .ifPresentOrElse(
                        System.out::println,                           // Afișăm primul angajat
                        () -> System.out.println("Nu există angajați.") // Mesaj dacă nu există angajați
                );

        //subpunctul 8
        System.out.println("\nAfișarea de statistici referitoare la salariul angajaților: ");
        DoubleSummaryStatistics statisticiSalarii = lista.stream()
                .collect(Collectors.summarizingDouble(Angajat::getSalariul));  // Calculăm statisticile

        System.out.println("Salariul mediu: " + statisticiSalarii.getAverage());
        System.out.println("Salariul minim: " + statisticiSalarii.getMin());
        System.out.println("Salariul maxim: " + statisticiSalarii.getMax());


        //subpunctul 9
        lista.stream()
                .filter(a -> a.getNume().contains("Ion"))   // Căutăm angajați care au "Ion" în nume
                .findAny()                                // Găsim orice angajat care corespunde
                .ifPresentOrElse(
                        a->System.out.println("\nFirma are cel puțin un Ion angajat\n"),  // Mesaj dacă există un "Ion"
                        () -> System.out.println("\nFirma nu are nici un Ion angajat\n")    // Mesaj dacă nu există niciun "Ion"
                );


        //subpunctul 10
        long numarAngajatiVara = lista.stream()
                .filter(a -> a.getData_angajarii().getYear() == anul_trecut &&
                        a.getData_angajarii().getMonthValue() >= 6 &&
                        a.getData_angajarii().getMonthValue() <= 8)
                .count();

        System.out.println("Numărul de angajați care s-au angajat în vara anului trecut: " + numarAngajatiVara);


    }
}

