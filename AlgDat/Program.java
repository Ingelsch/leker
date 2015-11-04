package AlgDat;

import hjelpeklasser.Tabell;

import java.util.Arrays;

public class Program
{
	public static void main(String ... args)      // hovedprogram
	{
		Integer[] a = Tabell.randPermInteger(10);
		System.out.println(Arrays.toString(a));
		// En mulig utskrift: [7, 1, 8, 2, 10, 3, 4, 6, 5, 9]

		Tabell.innsettingssortering(a);
		System.out.println(Arrays.toString(a));
		// Utskrift: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


	} // Main2



	  
	/*
	  	___
	  	Oppgave1_1_7_1
	  	Oppgave1_1_7_1.makstest(); // Gir ingen feil
	  	___
	  	  int[] a = {7,5,9,2,10,4,1,8,6,3};     // en usortert heltallstabell
		  Tabell.utvalgssortering(a);           // stigende sortering
		  Tabell.snu(a);                        // tabellen snus
		  Tabell.skriv(a);                      // 10 9 8 7 6 5 4 3 2 1
	  	___
	  	  int[] a = {5, 9, 6, 10, 2, 7, 3, 8, 4, 1};          // en heltallstabell
  System.out.println(Arrays.toString(a));             // skriver ut

  int antInv = Tabell.inversjoner(a);                 // inversjoner
  System.out.println("Inversjoner: " + antInv);       // skriver ut

  int antOmb = Tabell.boble(a, a.length);             // ombyttinger
  antInv = Tabell.inversjoner(a);                     // inversjoner

  System.out.println(Arrays.toString(a));             // skriver ut
  System.out.print("Ombyttinger: " + antOmb + "  ");  // ombyttinger
  System.out.println("Inversjoner: " + antInv);       // inversjoner

  // Utskrift:
  // [5, 9, 6, 10, 2, 7, 3, 8, 4, 1]
  // Inversjoner: 29
  // [5, 6, 9, 2, 7, 3, 8, 4, 1, 10]
  // Ombyttinger: 7  Inversjoner: 22
	  	___
	  	int n = 100000;         // tabellstørrelse
  int antall = 1000;      // antall gjentagelser

  long tid = 0;           // for tidsmåling

  int a[] = randPerm(n);  // en permutasjon av 1, . .  n

  tid = System.currentTimeMillis();  // leser av klokken
  for (int i = 0; i < antall; i++) nestMaks1(a);
  tid = System.currentTimeMillis() - tid;  // medgått tid
  System.out.println("Maks1-metoden: " + tid + " millisek");

  tid = System.currentTimeMillis();  // leser av klokken
  for (int i = 0; i < antall; i++) nestMaks2(a);
  tid = System.currentTimeMillis() - tid;  // medgått tid
  System.out.println("Maks2-metoden: " + tid + " millisek");

  tid = System.currentTimeMillis();  // leser av klokken
  for (int i = 0; i < antall; i++) nestMaks3(a);
  tid = System.currentTimeMillis() - tid;  // medgått tid
  System.out.println("Maks3-metoden: " + tid + " millisek");
	  	___

	  		public static void Main2(String ... args)      // hovedprogram
	{
		int[] a = Tabell.randPerm(20);              // en tilfeldig tabell
		for (int k : a) System.out.print(k + " ");  // skriver ut a

		int m = Tabell.maks(a);   // finner posisjonen til største verdi

		System.out.println("\nStørste verdi ligger på plass " + m);

	} // Main2
	  	___

				//Oppgaver til Avsnitt 1.2.3, Oppgave 2 - andre del

		//Her er det satt opp flere mulige kall. For å få feilmeldinger av samme type som nedenfor, må ett og ett av kallene utføres:

		/*public static void Main2(String[] args) throws IOException
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
		  System.out.print("Størst(" + a[m] + ") har posisjon " + m);
		  System.out.println(", nest størst(" + a[nm] + ") har posisjon " + nm);

		  // Eksempel på en utskrift:

		  // 12 16 15 6 10 8 9 2 14 19 5 18 20 13 3 7 11 1 4 17
		  // Størst(20) har posisjon 12, nest størst(19) har posisjon 9

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