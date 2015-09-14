package hjelpeklasser;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell //Samleklasse for tabellmetoder
{
	private Tabell(){}
	// Metoden bytt(int[] a, int i, int j) - Programkode 1.1.8 d)
	public static void bytt(int[] a, int i, int j)
	{
		int temp = a[i]; a[i] = a[j]; a[j] = temp;
	}
	// Metoden randPerm(int n) - Programkode 1.1.8 e)
	public static int[] randPerm(int n)  // en effektiv versjon
	{
		Random r = new Random();         // en randomgenerator
		int[] a = new int[n];            // en tabell med plass til n tall

		Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

		for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
		{
			int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k
			bytt(a,k,i);                   // bytter om
		}

		return a;                        // permutasjonen returneres
	}
	// Metoden randPerm(int[] a) - Programkode 1.1.8 f))
	public static void randPerm(int[] a)  // stokker om a
	{
		Random r = new Random();     // en randomgenerator

		for (int k = a.length - 1; k > 0; k--)
		{
			int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
			bytt(a,k,i);
		}
	}
	// Metoden maks(int[] a, int fra, int til) - Programkode 1.2.1 b)
	public static int maks(int[] a, int fra, int til)
	{
		if (fra < 0 || til > a.length || fra >= til)
		{
			throw new IllegalArgumentException("Illegalt intervall!");
		}

		int m = fra;              // indeks til største verdi i a[fra:til>
		int maksverdi = a[fra];   // største verdi i a[fra:til>

		for (int i = fra + 1; i < til; i++)
		{
			if (a[i] > maksverdi)
			{
				m = i;                // indeks til største verdi oppdateres
				maksverdi = a[m];     // største verdi oppdateres
			}
		}

		return m;  // posisjonen til største verdi i a[fra:til>
	}
	// Metoden maks(int[] a) - Programkode 1.2.1 c)
	public static int maks(int[] a)  // bruker hele tabellen
	{
		return maks(a,0,a.length);     // kaller metoden over
	}
	// min-metodene - se Oppgave 1 i Avsnitt 1.2.1

	/*Lag to min-metoder (metoder som finner posisjonen til den minste verdien)
	tilsvarende de to maks-metodene i Programkode 1.2.1 b) og 1.2.1 c).
	Legg dem i samleklassen Tabell. Se Avsnitt 1.2.2.*/


	public static int min(int[] a, int fra, int til)
	{
			if (fra < 0 || til > a.length || fra >= til)
			  throw new IllegalArgumentException("Illegalt intervall!");

			int m = fra;             // indeks til minste verdi i a[fra:til>
			int minverdi = a[fra];   // minste verdi i a[fra:til>

			for (int i = fra + 1; i < til; i++) if (a[i] < minverdi)
			{
			  m = i;               // indeks til minste verdi oppdateres
			  minverdi = a[m];     // minste verdi oppdateres
			}

			return m;  // posisjonen til minste verdi i a[fra:til>
	}

	public static int min(int[] a)  // bruker hele tabellen
	{
		return min(a,0,a.length);     // kaller metoden over
	}

	//Avsnitt 1.2.2_3 Lag metoden public static void bytt(char[] c, int i, int j).
	// Den skal bytte om innholdet i posisjon i og j  i char-tabellen c. Legg metoden i samleklassen Tabell.
	/*public static void bytt(char[] c, int i, int j)
	{
		char temp = a[i]; a[i] = a[j]; a[j] = temp;
	}*/

	//Avsnitt 1.2.2_4 Lag metoden public static void skriv(int[] a, int fra, int til). Den skal skrive ut tallene i intervallet a[fra:til> til konsollet -
	// alle på én linje og et mellomrom mellom hvert tall. Ikke mellomrom og ikke linjeskift etter siste verdi. Lag så metoden public static void skriv(int[] a).
	// Den skal skrive ut hele tabellen - alle på én linje, en blank mellom hvert tall. Ikke mellomrom og ikke linjeskift etter siste verdi. Legg begge metodene i samleklassen Tabell.

	public static void skriv(int[] a, int fra, int til)
	{
		fratilKontroll(a.length, fra, til);
		if (til - fra > 0) System.out.print(a[fra]);
		for (int i = fra + 1; i < til; i++) System.out.print(" " + a[i]);
	}

	public static void skriv(int[] a)
	{
		skriv(a, 0, a.length);
	}

	//Avsnitt 1.2.2_5 Lag to skrivln-metoder. De skal ha samme signatur og fungere på samme måte som de to skriv-metodene i Oppgave 4 ,
	// men utskriften skal avsluttes med et linjeskift. Legg begge metodene i samleklassen Tabell.

	/*public static void skrivln(int[] a, int fra, int til)
	{
		skriv(a,fra,til);
		System.out.println();
	}*/

		/*public static void skrivln(int[] a)
		{
			skrivln(a, 0, a.length);
		}*/

	//Avsnitt 1.2.2_6 Som i Oppgave 4 og 5, men med en tabell c av typen char[].
	public static void skriv(char[] a, int fra, int til)
	{
		fratilKontroll(a.length, fra, til);
		if (til - fra > 0) System.out.print(a[fra]);
		for (int i = fra + 1; i < til; i++) System.out.print(" " + a[i]);
	}

	public static void skriv(char[] a)
	{
		skriv(a, 0, a.length);
	}

	/*public static void skrivln(char[] a, int fra, int til)
	{
		skriv(a, fra, til);
		System.out.println();
	}*/

	public static void skrivln(char[] a)
	{
		skrivln(a, 0, a.length);
	}
	//Avsnitt 1.2.2_7 Lag metoden public static int[] naturligeTall(int n). Den skal returnere en heltallstabell som inneholder tallene 1, 2, . . . , n.
	// Hvis n er mindre enn 1 skal det kastes et unntak. Lag også den mer generelle metoden public static int[] heleTall(int fra, int til).
	// Den skal returnere en heltallstabell som inneholder tallene fra og med fra og til, men ikke med, tallet til.
	// For eksempel skal kallet heleTall(1,6) gi tabellen {1, 2, 3, 4, 5}. Hvis fra er større enn til kastes et unntak. Hvis fra er lik til returneres en tom tabell. Legg metodene i samleklassen Tabell.

	public static int[] naturligeTall(int n)
	{
		if (n < 1) throw new
				IllegalArgumentException("n(" + n + ") er mindre enn 1!");

		int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = i + 1;
		return a;
	}

	public static int[] heleTall(int fra, int til)
	{
		if (fra > til) throw new
				IllegalArgumentException("fra(" + fra + ") > til(" + til + ")");

		int[] a = new int[til - fra];
		for (int i = fra; i < til; i++) a[i-fra] = i;
		return a;
	}
	//LOppg 1 legg metodene Programkode 1.2.3 a) og 1.2.3 d), inn i samleklassen Tabell.
	// Programkode 1.2.3 a)
	public static void fratilKontroll(int tablengde, int fra, int til)
	{
		if (fra < 0)                                  // fra er negativ
			throw new ArrayIndexOutOfBoundsException
					("fra(" + fra + ") er negativ!");

		if (til > tablengde)                          // til er utenfor tabellen
			throw new ArrayIndexOutOfBoundsException
					("til(" + til + ") > tablengde(" + tablengde + ")");

		if (fra > til)                                // fra er større enn til
			throw new IllegalArgumentException
					("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
	}
	//Flg. metode (som skal legges i class Tabell?) sjekker om et lukket tabellinetrvall er lovlig:
	// Programkode 1.2.3 d)
	public static void vhKontroll(int tablengde, int v, int h)
	{
		if (v < 0)
			throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");

		if (h >= tablengde)
			throw new ArrayIndexOutOfBoundsException
					("h(" + h + ") >= tablengde(" + tablengde + ")");

		if (v > h + 1)
			throw new IllegalArgumentException
					("v = " + v + ", h = " + h);
	}
	//Oppgave 2 - føste del Gjør om maks-metoden i Programkode 1.2.1 b), som du nå skal ha lagt inn i class Tabell,
	// slik at parameterverditesten blir erstattet med Programkode 1.2.3 b) og c).
	// Lag så et testprogram der maks-metoden inngår (bruk main i class Program), men med parameterverdier som du vet
	// vil føre til at unntak kastes. Velg verdier slik at du får frem alle de mulige feilmeldingstypene.

	/*public static int maks(int[] a, int fra, int til)
	{
		fratilKontroll(a.length,fra,til);

		if (fra == til) throw new NoSuchElementException
				("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

		int m = fra;             // indeks til største verdi i a[fra:til>
		int maksverdi = a[fra];  // største verdi i a[fra:til>

		for (int i = fra + 1; i < til; i++) if (a[i] > maksverdi)
		{
			m = i;               // indeks til største verdi oppdateres
			maksverdi = a[m];    // største verdi oppdateres
		}

		return m;  // posisjonen til største verdi i a[fra:til>
	}*/

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
	//Oppgave 3*/

	/*public static int maks(int[] a, int fra, int til)
	{
		if (a == null) throw new NullPointerException
				("parametertabellen a er null!");

		fratilKontroll(a.length, fra, til);

		if (fra == til) throw new NoSuchElementException
				("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

		int m = fra;             // indeks til største verdi i a[fra:til>
		int maksverdi = a[fra];  // største verdi i a[fra:til>

		for (int i = fra + 1; i < til; i++) if (a[i] > maksverdi)
		{
			m = i;               // indeks til største verdi oppdateres
			maksverdi = a[m];    // største verdi oppdateres
		}

		return m;  // posisjonen til største verdi i a[fra:til>
	}*/
	//Oppgave 4


	/*public static void skrivln(int[] a, int fra, int til)
	{
		fratilKontroll(a.length,fra,til);

		for (int i = fra; i < til; i++) System.out.print(a[i] + " ");
		System.out.println();
	}*/

	/*public static void skriv(char[] c, int fra, int til)
	{
		fratilKontroll(c.length,fra,til);

		for (int i = fra; i < til; i++) System.out.print(c[i] + " ");
	}*/

	public static void skrivln(char[] c, int fra, int til)
	{
		fratilKontroll(c.length,fra,til);

		for (int i = fra; i < til; i++) System.out.print(c[i] + " ");
		System.out.println();
	}
	//Oppgave 5

	public static void snu(int[] a, int v, int h)
	{
		vhKontroll(a.length,v,h);
		while (v < h) bytt(a,v++,h--);
	}

	public static void snu(int[] a)
	{
		int v = 0, h = a.length-1;
		while (v < h) bytt(a,v++,h--);
	}

	/*public static void main(String[] args)
	{
		int[] a = {2,3,5,7,9,6,10,2,1,4};
		int min = Tabell.min(a);
		int max = Tabell.maks(a, 3, 6);
		System.out.println("Her er posisjonen til minimumsverdien: ");
		System.out.println(max);
	}*/
}
