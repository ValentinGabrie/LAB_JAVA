package org.example.lab1;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ex2 {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("in.txt"));

		int S = 0, m = 100000, M = 0;
		float Ma = 0, k = 0;

		while (scanner.hasNext()) {
			int x = scanner.nextInt();

			S = S + x;

			k++;

			Ma = S / k;

			if (m > x)

				m = x;

			if (M < x)

				M = x;

		}
		String nume_fis = "out.txt";
		PrintStream flux_out = new PrintStream(nume_fis);
		flux_out.println("Suma = " + S);
		flux_out.println("Media aritmetica =  " + Ma);
		flux_out.println("Minimul este = " + m);
		flux_out.println("Maximul este = " + M);

	}

}
