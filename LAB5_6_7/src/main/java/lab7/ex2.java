package lab7;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/*
Să se realizeze un program care implementează următoarele cerințe:
1) Creează o colecție de tip Set<InstrumentMuzical> în care utilizând polimorfismul se
introduc 3 chitări şi 3 seturi de tobe.
2) Salvează colecția Set<InstrumentMuzical> în fișierul instrumente.json. Pentru ca în
fișierul json să fie salvat şi tipul obiectelor (care este necesar la citirea datelor din json
şi crearea obiectelor corespunzătoare) se configurează în funcția de scriere obiectul de
tip ObjectMapper în felul următor:
 mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
3) Încarcă datele din fişierul instrumente.json în program, într-o colecție de tip
Set<InstrumentMuzical>, care va fi utilizată pentru rezolvarea cerințelor următoare.
Pentru ca operația de încărcare a datelor din json în program să reușească în contextual
în care se utilizează tipuri polimorfe, este necesar să se completeze deasupra clasei
abstracte adnotația de mai jos, care va ajuta la realizarea legăturii dintre tipurile
abstracte si implementările concrete.
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)

4) Să se afișeze implementarea utilizată pentru interfața Set de către ObjectMapper în
urma citirii
5) Să se verifice dacă colecția Set permite sau nu duplicate, prin încercarea de a adăuga
un instrument care are aceleași caracteristici cu unul deja introdus. În urma verificării
se va afişa un mesaj corespunzător. Să se scrie codul care face ca duplicatele să nu fie
permise în colecţia Set.
6) Să se șteargă instrumentele din Set al căror preț este mai mare de 3000 de RON. Se va
utiliza metoda removeIf().
7) Să se afișeze toate datele chitărilor, utilizând Stream API şi operatorul instanceof
8) Să se afișeze toate datele tobelor, utilizând Stream API şi metoda getClass()
9) Să se afișeze datele chitării care are cele mai multe corzi, utilizând Stream API,
expresii Lambda, referințe la metode şi clasa Optional.
10) Să se afișeze datele tobelor acustice, ordonat după numărul de tobe din set utilizând
Stream API, referințe la metode, expresii Lambda, etc */
public class ex2 {
    //rezolva subpunctul 2 serializare si deserializare

    public static void scriere(Set<InstrumentMuzical> instrumente) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("C:\\Users\\nicol\\Desktop\\JAVA\\Lab_JAVA_2\\src\\main\\resources\\instrumente.json");
        try {
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, instrumente);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Set<InstrumentMuzical> citire() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("C:\\Users\\nicol\\Desktop\\JAVA\\Lab_JAVA_2\\src\\main\\resources\\instrumente.json");
        Set<InstrumentMuzical> instrumente = new HashSet<>();
        try {
            instrumente = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(Set.class, InstrumentMuzical.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instrumente;
    }




    public static void main(String[] args) throws IOException {

        Set<InstrumentMuzical> instrumente = new HashSet<>();
        instrumente.add(new Chitara("Fender", 2000, TipChitara.ELECTRICA, 6));
        instrumente.add(new Chitara("Yamaha", 1500, TipChitara.ACUSTICA, 6));
        instrumente.add(new Chitara("Yamaha", 1000, TipChitara.CLASICA, 6));
        instrumente.add(new SetTobe("Yamaha", 3000, TipTobe.ELECTRONICE, 5, 3));
        instrumente.add(new SetTobe("Yamaha", 2000, TipTobe.ACUSTICE, 5, 3));
        instrumente.add(new SetTobe("Yamaha", 1000, TipTobe.ELECTRONICE, 5, 3));

       // scriere(instrumente);

        Set<InstrumentMuzical> instrumenteCitite = citire();
        System.out.println("\n");
        System.out.println(instrumenteCitite);
        System.out.println("\n");

        //4) Să se afișeze implementarea utilizată pentru interfața Set de către ObjectMapper în
        //urma citirii

        System.out.println(instrumenteCitite.getClass());
        System.out.println("\n");

        //5) Să se verifice dacă colecția Set permite sau nu duplicate, prin încercarea de a adăuga
        //un instrument care are aceleași caracteristici cu unul deja introdus. În urma verificării
        //se va afişa un mesaj corespunzător. Să se scrie codul care face ca duplicatele să nu fie
        //permise în colecţia Set.

        Chitara chitara = new Chitara("Fender", 2000, TipChitara.ELECTRICA, 6);
        if(instrumenteCitite.add(chitara)){
            System.out.println("Chitara adaugata cu succes");
        }else{
            System.out.println("Chitara nu a fost adaugata");
        }
        System.out.println("\n");

        //6) Să se șteargă instrumentele din Set al căror preț este mai mare de 3000 de RON. Se va
        //utiliza metoda removeIf().
        instrumenteCitite.removeIf(instrument -> instrument.getPret() > 2000);
        System.out.println(instrumenteCitite);
        System.out.println("\n");



        //7) Să se afișeze toate datele chitărilor, utilizând Stream API şi operatorul instanceof
        instrumenteCitite.stream()
                .filter(instrument -> instrument instanceof Chitara)
                .forEach(System.out::println);

        System.out.println("\n");

        //8) Să se afișeze toate datele tobelor, utilizând Stream API şi metoda getClass()
        instrumenteCitite.stream()
                .filter(instrument -> instrument.getClass().equals(SetTobe.class))
                .forEach(System.out::println);

        System.out.println("\n");

        //9) Să se afișeze datele chitării care are cele mai multe corzi, utilizând Stream API,
        //expresii Lambda, referințe la metode şi clasa Optional.

        InstrumentMuzical chitaraMaxCorzi = instrumenteCitite.stream()
                .filter(instrument -> instrument instanceof Chitara)
                .max((i1, i2) -> ((Chitara) i1).getNr_corzi() - ((Chitara) i2).getNr_corzi())
                .orElse(null);
        System.out.println(chitaraMaxCorzi);
        System.out.println("\n");

        //10) Să se afișeze datele tobelor acustice, ordonat după numărul de tobe din set utilizând
        //Stream API, referințe la metode, expresii Lambda, etc

        instrumenteCitite.stream()
                .filter(instrument -> instrument instanceof SetTobe)
                .filter(instrument -> ((SetTobe) instrument).getTip_tobe().equals(TipTobe.ACUSTICE))
                .sorted((i1, i2) -> ((SetTobe) i1).getNr_tobe() - ((SetTobe) i2).getNr_tobe())
                .forEach(System.out::println);


    }
}
