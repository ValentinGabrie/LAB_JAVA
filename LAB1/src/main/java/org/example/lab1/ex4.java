package org.example.lab1;

import java.util.Random;

public class ex4 {

	public static void main(String[] args) {
		Random rand = new Random();
		  
	    int m = rand.nextInt(31);
	    int n = rand.nextInt(31);
	    
	    while(m != n)
	    {
	        if(m > n)
	        	
	            m= m-n;
	        
	        else
	        	
	            n= n-m;
	    }
	    System.out.println(m);

	}

}
