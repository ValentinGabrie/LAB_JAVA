package org.example.lab3;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

class Produs
{
	private String denumire;
	private double pret;
	private double cantitate;
	private static double min = 99999;
	private static double max = 0;
	
	Produs(String denumire, double pret, double cantitate)
	{
		this.denumire = denumire;
		this.pret = pret;
		this.cantitate = cantitate;
		
		if (pret < min) min = pret;
		else if (pret > max) max = pret;
	}
	
	public String toString()
	{
		return (denumire + ";" + pret + ";" + cantitate);
	}
	
	public String getDenumire()
	{
		return denumire;
	}
	
	public double getPret()
	{
		return pret;
	}
	
	public double getCantitate()
	{
		return cantitate;
	}
	
	public static double getMin()
	{
		return min;
	}
	
	public static double getMax()
	{
		return max;
	}
	
}

public class MainApp1 {

	public static void main(String[] args) throws IOException{
		
		int k = 0;
		Produs[] p = new Produs[100];
		PrintStream flux_out = new PrintStream ("C:\\Users\\nicol\\Desktop\\JAVA\\LAB JAVA\\src\\Teme\\lab3\\out.txt");
		
		Scanner scanner = new Scanner(new File("C:\\Users\\nicol\\Desktop\\JAVA\\LAB JAVA\\src\\Teme\\lab3\\in.txt"));
		while(scanner.hasNext())
		{
			k++;
			
			String x = scanner.next();
			String[] s = x.split(";");
			String denumire = s[0];
			String pret_string = s[1];
			String cantitate_string = s[2];
			
			double pret = Double.parseDouble(pret_string);
			double cantitate = Double.parseDouble(cantitate_string);
			
			p[k] = new Produs(denumire, pret, cantitate);
		}
		int n = k;
		
		for(int i=1; i<=n; i++)
		{
			flux_out.print(String.format("%-12s %-12s %-12s",p[i].getDenumire(), p[i].getPret(), p[i].getCantitate())+"\n");
		}
		
		double min = Produs.getMin();
		double max = Produs.getMax();
		
		flux_out.print("\nPret minim: "+min+" -> Produs:  ");
		for(int i=1; i<=n; i++)
		{
			if(p[i].getPret() == min) flux_out.print(String.format("%-8s %-8s",p[i].getDenumire(), p[i].getCantitate())+"\n");
		}
		
		flux_out.print("Pret maxim: "+max+" -> Produs:  ");
		for(int i=1; i<=n; i++)
		{
			if(p[i].getPret() == max) flux_out.print(String.format("%-8s %-8s",p[i].getDenumire(), p[i].getCantitate())+"\n");
		}
		
		Scanner scan2 = new Scanner(System.in);
		System.out.print("Cantitate maxima cautata: ");
		double y = scan2.nextDouble();
		
		for(int i=1; i<=n; i++)
		{
			if(p[i].getCantitate() < y) System.out.print(p[i].getDenumire()+" "+p[i].getPret()+" "+p[i].getCantitate()+"\n");
		}
		
		flux_out.close();
		scanner.close();

	}

}
