package AlgDat;

import hjelpeklasser.*;

import java.io.IOException;

public class Program
{
	public static void main(String[] args) throws IOException
	{
		int[] a = Tabell.randPerm(10);
		int[] c = null;

		//Tabell.maks(a,-1,10);
		//Tabell.maks(a,0,11);
		//Tabell.maks(a,10,0);
		//Tabell.maks(a,0,0);
		Tabell.maks(c,0,0);
	}



	  
	/*
	  	___

	  		public static void main(String ... args)      // hovedprogram
	{
		int[] a = Tabell.randPerm(20);              // en tilfeldig tabell
		for (int k : a) System.out.print(k + " ");  // skriver ut a

		int m = Tabell.maks(a);   // finner posisjonen til største verdi

		System.out.println("\nStørste verdi ligger på plass " + m);

	} // main
	  	___

				//Oppgave 2 - andre del

		//Her er det satt opp flere mulige kall. For å få feilmeldinger av samme type som nedenfor, må ett og ett av kallene utføres:

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