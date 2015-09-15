package hjelpeklasser;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell //Samleklasse for tabellmetoder
{
	public static void bytt(int[] a, int i, int j)
	//Programkode 1.1.8 d)
	{
		int temp = a[i]; a[i] = a[j]; a[j] = temp;
	}

	public static int antallMaks(int[] a)    // teller opp i a
	{
		int antall = 0;            // antall tall
		int maksverdi = a[0];

		for (int i = 1; i < a.length; i++)    // går gjennom tabellen a
		{
			if (a[i] > maksverdi)    // a[i] er større enn største foran
			{
				antall++;              // har funnet et nytt tall
				maksverdi = a[i];      // oppdaterer maksverdi
			}
		}

		return antall;    // de som er større enn det største foran
	}

	public static int[] randPerm(int n)  // en effektiv versjon
	//Programkode 1.1.8 e)
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

	public static void randPerm(int[] a)  // stokker om a
	//Programkode 1.1.8 f)
	{
		Random r = new Random();     // en randomgenerator

		for (int k = a.length - 1; k > 0; k--)
		{
			int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
			bytt(a,k,i);
		}
	}

	/*public static int maks(int[] a, int fra, int til)
	//Programkode 1.2.1 b)
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
	}*/

	/*public static int maks(int[] a, int fra, int til)
	//Maks-metoden er oppdatert med Programkode 1.2.3 b) og c)
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

	public static int maks(int[] a, int fra, int til)
	//Metoden er oppdatert slik at det også er kode som tester parameteren a og som gir en fornuftig feilmelding hvis a er null.
	{
		if (a == null) throw new NullPointerException
				("parametertabellen a er null!");

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
	}

	public static int maks(int[] a)  // bruker hele tabellen
	//Programkode 1.2.1 c)
	{
		return maks(a, 0, a.length);     // kaller metoden over
	}

	public static int min(int[] a, int fra, int til)
	//Oppgave 1 til Avsnitt 1.2.1
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
	//Oppgave 1 til Avsnitt 1.2.1
	{
		return min(a, 0, a.length);     // kaller metoden over
	}

	public static void bytt(char[] a, int i, int j)
	//Oppgave 3 til Avsnitt 1.2.1
	{
		char temp = a[i]; a[i] = a[j]; a[j] = temp;
	}

	public static void skriv(int[] a, int fra, int til)
	//Oppgave 4 til Avsnitt 1.2.1
	{
		fratilKontroll(a.length, fra, til);
		if (til - fra > 0) System.out.print(a[fra]);
		for (int i = fra + 1; i < til; i++) System.out.print(" " + a[i]);
	}

	public static void skriv(int[] a)
	//Oppgave 4 til Avsnitt 1.2.1
	{
		skriv(a, 0, a.length);
	}

	public static void skriv(char[] a, int fra, int til)
	//Oppgave 6 til Avsnitt 1.2.1
	{
		fratilKontroll(a.length, fra, til);
		if (til - fra > 0) System.out.print(a[fra]);
		for (int i = fra + 1; i < til; i++) System.out.print(" " + a[i]);
	}

	public static void skriv(char[] a)
	//Oppgave 6 til Avsnitt 1.2.1
	{
		skriv(a, 0, a.length);
	}

	public static void skrivln(char[] a, int fra, int til)
	//Oppgave 6 til Avsnitt 1.2.1
	{
		skriv(a, fra, til);
		System.out.println();
	}

	public static void skrivln(char[] a)
	//Oppgave 6 til Avsnitt 1.2.1
	{
		skrivln(a, 0, a.length);
	}

	public static int[] naturligeTall(int n)
	//Oppgave 7 til Avsnitt 1.2.1
	{
		if (n < 1) throw new
				IllegalArgumentException("n(" + n + ") er mindre enn 1!");

		int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = i + 1;
		return a;
	}

	public static int[] heleTall(int fra, int til)
	//Oppgave 7 til Avsnitt 1.2.1
	{
		if (fra > til) throw new
				IllegalArgumentException("fra(" + fra + ") > til(" + til + ")");

		int[] a = new int[til - fra];
		for (int i = fra; i < til; i++) a[i-fra] = i;
		return a;
	}

	public static void fratilKontroll(int tablengde, int fra, int til)
	//Programkode 1.2.3 a)
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

	public static void vhKontroll(int tablengde, int v, int h)
	//Programkode 1.2.3 d)
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

	public static void snu(int[] a, int v, int h)
	//Oppgave 5 til Avsnitt 1.2.3
	{
		vhKontroll(a.length,v,h);
		while (v < h) bytt(a,v++,h--);
	}

	public static void snu(int[] a)
	//Oppgave 5 til Avsnitt 1.2.3
	{
		int v = 0, h = a.length-1;
		while (v < h) bytt(a,v++,h--);
	}

	public static void snu(char[] a, int v, int h)
	//Oppgave 6 til Avsnitt 1.2.3
	{
		vhKontroll(a.length,v,h);
		while (v < h) bytt(a,v++,h--);
	}

	public static void snu(char[] a)
	////Oppgave 6 til Avsnitt 1.2.3
	{
		int v = 0, h = a.length-1;
		while (v < h) bytt(a,v++,h--);
	}

	/*public static int[] nestMaks(int[] a)  // legges i class Tabell
	//Programkode 1.2.4 a)
	{
		int n = a.length;   // tabellens lengde

		if (n < 2) throw   // må ha minst to verdier!
				new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

		int m = maks(a);  // m er posisjonen til tabellens største verdi

		int nm;     // nm skal inneholde posisjonen til nest største verdi

		if (m == 0)                            // den største ligger først
		{
			nm = maks(a,1,n);                    // leter i a[1:n>
		}
		else if (m == n-1)                     // den største ligger bakerst
		{
			nm = maks(a,0,n-1);                  // leter i a[0:n-1>
		}
		else
		{
			int mv = maks(a,0,m);                // leter i a[0:m>
			int mh = maks(a,m+1,n);              // leter i a[m+1:n>
			nm = a[mh] > a[mv] ? mh : mv;        // hvem er størst?
		}

		return new int[] {m,nm};      // m i posisjon 0 , nm i posisjon 1

	}*/ // nestMaks

	/*public static int[] nestMaks(int[] a) // ny versjon
	//Programkode 1.2.5 a)
	{
		int n = a.length;     // tabellens lengde
		if (n < 2) throw      // må ha minst to verdier
				new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

		int m = 0;      // m er posisjonen til største verdi
		int nm = 1;     // nm er posisjonen til nest største verdi

		// bytter om m og nm hvis a[1] er større enn a[0]
		if (a[1] > a[0]) { m = 1; nm = 0; }

		int maksverdi = a[m];                // største verdi
		int nestmaksverdi = a[nm];           // nest største verdi

		for (int i = 2; i < n; i++)
		{
			if (a[i] > nestmaksverdi)
			{
				if (a[i] > maksverdi)
				{
					nm = m;
					nestmaksverdi = maksverdi;     // ny nest størst

					m = i;
					maksverdi = a[m];              // ny størst
				}
				else
				{
					nm = i;
					nestmaksverdi = a[nm];         // ny nest størst
				}
			}
		} // for

		return new int[] {m,nm};    // n i posisjon 0, nm i posisjon 1

	}*/ // nestMaks

	public static int[] nestMaks(int[] a)
	//Oppgave 2 til Avsnitt 1.2.5
	{
		int n = a.length;  // tabellens lengde

		// må ha minst to verdier i tabellen
		if (a.length < 2) throw new
				IllegalArgumentException("a.length(" + a.length + ") < 2!");

		int sist = a.length - 1; // siste posisjon i tabellen

		// starter med å se på første og siste verdi i tabellen
		int m = 0; // første posisjon
		int nm = sist; // siste posisjon

		// m skal være sist hvis a[sist] er større enn a[0]
		if (a[sist] > a[0]) { m = sist; nm = 0; }

		int maksverdi = a[m];  // største verdi
		int nestmaksverdi = a[nm];  // nest største verdi

		int temp = a[sist];            // tar vare på siste verdi
		a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

		for (int i = 1; ; i++)
		{
			if (a[i] >= nestmaksverdi)
			{
				if (i == sist)
				{
					a[sist] = temp; int[] b = {m,nm}; return b;
				}
				else if (a[i] > maksverdi)
				{
					nm = m; nestmaksverdi = maksverdi;
					m = i; maksverdi = a[m];
				}
				else
				{
					nm = i;
					nestmaksverdi = a[nm]; // ny nest størst
				}
			}
		} // for
	} // nestMaks

	public static int[] nestMin(int[] a)
	////Oppgave 3 til Avsnitt 1.2.5
	{
		int n = a.length;   // tabellens lengde

		if (n < 2) throw new IllegalArgumentException
				("a.length(" + n + ") < 2!");

		int m = Tabell.min(a);   // m er posisjonen til tabellens minste verdi

		int nm = 0;   // nm står for nestmin

		if (m == 0) nm = Tabell.min(a,1,n);              // leter i a[1:n>
		else if (m == n-1) nm = Tabell.min(a,0,n-1);     // leter i a[0:n-1>
		else
		{
			int mv = Tabell.min(a,0,m);                   // leter i a[0:m>
			int mh = Tabell.min(a,m+1,n);                 // leter i a[m+1:n>
			nm = a[mh] < a[mv] ? mh : mv;           // hvem er minst?
		}

		int[] b = {m,nm};
		return b;  // minste verdi i b[0], nest minste i b[1]

	} // nestMin

	public static int[] tredjeMaks(int[] a)
	////Oppgave 4 til Avsnitt 1.2.5
	{
		int n = a.length;     // tabellens lengde

		if (n < 3)     // må ha minst tre verdier
			throw new IllegalArgumentException("a.length(" + n + ") < 3!");

		int m = 0;      // m er posisjonen til største verdi
		int nm = 1;     // nm er posisjonen til nest største verdi
		int tm = 2;     // tm er posisjonen til den tredje største

		// vi bytter om slik at når vi starter er m er posisjonen til
		// den største av de tre første, nm er posisjonen til den nest
		// største og tm posisjonen til den minste av de tre første

		if (a[nm] > a[m]) {int temp = m; m = nm; nm = temp;}
		if (a[tm] > a[m]) {int temp = tm; tm = m; m = temp;}
		if (a[tm] > a[nm]) {int temp = tm; tm = nm; nm = temp;}

		int maksverdi = a[m];                // største verdi
		int nestmaksverdi = a[nm];           // nest største verdi
		int tredjemaksverdi = a[tm];         // tredje største verdi

		for (int i = 3; i < n; i++)
		{
			if (a[i] > tredjemaksverdi)
			{
				if (a[i] > nestmaksverdi)
				{
					tm = nm;
					tredjemaksverdi = nestmaksverdi;  // ny tredje største verdi

					if (a[i] > maksverdi)
					{
						nm = m;
						nestmaksverdi = maksverdi;      // ny nest størst

						m = i;
						maksverdi = a[m];               // ny størst
					}
					else
					{
						nm = i;
						nestmaksverdi = a[nm];          // ny nest størst
					}
				}
				else
				{
					tm = i;
					tredjemaksverdi = a[tm];          // ny tredje største verdi
				}
			}
		} // for

		return new int[] {m,nm,tm};

	} // tredjeMaks



}
