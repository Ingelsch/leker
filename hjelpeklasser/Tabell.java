package hjelpeklasser;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell //Samleklasse for tabellmetoder
{
	public static void bytt(int[] a, int i, int j)
	//Programkode 1.1.8 d)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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

	public static int maks(int[] a, int fra, int til)
	//Metoden er oppdatert slik at det også er kode som tester parameteren a og som
	// gir en fornuftig feilmelding hvis a er null.
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
			nm = a[mh] > a[mv] ? mh : mv;        // hvem er storst?
		}

		return new int[] {m,nm};      // m i posisjon 0 , nm i posisjon 1

	} // nestMaks

	public static int[] nestMaks(int[] a) // ny versjon
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

		// må ha minst to verdier i tabellen
		if (a.length < 2) throw new
				IllegalArgumentException("a.length(" + a.length + ") < 2!");

		int sist = a.length - 1; // siste posisjon i tabellen

		// starter med å se på forste og siste verdi i tabellen
		int m = 0; // forste posisjon
		int nm = sist; // siste posisjon

		// m skal vaere sist hvis a[sist] er større enn a[0]
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
					nestmaksverdi = a[nm]; // ny nest storst
				}
			}
		} // for
	} // nestMaks

	public static int[] nestMaks3(int[] a)   // en turnering
	//Programkode 1.2.13 a)
	{
		int n = a.length;                // for å forenkle notasjonen

		if (n < 2) // må ha minst to verdier!
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
		// den største av de tre forste, nm er posisjonen til den nest
		// største og tm posisjonen til den minste av de tre forste

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
					tredjemaksverdi = a[tm];          // ny tredje største verdi
				}
			}
		} // for

		return new int[] {m,nm,tm};

	} // tredjeMaks

	public static boolean nestePermutasjon(int[] a)
	//Programkode 1.3.1 b)
	{
		int i = a.length - 2;                    // i starter nest bakerst
		while (i >= 0 && a[i] > a[i + 1]) i--;   // går mot venstre
		if (i < 0) return false;                 // a = {n, n-1, . . . , 2, 1}

		int j = a.length - 1;                    // j starter bakerst
		while (a[j] < a[i]) j--;                 // stopper når a[j] > a[i]
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
		for (int i = 1; i < n; i++)     // går fra 1 til n
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
				int m = i;             // indeks til den foreløpig minste
				int  minverdi = a[i];  // verdien til den foreløpig minste

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

	public static int linearsøk(int[] a, int verdi) // legges i class Tabell
	//Programkode 1.3.5 b)
	{
		if (a.length == 0 || verdi > a[a.length-1])
			return -(a.length + 1);  // verdi er større enn den største

		int i = 0; for( ; a[i] < verdi; i++);  // siste verdi er vaktpost

		return verdi == a[i] ? i : -(i + 1);   // sjekker innholdet i a[i]
	}

	public static int binarsøk(int[] a, int fra, int til, int verdi)
	//Programkode 1.3.6 c)
	{
		Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
		int v = fra, h = til - 1;  // v og h er intervallets endepunkter

		while (v < h)  // obs. må ha v < h her og ikke v <= h
		{
			int m = (v + h)/2;  // heltallsdivisjon - finner midten

			if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
			else  h = m;                   // verdi må ligge i a[v:m]
		}
		if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
		else if (verdi == a[v]) return v;            // funnet
		else  return -(v + 2);                       // ikke funnet
	}

	public static void innsettingssortering(int[] a, int fra, int til)
	//Avsnitt 1.3.8 oppg 7
	{
		fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)

		for (int i = fra + 1; i < til; i++)  // a[fra] er første verdi
		{
			int temp = a[i];  // flytter a[i] til en hjelpevariabel

			// verdier flyttes inntil rett sortert plass i a[fra:i> er funnet
			int j = i-1; for (; j >= fra && temp < a[j]; j--) a[j+1] = a[j];

			a[j+1] = temp;  // verdien settes inn på rett sortert plass
		}
	}

	public static int parter(int[] a, int v, int h, int skilleverdi)
	//	Programkode 1.3.9 a)
	{
		while (v <= h && a[v] < skilleverdi) v++;     // h er stoppverdi for v
		while (v <= h && a[h] >= skilleverdi) h--;    // v er stoppverdi for h

		while (true)                                  // stopper når v >= h
		{
			if (v < h) Tabell.bytt(a,v++,h--);          // bytter om a[v] og a[h]
			else  return v;                             // partisjoneringen er ferdig
			while (a[v] < skilleverdi) v++;             // flytter v mot høyre
			while (a[h] >= skilleverdi) h--;            // flytter h mot venstre
		}
	}
	private static void kvikksortering1(int[] a, int v, int h)  // en privat metode
	//Programkode 1.3.9 h)
	{
		if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
		int k = Tabell.parter(a, v, h, (v + h) / 2);  // bruker midtverdien
		kvikksortering1(a, v, k - 1);     // sorterer intervallet a[v:k-1]
		kvikksortering1(a, k + 1, h);     // sorterer intervallet a[k+1:h]
	}

	public static void kvikksortering(int[] a, int fra, int til) // fra/til i sortering
	//Programkode 1.3.9 h)
	{
		Tabell.fratilKontroll(a.length, fra, til);  // sjekker når metoden er offentlig
		kvikksortering1(a, fra, til - 1);  // v = fra, h = til - 1
	}

	public static void kvikksortering(int[] a)   // sorterer hele tabellen
	//Programkode 1.3.9 h)
	{
		kvikksortering1(a, 0, a.length - 1);
	}

	public static int flett(int[] a, int m, int[] b, int n, int[] c)
	//Programkode 1.3.11 c)
	{
		int i = 0, j = 0, k = 0;
		while (i < m && j < n) c[k++] = a[i] <= b[j] ? a[i++] : b[j++];

		while (i < m) c[k++] = a[i++];   // tar med resten av a
		while (j < n) c[k++] = b[j++];   // tar med resten av b

		return k;   // antallet verdier som er lagt inn i c
	}

	public static int flett(int[] a, int[] b, int[] c) // legges i samleklassen Tabell
	//Programkode 1.3.11 d)
	{
		return flett(a, a.length, b, b.length, c);
	}

	private static void flett(int[] a, int[] b, int fra, int m, int til)
	//Programkode 1.3.11 f)
	{
		int n = m - fra;                // antall elementer i a[fra:m>
		System.arraycopy(a,fra,b,0,n);  // kopierer a[fra:m> over i b[0:n>

		int i = 0, j = m, k = fra;      // løkkeST0r og indekser

		while (i < n && j < til)        // fletter b[0:n> og a[m:til> og
		{                               // legger resultatet i a[fra:til>
			a[k++] = b[i] <= a[j] ? b[i++] : a[j++];
		}

		while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
	}

		//Flg. rekursive (og private) metode benytter flett-metoden i Programkode 1.3.11 f):

	private static void flettesortering(int[] a, int[] b, int fra, int til)
	//Programkode 1.3.11 g)
	{
		if (til - fra <= 1) return;   // a[fra:til> har maks ett element
		int m = (fra + til)/2;        // midt mellom fra og til

		flettesortering(a,b,fra,m);   // sorterer a[fra:m>
		flettesortering(a,b,m,til);   // sorterer a[m:til>

		if (a[m-1] > a[m]) flett(a,b,fra,m,til);  // fletter a[fra:m> og a[m:til>
	}

	//I koden over deles a[fra:til> på midten og metoden kalles (rekursjon) først
	// på a[fra:m> og så på a[m:til>. Etterpå vil de være sortert og kan flettes sammen.
	// Legg merke til setningen: if (a[m-1] > a[m]). Intervallet a[fra:til> er allerede sortert
	// hvis den siste i a[fra:m> er mindre enn eller lik den første i a[m:til>. Midtdelingen gjør
	// at dette blir av orden n?log2(n) og dermed en effektiv metode. Flg. offentlige metode sorterer
	// en hel tabell:

	public static void flettesortering(int[] a)
	//Programkode 1.3.11 h)
	{
		int[] b = Arrays.copyOf(a, a.length/2);   // en hjelpetabell for flettingen
		flettesortering(a,b,0,a.length);          // kaller metoden over
	}

	public static int union(int[] a, int m, int[] b, int n, int[] c)
	//Programkode 1.3.11 j)
	{
		int i = 0, j = 0, k = 0;             // indekser for a, b og c
		while (i < m && j < n)
		{
			if (a[i] < b[j]) c[k++] = a[i++];  // tar med a[i]
			else if (a[i] == b[j])             // a[i] og b[j] er like
			{
				c[k++] = a[i++]; j++;            // tar med a[i], men ikke b[j]
			}
			else  c[k++] = b[j++];             // tar med b[j]
		}

		while (i < m) c[k++] = a[i++];       // tar med resten av a[0:m>
		while (j < n) c[k++] = b[j++];       // tar med resten av b[0:n>

		return k;                            // antall verdier i unionen
	}

	//Metoden union() returnerer antallet. Det er mindre enn m + n hvis a og b har felles verdier.
	// Flg. metode finner «unionen» av hele tabeller:

	public static int union(int[] a, int[] b, int[] c)
	//	Programkode 1.3.11 k)
	{
		return union(a, a.length, b, b.length, c);
	}

	//Snittet av to mengder er det de har felles. Vi kan finne snittet ved å bruke en fletteteknikk:

	public static int snitt(int[] a, int m, int[] b, int n, int[] c)
	//Programkode 1.3.11 m)
	{
		int i = 0, j = 0, k = 0;             // indekser for a, b og c
		while (i < m && j < n)
		{
			if (a[i] < b[j]) i++;              // hopper over a[i]
			else if (a[i] == b[j])             // a[i] og b[j] er like
			{
				c[k++] = a[i++]; j++;            // tar med a[i], men ikke b[j]
			}
			else  j++;                         // hopper over b[j]
		}

		return k;                            // antall i snittet
	}

	public static int snitt(int[] a, int[] b, int[] c)  // hele tabeller
	//Programkode 1.3.11 m)
	{
		return snitt(a, a.length, b, b.length, c);
	}


	public static boolean erLik(int[] a, int m, int[] b, int n)
	//Avsnitt 1.3.11 Oppgave 7
	{
		if (m < 0 || m > a.length)
			throw new IllegalArgumentException("a[0:m> er ulovlig!");

		if (n < 0 || n > b.length)
			throw new IllegalArgumentException("b[0:n> er ulovlig!");

		if (m != n) return false;  // forskjellige lengder

		if (a == b) return true;   // det samme intervallet

		for (int i = 0; i < m; i++)
			if (a[i] != b[i]) return false;

		return true;
	}

	public static boolean erLik(int[] a, int[] b)
	//Avsnitt 1.3.11 Oppgave 7
	{
		return erLik(a,a.length,b,b.length);
	}


	public static int differens(int[] a, int m, int[] b, int n, int[] c)
	//Avsnitt 1.3.11 Oppgave 8
	{
		if (m < 0 || m > a.length)
			throw new IllegalArgumentException("a[0:m> er ulovlig!");

		if (n < 0 || n > b.length)
			throw new IllegalArgumentException("b[0:n> er ulovlig!");

		int i = 0, j = 0, k = 0;

		while (i < m && j < n)
		{
			if (a[i] < b[j]) c[k++] = a[i++];
			else if (a[i] == b[j]) { i++; j++;}
			else j++;
		}
		while (i < m) c[k++] = a[i++];

		return k;
	}

	public static int differens(int[] a, int[] b, int[] c)
	//Avsnitt 1.3.11 Oppgave 8
	{
		return differens(a, a.length, b, b.length,c);
	}



	public static boolean inklusjon(int[] a, int m, int[] b, int n)
	//Avsnitt 1.3.11 Oppgave 9
	{
		if (m < 0 || m > a.length)
			throw new IllegalArgumentException("a[0:m> er ulovlig!");

		if (n < 0 || n > b.length)
			throw new IllegalArgumentException("b[0:n> er ulovlig!");

		int i = 0, j = 0;

		while (i < m && j < n)
		{
			if (a[i] < b[j]) i++;
			else if (a[i] == b[j]) {i++; j++;}
			else return false;
		}

		return j == n;
	}

	public static boolean inklusjon(int[] a, int[] b)
	//Avsnitt 1.3.11 Oppgave 9
	{
		return inklusjon(a, a.length, b, b.length);
	}


	public static int xunion(int[] a, int m, int[] b, int n, int[] c)
	//Avsnitt 1.3.11 Oppgave 10
	{
		if (m < 0 || m > a.length)
			throw new IllegalArgumentException("a[0:m> er ulovlig!");

		if (n < 0 || n > b.length)
			throw new IllegalArgumentException("b[0:n> er ulovlig!");

		int i = 0, j = 0, k = 0;

		while (i < m && j < n)
		{
			if (a[i] < b[j]) c[k++] = a[i++];
			else if (a[i] == b[j]) { i++; j++;}
			else c[k++] = b[j++];
		}
		while (i < m) c[k++] = a[i++];
		while (j < n) c[k++] = b[j++];

		return k;
	}

	public static int xunion(int[] a, int[] b, int[] c)
	//Avsnitt 1.3.11 Oppgave 10
	{
		return xunion(a, a.length, b, b.length,c);
	}

	public static int maks(double[] a)     // legges i class Tabell
	//Programkode 1.4.1 a)
	{
		int m = 0;                           // indeks til største verdi
		double maksverdi = a[0];             // største verdi

		for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
		{
			maksverdi = a[i];     // største verdi oppdateres
			m = i;                // indeks til største verdi oppdaters
		}
		return m;     // returnerer posisjonen til største verdi
	}

	public static int maks(String[] a)    // legges i class Tabell
	//Programkode 1.4.1 b)
	{
		int m = 0;                          // indeks til største verdi
		String maksverdi = a[0];            // største verdi

		for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
		{
			maksverdi = a[i];  // største verdi oppdateres
			m = i;             // indeks til største verdi oppdaters
		}
		return m;  // returnerer posisjonen til største verdi
	}

	public static <T extends Comparable<? super T>> int maks(T[] a)
	//Programkode 1.4.2 b)
	{
		int m = 0;                     // indeks til største verdi
		T maksverdi = a[0];            // største verdi

		for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
		{
			maksverdi = a[i];  // største verdi oppdateres
			m = i;             // indeks til største verdi oppdaters
		}
		return m;  // returnerer posisjonen til største verdi

	} // maks

	public static <T extends Comparable<? super T>> void innsettingssortering(T[] a)
	// Programkode 1.4.2 e)
	{
		for (int i = 1, j = 0; i < a.length; j = i++)  // starter med i = 1
		{
			T verdi = a[i];          // flytter a[i] til en hjelpevariabel
			while (verdi.compareTo(a[j]) < 0)  // sammenligner
			{
				a[j + 1] = a[j];       // forskyver mot høyre
				if (j-- == 0) break;   // venstre ende av tabellen
			}
			a[j + 1] = verdi;        // verdien inn på rett sortert plass
		}
	}

	public static void bytt(Object[] a, int i, int j)
	//Programkode 1.4.3 d)
	{
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static Integer[] randPermInteger(int n)
	//Programkode 1.4.3 d)
	{
		Integer[] a = new Integer[n];              // en Integer-tabell
		for (int i = 0; i < n; i++) a[i] = i + 1;  // tallene fra 1 til n

		Random r = new Random();   // hentes fra  java.util

		for (int k = n - 1; k > 0; k--)
		{
			int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
			bytt(a,k,i);             // bytter om
		}
		return a;  // tabellen med permutasjonen returneres
	}


	public static void skriv(Object[] a, int fra, int til)
	//Avsnitt 1.4.3 Oppgave 5
	{
		fratilKontroll(a.length,fra,til);

		for (int i = fra; i < til; i++) System.out.print(a[i] + " ");
	}

	public static void skriv(Object[] a)
	//Avsnitt 1.4.3 Oppgave 5
	{
		skriv(a,0,a.length);
	}

	public static void skrivln(Object[] a, int fra, int til)
	//Avsnitt 1.4.3 Oppgave 5
	{
		skriv(a,fra,til);
		System.out.println();
	}

	public static void skrivln(Object[] a)
	{
		skrivln(a,0,a.length);
	}
	public static <T> void innsettingssortering(T[] a, Komparator<? super T> c)
	//Programkode 1.4.6 b)
	{
		for (int i = 1, j = 0; i < a.length; j = i++)  // starter med i = 1
		{
			T verdi = a[i];          // flytter a[i] til en hjelpevariabel

			while (c.compare(verdi,a[j]) < 0)  // sammenligner
			{
				a[j + 1] = a[j];       // forskyver mot høyre
				if (j-- == 0) break;   // venstre ende av tabellen
			}

			a[j + 1] = verdi;        // verdien inn på rett sortert plass
		}
	}


	public static <T> int maks(T[] a, Komparator<? super T> c)
	//Avsnitt 1.4.6 Oppgave 4
	{
		return maks(a, 0, a.length, c);  // kaller metoden nedenfor
	}

	public static <T> int maks(T[] a, int fra, int til, Komparator<? super T> c)
	//Avsnitt 1.4.6 Oppgave 4
	{
		fratilKontroll(a.length,fra,til);

		if (fra == til) throw new NoSuchElementException
				("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

		int m = fra;                // indeks til største verdi
		T maksverdi = a[fra];       // største verdi

		for (int i = fra + 1; i < til; i++)   // går gjennom intervallet
		{
			if (c.compare(a[i],maksverdi) > 0)  // bruker komparatoren
			{
				maksverdi = a[i];     // største verdi oppdateres
				m = i;                // indeks til største verdi oppdateres
			}
		}
		return m;                 // posisjonen til største verdi

	}  // maks

	//Avsnitt 1.4.9 Oppgave 2 a)

	//Hvis samleklassen Tabell allerede har komparatorversjonen av maks-metoden og en generisk
	// bytt-metode, så vil utvalgssortering kunne kodes slik:

	public static <T> void utvalgssortering(T[] a, Komparator<? super T> c)
	{
		for (int k = a.length; k > 1; k--)
		{
			// bytter om: største verdi i a[0:k> flyttes til plass k-1
			bytt(a,maks(a,0,k,c),k-1);
		}
	}

	//Avsnitt 1.4.9 Oppgave 2 b)
	public static <T>
	int binærsøk(T[] a, int fra, int til, T verdi, Comparator<? super T> c)
	{
		Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
		int v = fra, h = til - 1;    // v og h er intervallets endepunkter

		while (v <= h)  // fortsetter så lenge som a[v:h] ikke er tom
		{
			int m = (v + h)/2;     // heltallsdivisjon - finner midten
			T midtverdi = a[m];  // hjelpevariabel for  midtverdien

			int cmp = c.compare(verdi, midtverdi);

			if (cmp > 0) v = m + 1;        // verdi i a[m+1:h]
			else if (cmp < 0) h = m - 1;   // verdi i a[v:m-1]
			else return m;                 // funnet
		}

		return -(v + 1);   // ikke funnet, v er relativt innsettingspunkt
	}

	public static <T> int binærsøk(T[] a, T verdi, Comparator<? super T> c)
	{
		return binærsøk(a,0,a.length,verdi,c);  // bruker metoden over
	}

	//Avsnitt 1.4.9 Oppgave 2 c)
	public static <T>
	int parter(T[] a, int v, int h, T skilleverdi, Comparator<? super T> c)
	{
		while (v <= h && c.compare(a[v],skilleverdi) < 0) v++;
		while (v <= h && c.compare(skilleverdi,a[h]) <= 0) h--;

		while (true)
		{
			if (v < h) Tabell.bytt(a,v++,h--); else return v;
			while (c.compare(a[v],skilleverdi) < 0) v++;
			while (c.compare(skilleverdi,a[h]) <= 0) h--;
		}
	}

	public static <T> int parter(T[] a, T skilleverdi, Comparator<? super T> c)
	{
		return parter(a,0,a.length-1,skilleverdi,c);  // kaller metoden over
	}

	public static <T>
	int sParter(T[] a, int v, int h, int k, Comparator<? super T> c)
	{
		if (v < 0 || h >= a.length || k < v || k > h) throw new
				IllegalArgumentException("Ulovlig parameterverdi");

		bytt(a,k,h);   // bytter - skilleverdien a[k] legges bakerst
		int p = parter(a,v,h-1,a[h],c);  // partisjonerer a[v:h-1]
		bytt(a,p,h);   // bytter for å få skilleverdien på rett plass

		return p;    // returnerer posisjonen til skilleverdien
	}

	public static <T>
	int sParter(T[] a, int k, Comparator<? super T> c)   // bruker hele tabellen
	{
		return sParter(a,0,a.length-1,k,c); // v = 0 og h = a.lenght-1
	}

	private static <T>
	void kvikksortering(T[] a, int v, int h, Comparator<? super T> c)
	{
		if (v >= h) return;  // hvis v = h er a[v:h] allerede sortert

		int p = sParter(a,v,h,(v + h)/2,c);
		kvikksortering(a,v,p-1,c);
		kvikksortering(a,p+1,h,c);
	}

	public static <T>
	void kvikksortering(T[] a, Comparator<? super T> c) // sorterer hele tabellen
	{
		kvikksortering(a,0,a.length-1,c);
	}

	//Oppgave 2 d)
	private static <T>
	void flett(T[] a, T[] b, int fra, int m, int til, Comparator<? super T> c)
	{
		int n = m - fra;   // antall elementer i a[fra:m>
		System.arraycopy(a,fra,b,0,n); // kopierer a[fra:m> over i b[0:n>

		int i = 0, j = m, k = fra;     // løkkevariabler og indekser

		while (i < n && j < til)  // fletter b[0:n> og a[m:til>, legger
			a[k++] = c.compare(b[i],a[j]) <= 0 ? b[i++] : a[j++];  // resultatet i a[fra:til>

		while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
	}

	public static <T>
	void flettesortering(T[] a, T[] b, int fra, int til, Comparator<? super T> c)
	{
		if (til - fra <= 1) return;     // a[fra:til> har maks ett element

		int m = (fra + til)/2;          // midt mellom fra og til

		flettesortering(a,b,fra,m,c);   // sorterer a[fra:m>
		flettesortering(a,b,m,til,c);   // sorterer a[m:til>

		flett(a,b,fra,m,til,c);         // fletter a[fra:m> og a[m:til>
	}

	public static <T> void flettesortering(T[] a, Comparator<? super T> c)
	{
		T[] b = Arrays.copyOf(a, a.length/2);
		flettesortering(a,b,0,a.length,c);  // kaller metoden over
	}



}
