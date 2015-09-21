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

		for (int i = 1; i < a.length; i++)    // gaar gjennom tabellen a
		{
			if (a[i] > maksverdi)    // a[i] er storre enn storste foran
			{
				antall++;              // har funnet et nytt tall
				maksverdi = a[i];      // oppdaterer maksverdi
			}
		}

		return antall;    // de som er storre enn det storste foran
	}

	public static int[] randPerm(int n)  // en effektiv versjon
	//Programkode 1.1.8 e)
	{
		Random r = new Random();         // en randomgenerator
		int[] a = new int[n];            // en tabell med plass til n tall

		Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

		for (int k = n - 1; k > 0; k--)  // l�kke som gaar n - 1 ganger
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

	int m = fra;              // indeks til storste verdi i a[fra:til>
	int maksverdi = a[fra];   // storste verdi i a[fra:til>

	for (int i = fra + 1; i < til; i++)
	{
		if (a[i] > maksverdi)
		{
			m = i;                // indeks til storste verdi oppdateres
			maksverdi = a[m];     // storste verdi oppdateres
		}
	}

	return m;  // posisjonen til storste verdi i a[fra:til>
	}*/

	/*public static int maks(int[] a, int fra, int til)
	//Maks-metoden er oppdatert med Programkode 1.2.3 b) og c)
	{
		fratilKontroll(a.length,fra,til);

		if (fra == til) throw new NoSuchElementException
				("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

		int m = fra;             // indeks til storste verdi i a[fra:til>
		int maksverdi = a[fra];  // storste verdi i a[fra:til>

		for (int i = fra + 1; i < til; i++) if (a[i] > maksverdi)
		{
			m = i;               // indeks til storste verdi oppdateres
			maksverdi = a[m];    // storste verdi oppdateres
		}

		return m;  // posisjonen til storste verdi i a[fra:til>
	}*/

	public static int maks(int[] a, int fra, int til)
	//Metoden er oppdatert slik at det ogsaa er kode som tester parameteren a og som gir en fornuftig feilmelding hvis a er null.
	{
		if (a == null) throw new NullPointerException
				("parametertabellen a er null!");

		fratilKontroll(a.length,fra,til);

		if (fra == til) throw new NoSuchElementException
				("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

		int m = fra;             // indeks til storste verdi i a[fra:til>
		int maksverdi = a[fra];  // storste verdi i a[fra:til>

		for (int i = fra + 1; i < til; i++) if (a[i] > maksverdi)
		{
			m = i;               // indeks til storste verdi oppdateres
			maksverdi = a[m];    // storste verdi oppdateres
		}

		return m;  // posisjonen til storste verdi i a[fra:til>
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

		if (fra > til)                                // fra er storre enn til
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

	public static void snu(int[] a, int v)  // snur fra og med v og ut tabellen
	{
		snu(a, v, a.length - 1);
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

	public static int[] nestMaks1(int[] a)  // legges i class Tabell
	//Programkode 1.2.4 a)
	{
		int n = a.length;   // tabellens lengde

		if (n < 2) throw   // maa ha minst to verdier!
				new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

		int m = maks(a);  // m er posisjonen til tabellens storste verdi

		int nm;     // nm skal inneholde posisjonen til nest storste verdi

		if (m == 0)                            // den storste ligger f�rst
		{
			nm = maks(a,1,n);                    // leter i a[1:n>
		}
		else if (m == n-1)                     // den storste ligger bakerst
		{
			nm = maks(a,0,n-1);                  // leter i a[0:n-1>
		}
		else
		{
			int mv = maks(a,0,m);                // leter i a[0:m>
			int mh = maks(a,m+1,n);              // leter i a[m+1:n>
			nm = a[mh] > a[mv] ? mh : mv;        // hvem er storst?
		}

		return new int[] {m,nm};      // m i posisjon 0 , nm i posisjon 1

	} // nestMaks

	public static int[] nestMaks(int[] a) // ny versjon
	//Programkode 1.2.5 a)
	{
		int n = a.length;     // tabellens lengde
		if (n < 2) throw      // maa ha minst to verdier
				new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

		int m = 0;      // m er posisjonen til storste verdi
		int nm = 1;     // nm er posisjonen til nest storste verdi

		// bytter om m og nm hvis a[1] er storre enn a[0]
		if (a[1] > a[0]) { m = 1; nm = 0; }

		int maksverdi = a[m];                // storste verdi
		int nestmaksverdi = a[nm];           // nest storste verdi

		for (int i = 2; i < n; i++)
		{
			if (a[i] > nestmaksverdi)
			{
				if (a[i] > maksverdi)
				{
					nm = m;
					nestmaksverdi = maksverdi;     // ny nest storst

					m = i;
					maksverdi = a[m];              // ny storst
				}
				else
				{
					nm = i;
					nestmaksverdi = a[nm];         // ny nest storst
				}
			}
		} // for

		return new int[] {m,nm};    // n i posisjon 0, nm i posisjon 1

	} // nestMaks

	public static int[] nestMaks2(int[] a)
	//Oppgave 2 til Avsnitt 1.2.5
	{
		int n = a.length;  // tabellens lengde

		// maa ha minst to verdier i tabellen
		if (a.length < 2) throw new
				IllegalArgumentException("a.length(" + a.length + ") < 2!");

		int sist = a.length - 1; // siste posisjon i tabellen

		// starter med aa se paa forste og siste verdi i tabellen
		int m = 0; // forste posisjon
		int nm = sist; // siste posisjon

		// m skal vaere sist hvis a[sist] er storre enn a[0]
		if (a[sist] > a[0]) { m = sist; nm = 0; }

		int maksverdi = a[m];  // storste verdi
		int nestmaksverdi = a[nm];  // nest storste verdi

		int temp = a[sist];            // tar vare paa siste verdi
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
					nestmaksverdi = a[nm]; // ny nest storst
				}
			}
		} // for
	} // nestMaks

	public static int[] nestMaks3(int[] a)   // en turnering
	//Programkode 1.2.13 a)
	{
		int n = a.length;                // for aa forenkle notasjonen

		if (n < 2) // maa ha minst to verdier!
			throw new IllegalArgumentException("a.length(" + n + ") < 2!");

		int[] b = new int[2*n];          // turneringstreet
		System.arraycopy(a,0,b,n,n);     // legger a bakerst i b

		for (int k = 2*n-2; k > 1; k -= 2)   // lager turneringstreet
			b[k/2] = Math.max(b[k],b[k+1]);

		int maksverdi = b[1], nestmaksverdi = Integer.MIN_VALUE;

		for (int m = 2*n - 1, k = 2; k < m; k *= 2)
			{
				int tempverdi = b[k+1];  // ok hvis maksverdi er b[k]
				if (maksverdi != b[k]) { tempverdi = b[k]; k++; }
				if (tempverdi > nestmaksverdi) nestmaksverdi = tempverdi;
			}

		return new int[] {maksverdi,nestmaksverdi}; // storst og nest storst

	} // nestMaks

	public static void kopier(int[] a, int i, int[] b, int j, int ant)
	{
		while (i < ant) b[j++] = a[i++];
	}

	public static int[] nestMin(int[] a)
	////Oppgave 3 til Avsnitt 1.2.5
	{
		int n = a.length;   // tabellens lengde

		if (n < 2) throw new IllegalArgumentException
				("a.length(" + n + ") < 2!");

		int m = Tabell.min(a);   // m er posisjonen til tabellens minste verdi

		int nm = 0;   // nm staar for nestmin

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

		if (n < 3)     // maa ha minst tre verdier
			throw new IllegalArgumentException("a.length(" + n + ") < 3!");

		int m = 0;      // m er posisjonen til storste verdi
		int nm = 1;     // nm er posisjonen til nest storste verdi
		int tm = 2;     // tm er posisjonen til den tredje storste

		// vi bytter om slik at naar vi starter er m er posisjonen til
		// den storste av de tre forste, nm er posisjonen til den nest
		// storste og tm posisjonen til den minste av de tre forste

		if (a[nm] > a[m]) {int temp = m; m = nm; nm = temp;}
		if (a[tm] > a[m]) {int temp = tm; tm = m; m = temp;}
		if (a[tm] > a[nm]) {int temp = tm; tm = nm; nm = temp;}

		int maksverdi = a[m];                // storste verdi
		int nestmaksverdi = a[nm];           // nest storste verdi
		int tredjemaksverdi = a[tm];         // tredje storste verdi

		for (int i = 3; i < n; i++)
		{
			if (a[i] > tredjemaksverdi)
			{
				if (a[i] > nestmaksverdi)
				{
					tm = nm;
					tredjemaksverdi = nestmaksverdi;  // ny tredje storste verdi

					if (a[i] > maksverdi)
					{
						nm = m;
						nestmaksverdi = maksverdi;      // ny nest storst

						m = i;
						maksverdi = a[m];               // ny storst
					}
					else
					{
						nm = i;
						nestmaksverdi = a[nm];          // ny nest storst
					}
				}
				else
				{
					tm = i;
					tredjemaksverdi = a[tm];          // ny tredje storste verdi
				}
			}
		} // for

		return new int[] {m,nm,tm};

	} // tredjeMaks

	public static boolean nestePermutasjon(int[] a)
	//Programkode 1.3.1 b)
	{
		int i = a.length - 2;                    // i starter nest bakerst
		while (i >= 0 && a[i] > a[i + 1]) i--;   // gaar mot venstre
		if (i < 0) return false;                 // a = {n, n-1, . . . , 2, 1}

		int j = a.length - 1;                    // j starter bakerst
		while (a[j] < a[i]) j--;                 // stopper naar a[j] > a[i]
		bytt(a,i,j); snu(a,i + 1);         // bytter og snur

		return true;                             // en ny permutasjon
	}

	public static int inversjoner(int[] a)
	//Programkode 1.3.2 a)
	{
		int antall = 0;  // antall inversjoner
		for (int i = 0; i < a.length - 1; i++)
			{
				for (int j = i + 1; j < a.length; j++)
					{
						if (a[i] > a[j]) antall++;  // en inversjon siden i < j
					}
			}
		return antall;
	}

	public static boolean erSortert(int[] a)  // legges i samleklassen Tabell
	//Programkode 1.3.2 c)
	{
		for (int i = 1; i < a.length; i++)      // starter med i = 1
			if (a[i-1] > a[i]) return false;      // en inversjon

		return true;
	}

	public static int boble(int[] a, int n)  // legges i samleklassen Tabell
	//Programkode 1.3.3 a)
	{
		int antall = 0;                 // antall ombyttinger i tabellen
		for (int i = 1; i < n; i++)     // gaar fra 1 til n
			{
				if (a[i - 1] > a[i])          // sammenligner to naboverdier
					{
						bytt(a,i - 1, i);           // bytter om to naboverdier
						antall++;                   // teller opp
					}
			}
		return antall;                  // returnerer
	}

	public static void boblesortering(int[] a)
	//Programkode 1.3.3 d)
	{
		for (int n = a.length; n > 1; n--)
			{
				if (boble(a, n) == 0) break;
			}
	}

	public static void utvalgssortering(int[] a)
	{
		for (int i = 0; i < a.length - 1; i++)
			{
				int m = i;             // indeks til den forel�pig minste
				int  minverdi = a[i];  // verdien til den forel�pig minste

				for (int j = i + 1; j < a.length; j++)
					{
						if (a[j] < minverdi)
							{
								minverdi = a[j];  // ny minste verdi
								m = j;            // indeksen til ny minste verdi
							}
					}
				// bytter om a[i] og a[m]
				int temp = a[i];
				a[i] = a[m];
				a[m] = temp;
			}
	}

	public static void utvalgssortering2(int[] a)
	//Programkode 1.3.4 a)
	{
		for (int i = 0; i < a.length - 1; i++)
			bytt(a, i, min(a, i, a.length));  // to hjelpemetoder
	}

	public static int linearsok(int[] a, int verdi) // legges i class Tabell
	//Programkode 1.3.5 b)
	{
		if (a.length == 0 || verdi > a[a.length-1])
			return -(a.length + 1);  // verdi er storre enn den storste

		int i = 0; for( ; a[i] < verdi; i++);  // siste verdi er vaktpost

		return verdi == a[i] ? i : -(i + 1);   // sjekker innholdet i a[i]
	}



}
