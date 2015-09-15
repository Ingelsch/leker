package AlgDat;

import hjelpeklasser.*;

import java.io.IOException;

public class Program
{
	public static void main(String ... args)      // hovedprogram
	{
		int[] a = Tabell.randPerm(20);              // en tilfeldig tabell
		for (int k : a) System.out.print(k + " ");  // skriver ut a

		int m = Tabell.maks(a);   // finner posisjonen til st�rste verdi

		System.out.println("\nSt�rste verdi ligger p� plass " + m);

	} // main



	  
	/*
	  	___

	  		public static void main(String ... args)      // hovedprogram
	{
		int[] a = Tabell.randPerm(20);              // en tilfeldig tabell
		for (int k : a) System.out.print(k + " ");  // skriver ut a

		int m = Tabell.maks(a);   // finner posisjonen til st�rste verdi

		System.out.println("\nSt�rste verdi ligger p� plass " + m);

	} // main
	  	___

				//Oppgaver til Avsnitt 1.2.3, Oppgave 2 - andre del

		//Her er det satt opp flere mulige kall. For � f� feilmeldinger av samme type som nedenfor, m� ett og ett av kallene utf�res:

		/*public static void main(String[] args) throws IOException
		{
			int[] a = Tabell.randPerm(10);
			int[] c = null;

			Tabell.maks(a,-1,10);
			//Tabell.maks(a,0,11);
			//Tabell.maks(a,10,0);
			//Tabell.maks(a,0,0);
			//Tabell.maks(c,0,0);
		}

		// Feilmeldinger:

		// ArrayIndexOutOfBoundsException: fra(-1) er negativ!
		// ArrayIndexOutOfBoundsException: til(11) > tablengde(10)
		// IllegalArgumentException: fra(10) > til(0) - illegalt intervall!
		// NoSuchElementException: fra(0) = til(0) - tomt tabellintervall!
		// NullPointerException
		___


			Flg. kodebit viser hvordan metoden i Programkode 1.2.4 a) kan brukes:

		   int[] a = Tabell.randPerm(20); // tilfeldig permutasjon av 1 . . 20
		  int[] b = Tabell.nestMaks(a);  // metoden returnerer en tabell

		  int m = b[0], nm = b[1];       // m for maks, nm for nestmaks

		  Tabell.skrivln(a);        // se Oppgave 5 i Avsnitt 1.2.2
		  System.out.print("St�rst(" + a[m] + ") har posisjon " + m);
		  System.out.println(", nest st�rst(" + a[nm] + ") har posisjon " + nm);

		  // Eksempel p� en utskrift:

		  // 12 16 15 6 10 8 9 2 14 19 5 18 20 13 3 7 11 1 4 17
		  // St�rst(20) har posisjon 12, nest st�rst(19) har posisjon 9

		___



	  	int[] a = {8, 4, 17, 10, 6, 20, 1, 11, 20, 20, 15, 3, 18, 9, 2, 7, 19};
		for (int b : a) System.out.print(b + " ");

		int min = Tabell.min(a);
		System.out.println("\nTabellens minimumsverdi er: \n" + min + ".\nverdien er: " + a[min] + ".\n");

		int max = Tabell.maks(a, 2, 5);
		System.out.println("Tabellintervallets hoyeste verdi har posisjon: \n" + max + ".\nverdien er: " + a[max]+ ".\n");
	  	___
	  	int[] a = {8,3,5,7,9,1,6,10,2,4};
	    int i = Oppgave1_1_2_2.min(a);
	    System.out.println(("Minimumsverdien ligger pa indeks " + i + ", og har verdi " + a[i]));
  		___
  		
  		int[] a =	{8, 4, 17, 10, 6, 20, 1, 11, 20, 20, 15, 3, 18, 9, 2, 7, 19};
  		int i = Eksempel1_1_2.maks(a);
  		System.out.println("Maksverdien ligger pa indeks " + i + ", og har verdi " + a[i]);
  		___

	    int[] a = {8,3,5,7,9,6,10,2,1,4};
    	System.out.println("Her er posisjonene til minste og storste verdi: \n[min, maks]");
    	System.out.println(java.util.Arrays.toString(minmax(a)));
    	___
      
      int[] a =	{8, 4, 17, 10, 6, 20, 1, 11, 20, 20, 15, 3, 18, 9, 2, 7, 19};
	  	int i = Oppgave1_1_2_2.min(a);
	  	System.out.println("Minverdien ligger pa indeks " + i + ", og har verdi " + a[i]);
	  	
	  	___
	  	
	  	int[] a =	{8, 4, 17, 10, 6, 20, 1, 11, 20, 20, 15, 3, 18, 9, 2, 7, 19};
			int i = Eksempel1_1_3rev.maks(a);
			System.out.println("Maksverdien ligger pa indeks " + i + ", og har verdi " + a[i]);
  		___
  
  		int n = 5;
  		System.out.println("Fakultetet av " + n + " blir: \n" + Oppgave1_1_3_6.fak(n));
      		
	}*/

}// class Program