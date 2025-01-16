package org.example.Lab4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

enum Stare
{
	achizitionat,
	expus,
	vandut
}

enum Mod
{
	color,
	alb_negru
}

enum Format
{
	A3,
	A4
}

enum Sistem
{
	windows,
	linux
}

@SuppressWarnings("serial")
class Echipament implements Serializable
{
	private String denumire;
	private int nr_inv;
	private double pret;
	private String zona_mag;
	private Stare stare;

	public Echipament(String denumire,int nr_inv,double pret,String zona_mag,Stare stare)
	{
		super();
		this.denumire=denumire;
		this.nr_inv=nr_inv;
		this.pret=pret;
		this.zona_mag=zona_mag;
		this.stare=stare;
	}

	public String getDenumire() {
		return denumire;
	}
	public int getNr_inv() {
		return nr_inv;
	}
	public double getPret() {
		return pret;
	}
	public String getZona_mag() {
		return zona_mag;
	}
	public Stare getSt() {
		return stare;
	}
	public void setSt(Stare stare) {
		this.stare = stare;
	}
	@Override
	public String toString() {
		return "Denumire: " + denumire + "; Numar inventar: " + nr_inv + "; Pret: " + pret + "; Zona magazin: "
				+ zona_mag + "; Stare: " + stare + "; Tip: ";
	}
}

@SuppressWarnings("serial")
class Imprimanta extends Echipament implements Serializable
{
	private int ppm;
	private String rezolutie;
	private int p_car;
	private Mod mod;

	public Imprimanta(String denumire,int nr_inv,double pret,String zona_mag,Stare stare,int ppm,String rezolutie,int p_car,Mod mod)
	{
		super(denumire,nr_inv,pret,zona_mag,stare);
		this.ppm=ppm;
		this.rezolutie=rezolutie;
		this.p_car=p_car;
		this.mod=mod;
	}

	public int getPpm() {
		return ppm;
	}
	public String getRezolutie() {
		return rezolutie;
	}
	public int getP_car() {
		return p_car;
	}
	public Mod getMod_scriere() {
		return mod;
	}
	public void setMod_scriere(Mod mod) {
		this.mod = mod;
	}
	@Override
	public String toString() {
		return super.toString()+"Imprimanta; Pagini pe minut: " + ppm + "; Rezolutie: " + rezolutie + "; Pagini/cartus: " + p_car + "; Mod scriere: "
				+ mod;
	}
}

@SuppressWarnings("serial")
class Copiator extends Echipament implements Serializable
{
	private int ppm;
	private int p_ton;
	private Format format;

	public Copiator(String denumire,int nr_inv,double pret,String zona_mag,Stare stare,int ppm,int p_ton,Format format)
	{
		super(denumire,nr_inv,pret,zona_mag,stare);
		this.ppm=ppm;
		this.p_ton=p_ton;
		this.format=format;
	}

	public int getPpm() {
		return ppm;
	}
	public int getP_ton() {
		return p_ton;
	}
	public Format getFormat_copiere() {
		return format;
	}
	@Override
	public String toString() {
		return super.toString()+"Copiator; pagini pe min: " + ppm + "; Pagini/toner " + p_ton + " Format copiere: " + format;
	}
	public void setFormat_copiere(Format format) {
		this.format = format;
	}
}

@SuppressWarnings("serial")
class Sistem_de_calcul extends Echipament implements Serializable
{
	private String tip_mon;
	private double vit_proc;
	private int c_hdd;
	private Sistem sistem;

	public Sistem_de_calcul(String denumire,int nr_inv,double pret,String zona_mag,Stare stare,String tip_mon,double vit_proc,int c_hdd,Sistem sistem)
	{
		super(denumire,nr_inv,pret,zona_mag,stare);
		this.tip_mon=tip_mon;
		this.vit_proc=vit_proc;
		this.c_hdd=c_hdd;
		this.sistem=sistem;
	}

	public String getTip_mon() {
		return tip_mon;
	}
	public double getVit_proc() {
		return vit_proc;
	}
	public int getC_hdd() {
		return c_hdd;
	}
	public Sistem getSist_operare() {
		return sistem;
	}
	@Override
	public String toString() {
		return super.toString()+"Sistem de calcul; tip monitor: " + tip_mon + "; Viteza procesor: " + vit_proc + ", Capacitate HDD: " + c_hdd + ", Sistem de operare: "
				+ sistem;
	}
	public void setSist_operare(Sistem sistem) {
		this.sistem = sistem;
	}
}

public class ex1 {

	static void scrie(Object o, String fis) {
		try {
		FileOutputStream f = new FileOutputStream(fis);
		ObjectOutputStream oos = new ObjectOutputStream(f);
		oos.writeObject(o);
		oos.close();
		f.close();
		}
		catch (IOException e) {
		e.printStackTrace();
		}
	}
	
	static Object citeste(String fis) {
		try {
		FileInputStream f = new FileInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(f);
		Object o=ois.readObject();
		ois.close();
		f.close();
		return o;
		}
		catch (IOException | ClassNotFoundException e) {
		e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] Args) throws IOException{
		BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\nicol\\Desktop\\JAVA\\LAB JAVA\\src\\Teme\\Lab4\\echipamente.txt"));
		BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
		String s=reader.readLine();
		if(s==null) {
			System.out.println("Fisiserul nu a putut fi deschis!");
			System.exit(0);
		}
		else {
			Echipament[] echip=new Echipament[20];
			int n=0;
			
			while(s!=null) {
				String[] info=s.split(";");
				if(info[5].equals("imprimanta")==true) {
					
					if(info[9].equals("alb negru"))
					{
						echip[n++]=new Imprimanta(info[0],Integer.parseInt(info[1]),Integer.parseInt(info[2]),info[3],Stare.valueOf(info[4]),Integer.parseInt(info[6]),info[7],Integer.parseInt(info[8]),Mod.alb_negru);
					}
					else {
						if(info[9].equals("color"))
						{
							echip[n++]=new Imprimanta(info[0],Integer.parseInt(info[1]),Integer.parseInt(info[2]),info[3],Stare.valueOf(info[4]),Integer.parseInt(info[6]),info[7],Integer.parseInt(info[8]),Mod.color);
						}
					}
				}
				
				if(info[5].equals("copiator")==true) {
					echip[n++]=new Copiator(info[0],Integer.parseInt(info[1]),Double.parseDouble(info[2]),info[3],Stare.valueOf(info[4]),Integer.parseInt(info[6]),Integer.parseInt(info[7]), Format.valueOf(info[8]));
				}
				if(info[5].equals("sistem de calcul")==true) {
					echip[n++]=new Sistem_de_calcul(info[0],Integer.parseInt(info[1]),Double.parseDouble(info[2]),info[3],Stare.valueOf(info[4]),info[6],Double.parseDouble(info[7]),Integer.parseInt(info[8]), Sistem.valueOf(info[9]));
				}
				s=reader.readLine();	
				
			}
			int opt;
			String nume;
			boolean ok;
			do{
				System.out.println("1. Afisarea imprimantelor");
				System.out.println("2. Afisarea copiatoarelor");
				System.out.println("3. Afisarea sistemelor de calcul");
				System.out.println("4. Modificarea starii in care se afla un echipament");
				System.out.println("5. Setarea unui anumit mod de scriere pentru o imprimanta");
				System.out.println("6. Setarea unui format de copiere pentru copiatoare");
				System.out.println("7. Instalarea unui anumit sistem de operare pe un sistem de calcul");
				System.out.println("8. Afisarea echipamentelor vandute");
				System.out.println("9. Serializare");
				System.out.println("10. Deserializare");
				System.out.println("0. Iesire");	
				System.out.println("Optiunea dvs. este:");
				s=read.readLine();
				opt=Integer.parseInt(s);
				switch(opt){
				case 1:
					System.out.println("Afisarea imprimantelor: ");
					for (int i=0;i<n;i++)
						if(echip[i] instanceof Imprimanta)
							System.out.println(echip[i]);
					break;
				case 2:	
					System.out.println("Afisarea copiatoarelor: ");
					for (int i=0;i<n;i++)
						if(echip[i] instanceof Copiator)
							System.out.println(echip[i]);
					break;
				case 3:
					System.out.println("Afisarea sistemelor de calcul: ");
					for (int i=0;i<n;i++)
						if(echip[i] instanceof Sistem_de_calcul)
							System.out.println(echip[i]);
					break;
				case 4:
					Stare st_noua;
					System.out.print("Introduceti numele echipamentului pentru care sa se modifice starea: ");
					nume=read.readLine();
					System.out.print("Introduceti starea(achizitionat/vandut/expus): ");
					s=read.readLine();
					st_noua=Stare.valueOf(s);
					
					ok=false;
					for(int i=0;i<n;i++) {
						if(nume.compareTo(echip[i].getDenumire())==0) {
							echip[i].setSt(st_noua);
							System.out.println(echip[i]);
							ok=true;
						}
					}
					if(ok==false)
						System.out.println("Nu exista nicio inregistrare cu numele '" + nume + "'.");
					break;
				case 5:
					System.out.print("Introduceti numele imprimantei pentru setarea modului de scriere: ");
					nume=read.readLine();
					System.out.print("Introduceti modul de scriere (color/alb_negru): ");
					s=read.readLine();
					Mod mod_nou=Mod.valueOf(s);
					
					ok=false;
					for(int i=0;i<n;i++) {
						if(nume.compareTo(echip[i].getDenumire())==0 && (echip[i] instanceof Imprimanta)) {
							((Imprimanta) echip[i]).setMod_scriere(mod_nou);
							System.out.println(echip[i]);
							ok=true;
						}
					}
					if(ok==false)
						System.out.println("Nu exista nicio imprimanta cu numele '" + nume + "'.");
					break;
				case 6:
					System.out.print("Introduceti numele copiatorului pentru modificarea formatului: ");
					nume=read.readLine();
					System.out.print("Introduceti formatul(A3/A4): ");
					s=read.readLine();
					Format form_nou=Format.valueOf(s);
					
					ok=false;
					for(int i=0;i<n;i++) {
						if(nume.compareTo(echip[i].getDenumire())==0 && (echip[i] instanceof Copiator)) {
							((Copiator) echip[i]).setFormat_copiere(form_nou);
							ok=true;
							System.out.println(echip[i]);
						}
					}
					if(ok==false)
						System.out.println("Nu exista niciun copiator cu numele '" + nume + "'.");
					break;
				case 7:
					System.out.print("Introduceti numele sistemului de calcul:");
					nume=read.readLine();
					System.out.print("Introduceti sistemul de operare(windows/linux): ");
					s=read.readLine();
					Sistem sist=Sistem.valueOf(s);
					
					ok=false;
					for(int i=0;i<n;i++) {
						if(nume.compareTo(echip[i].getDenumire())==0 && (echip[i] instanceof Sistem_de_calcul)) {
							((Sistem_de_calcul) echip[i]).setSist_operare(sist);
							System.out.println(echip[i]);
							ok=true;
						}
					}
					if(ok==false)
						System.out.println("Nu exista niciun sistem de calcul cu numele '" + nume + "'.");
					break;
				case 8:
					System.out.println("Vandute:");
					for (int i=0;i<n;i++)
						if(echip[i].getSt().compareTo(Stare.valueOf("vandut"))==0)
							System.out.println(echip[i]);
					break;
				case 9:
					scrie(echip,"C:\\Users\\nicol\\Desktop\\JAVA\\LAB JAVA\\src\\Teme\\Lab4\\echip.bin");
					break;
				case 10:
					Echipament []q;
					q=(Echipament[])citeste("C:\\Users\\nicol\\Desktop\\JAVA\\LAB JAVA\\src\\Teme\\Lab4\\echip.bin");
					for(Echipament e:q)
						if(e!=null)
							System.out.println(e);
					break;
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println("Optiunea dvs. este gresita!");
					break;
				}
			}while(opt!=0);
			reader.close();
		}
	}
			
}
