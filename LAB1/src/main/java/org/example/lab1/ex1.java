package org.example.lab1;

import java.util.Scanner;

public class ex1 {

	public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.print("latime = ");
		int l = scanner.nextInt(); 
		
		System.out.print("Lungime = ");
		int L = scanner.nextInt(); 
		
		System.out.println("Perimetru =" + 2*(L+l));
		
		System.out.print("Aria =" + L*l);	
	}

}
