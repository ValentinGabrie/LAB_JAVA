package org.example.lab1;

import java.util.Random;

public class ex5 {

	public static void main(String[] args) {
		Random rand = new Random();
		  
	    int n = rand.nextInt(21);
	    
	    int n0,n1,n2;
	 
	        n0 = 0;
	        n1 = 1;
	        n2 = 1;
	        while (n2 <n)
	        {
	            n0 = n1;
	            n1 = n2;
	            n2 = n0 + n1;
	        }
	        if (n2==n || n == 0 || n == 1 )
	        	{
	        	System.out.println(n +"  apartine sirului lui Fobonacci");
	        	}
	        else
	        	{
	        	System.out.println(n +"  nu apartine sirului lui Fobonacci");
	        	}

	}

}
