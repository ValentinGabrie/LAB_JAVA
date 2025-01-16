package org.lab5.ex2;


import java.util.ArrayList;
import java.util.List;

public class PerecheNumere {
    private int a, b;

    public PerecheNumere(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "PerecheNumere{" +
                "nr1=" + a +
                ", nr2=" + b +
                '}';
    }

    public PerecheNumere() {
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public boolean suntConsecutiveFibonacci() {
        List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(0);
        fibonacci.add(1);
        int a = 0, b = 1, c = a + b;
        while (c <= Math.max(a, this.b)) {
            fibonacci.add(c);
            a = b;
            b = c;
            c = a + b;
        }
        return fibonacci.contains(a) && fibonacci.contains(this.b) &&
                Math.abs(fibonacci.indexOf(a) - fibonacci.indexOf(this.b)) == 1;
    }
    public int cmmmc() {
        return (a * b) / cmmdc(a, b);
    }

    private int cmmdc(int a, int b) {
        if (b == 0) return a;
        return cmmdc(b, a % b);
    }
    private int numarCifrePare(int numar) {
        int nr = 0;
        while (numar != 0) {
            if ((numar % 10) % 2 == 0) nr++;
            numar /= 10;
        }
        return nr;
    }
    public boolean aceeasiNumarCifrePare() {
        return numarCifrePare(a) == numarCifrePare(b);
    }

    private int sumaCifrelor(int numar) {
        int suma = 0;
        while (numar != 0) {
            suma += numar % 10;
            numar /= 10;
        }
        return suma;
    }
    public boolean sumaCifrelorEgala() {
        return sumaCifrelor(a) == sumaCifrelor(b);
    }
}
