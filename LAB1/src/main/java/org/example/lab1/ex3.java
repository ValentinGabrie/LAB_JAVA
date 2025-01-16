package org.example.lab1;

import java.util.Scanner;

public class ex3 {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in); 
		
		System.out.print("n = ");
		
		int n = scanner.nextInt(); 
		int i, a = 0;
		for(i = 2; i <= n/2; i++)
		{
			if(n % i == 0)
			{
				System.out.println(i);
				a++;
			}
		}
		if(a == 0)
			System.out.println("Numarul este prim");

	}

}
