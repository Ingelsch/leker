package hjelpeklasser;

import java.util.*;

/**
 * Created by inge on 28.10.2015.
 */
public class TabellKø<T> implements Kø<T>
{
	private T[] a;      // en tabell
	private int fra;    // posisjonen til den første i køen
	private int til;    // posisjonen til første ledige plass

	private T[] utvidTabell(int lengde)
	{
		T[] b = (T[]) new Object[lengde];  // ny tabell

		// kopierer intervallet a[fra:a.length> over i b
		System.arraycopy(a, fra, b, 0, a.length - fra);

		// kopierer intervallet a[0:fra> over i b
		System.arraycopy(a, 0, b, a.length - fra, fra);

		fra = 0;
		til = a.length;

		return b;
	}

	public TabellKø(int lengde)
	{
		if (lengde < 0)
		{
			throw new
					IllegalArgumentException("Negativ tabellengde(" + lengde + ")!");
		}

		lengde = lengde == 0 ? 1 : Integer.highestOneBit(lengde) << 1;

		if (lengde < 0)    // kan få oversvømmelse her
		{
			throw new IllegalArgumentException("For stor tabellengde!");
		}

		a = (T[]) new Object[lengde];
		fra = til = 0;
	}

	public TabellKø()   // standardkonstruktør
	{
		this(8);
	}

	public boolean leggInn(T t)
	{
		a[til] = t;                         // ny verdi bakerst i køen
		til = (til + 1) & (a.length - 1);   // øker med 1
		if (fra == til)                     // sjekker om tabellen er full
		{
			a = utvidTabell(2 * a.length);      // dobler tabellen
		}

		return leggInn(t);
	}

	public T taUt()
	{
		if (fra == til)                      // sjekker om køen er tom
		{
			throw new NoSuchElementException("Køen er tom!");
		}

		T temp = a[fra];                     // tar vare på den første i køen
		a[fra] = null;                       // nuller innholdet
		fra = (fra + 1) & (a.length - 1);    // øker fra med 1
		return temp;                         // returnerer den første
	}

	public T kikk()
	{
		if (fra == til)
		{
			throw new NoSuchElementException("Køen er tom!");
		}

		return a[fra];
	}

	public int antall()
	{
		return (til - fra) & (a.length - 1);
	}

	public boolean tom()
	{
		return til == fra;
	}

	public void nullstill()
	{
		while (fra != til)
		{
			a[fra] = null;
			fra = (fra + 1) & (a.length - 1);
		}
	}

	public String toString()
	{
		if (til == fra)
		{
			return "[]";
		}

		StringBuilder s = new StringBuilder();
		s.append('[');
		s.append(a[fra]);
		fra = (fra + 1) & (a.length - 1);

		while (fra != til)
		{
			s.append(',');
			s.append(' ');
			s.append(a[fra]);
			fra = (fra + 1) & (a.length - 1);
		}

		s.append(']');

		return s.toString();
	}

	public int indeksTil(T t)
	{
		int k = fra;

		while (k != til)
		{
			if (t.equals(a[k]))
			{
				return fra <= k ? k - fra : a.length + k - fra;
			}

			k++;
			if (k == a.length)
			{
				k = 0;
			}
		}
		return -1;  // ikke funnet

	}

	public static <T> void snu(Stakk<T> A)
	{
		Kø<T> B = new TabellKø<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu(Kø<T> A)
	{
		Stakk<T> B = new TabellStakk<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu2(Kø<T> A)
	{
		Kø<T> B = new TabellKø<T>();
		Kø<T> C = new TabellKø<T>();

		while (!A.tom())
		{
			while (!B.tom()) C.leggInn(B.taUt());
			B.leggInn(A.taUt());
			while (!C.tom()) B.leggInn(C.taUt());
		}
		while (!B.tom()) A.leggInn(B.taUt());

	}

	public static <T> void sorter(Kø<T> kø, Stakk<T> stakk, Comparator<? super T> c)
	{
		int n = kø.antall();

		while (n > 0)
		{
			stakk.leggInn(kø.taUt());       // kandidat for å være den største

			for (int i = 1; i < n; i++)
			{
				T verdi = kø.taUt();
				if (c.compare(verdi, stakk.kikk()) > 0)
				{
					kø.leggInn(stakk.taUt());   // fant en som var større - den
					stakk.leggInn(verdi);       // legges øverst på stakken
				}
				else
				{
					kø.leggInn(verdi);
				}
			}
			n--;
		}

		while (!stakk.tom()) kø.leggInn(stakk.taUt());  // flytter fra stakk til kø
	}
}// TabellKø

/*private T[] a;      // en tabell
	private int fra;    // posisjonen til den første i køen
	private int til;    // posisjonen til første ledige plass

	@SuppressWarnings("unchecked")      // pga. konverteringen: Object[] -> T[]
	private T[] utvidTabell(int lengde)
	{
		T[] b = (T[])new Object[lengde];  // ny tabell

		// kopierer intervallet a[fra:a.length> over i b
		System.arraycopy(a,fra,b,0,a.length - fra);

		// kopierer intervallet a[0:fra> over i b
		System.arraycopy(a,0,b,a.length - fra, fra);

		fra = 0; til = a.length;

		return b;
	}

	public TabellKø(int lengde)
	{
		if (lengde < 1)
			throw new IllegalArgumentException("Må ha positiv lengde!");

		a = (T[])new Object[lengde];

		fra = til = 0;    // a[fra:til> er tom
	}

	public TabellKø()   // standardkonstruktør
	{
		this(8);
	}

	public void leggInn(T t)
	{
		a[til] = t;                       // ny verdi bakerst i køen
		til++;                            // øker til med 1
		if (til == a.length) til = 0;     // hopper til 0
		if (fra == til)                   // sjekker om tabellen er full
			a = utvidTabell(2*a.length);    // dobler tabellen
	}

	public T kikk()
	{
		if (fra == til)
			throw new NoSuchElementException("Køen er tom!");

		return a[fra];
	}

	public T taUt()
	{
		if (fra == til) throw new         // sjekker om køen er tom.
				NoSuchElementException("Køen er tom!");

		T temp = a[fra];                  // tar vare på den første i køen
		a[fra] = null;                    // nuller innholdet
		fra++;                            // øker fra med 1
		if (fra == a.length) fra = 0;     // hopper til 0
		return temp;                      // returnerer den første
	}

	public int antall()
	{
		return fra <= til ? til - fra : a.length + til - fra;
	}

	public boolean tom()
	{
		return til == fra;
	}

	public void nullstill()
	{
		while (fra != til)
		{
			a[fra++] = null;
			if (fra == a.length) fra = 0;
		}
	}

	public String toString()
	{
		if (til == fra) return "[]";

		int sfra = fra, stil = til;

		StringBuilder s = new StringBuilder();
		s.append('[').append(a[sfra]);
		sfra++;
		if (sfra == a.length) sfra = 0;

		while (sfra != stil)
		{
			s.append(',').append(' ').append(a[sfra]);
			sfra++;
			if (sfra == a.length) sfra = 0;
		}

		s.append(']');

		return s.toString();
	}

	public int indeksTil(T t)
	{
		int k = fra;

		while (k != til)
		{
			if (t.equals(a[k]))
				return fra <= k ? k - fra : a.length + k - fra;

			k++; if (k == a.length) k = 0;
		}
		return -1;  // ikke funnet

	}

	public static <T> void snu(Stakk<T> A)
	{
		Kø<T> B = new TabellKø<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu(Kø<T> A)
	{
		Stakk<T> B = new TabellStakk<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu2(Kø<T> A)
	{
		Kø<T> B = new TabellKø<T>();
		Kø<T> C = new TabellKø<T>();

		while (!A.tom())
		{
			while (!B.tom()) C.leggInn(B.taUt());
			B.leggInn(A.taUt());
			while (!C.tom()) B.leggInn(C.taUt());
		}
		while (!B.tom()) A.leggInn(B.taUt());
	}*/
