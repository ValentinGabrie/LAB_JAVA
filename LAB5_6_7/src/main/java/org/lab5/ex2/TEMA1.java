package org.lab5.ex2;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TEMA1 {
    static void scriere(List<PerecheNumere> lista) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("lab5/src/main/resources/PerecheNumere.json");
        mapper.writeValue(file, lista);
    }

    static List<PerecheNumere> citire() throws IOException {
        File file = new File("lab5/src/main/resources/PerecheNumere.json");
        ObjectMapper mapper = new ObjectMapper();
        List<PerecheNumere> lista = mapper.readValue(file, new TypeReference<List<PerecheNumere>>() {
        });
        return lista;
    }

    public static void main(String[] args) throws IOException {
        List<PerecheNumere> lista = new ArrayList<PerecheNumere>();
        lista.add(new PerecheNumere(2, 3));
        lista.add(new PerecheNumere(3, 4));
        lista.add(new PerecheNumere(4, 5));
        scriere(lista);
        for (PerecheNumere p : lista) {
            System.out.println(p);
        }
        for(PerecheNumere p : citire()) {
            System.out.println("Pereche: "+p);
            System.out.println("Consecutive Fibonacci: "+p.suntConsecutiveFibonacci());
            System.out.println("Cmmmc: "+p.cmmmc());
            System.out.println("Suma cifrelor egala: "+p.sumaCifrelorEgala());
            System.out.println("Acelasi nr cifre pare: "+p.aceeasiNumarCifrePare());
            System.out.println();
        }
    }


}
