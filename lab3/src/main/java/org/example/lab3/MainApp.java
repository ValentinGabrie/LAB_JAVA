package org.example.lab3;

public class MainApp {

	public static void main(String[] args) throws Exception {
		Parabola p1 = new Parabola(3, 6, 9);
		Parabola p2 = new Parabola(1, -2, 4);

		p1.afisare();
		p2.afisare();

		Parabola.Mijloc(p1, p2);

	}

}