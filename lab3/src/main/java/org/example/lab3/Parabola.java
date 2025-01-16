package org.example.lab3;

class Parabola {
	private double a,b,c;
	public Parabola(double a, double b,double c){
		this.a=a;
		this.b=b;
		this.c=c;
	}
	public Parabola(Parabola p) {
		this.a=p.a;
		this.b=p.b;
		this.c=p.c;
	}
	public void afisare() {
		System.out.println("f(x) = " + (int)a + "x^2" + " + " + (int)b + "x " + " + " +(int)c);
	}
	public double[] PunctVarf() {
		double[] coordonate = new double[2];
		coordonate[0] = -b/(2*a);
		coordonate[1] = ((-b*b)+4*a*c)/(4*a);
		System.out.println("Coordonatele varfului sunt: (x,y)=" + "(" + coordonate[0] + "," + coordonate[1] + ")");
		return coordonate;	
	}
	public static void Mijloc(Parabola x, Parabola y) {
		Parabola p1 = new Parabola(x);
		Parabola p2 = new Parabola(y);
		
		double[] parabola1 = new double[2];
		double[] parabola2 = new double[2];
		
		parabola1 = p1.PunctVarf();
		parabola2 = p2.PunctVarf();
		
		double abscisa = (parabola1[0]+parabola2[0])/2;
		double ordonata = (parabola1[1]+parabola2[1])/2;
		
		System.out.print("(x,y)="+"("+abscisa+","+ordonata+")");
	}
}