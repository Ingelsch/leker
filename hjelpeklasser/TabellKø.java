package hjelpeklasser;

import java.util.*;

/**
 * Created by inge on 28.10.2015.
 */
public class TabellK�<T> implements K�<T>
{
	private T[] a;      // en tabell
	private int fra;    // posisjonen til den f�rste i k�en
	private int til;    // posisjonen til f�rste ledige plass

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

	public TabellK�(int lengde)
	{
		if (lengde < 0)
		{
			throw new
					IllegalArgumentException("Negativ tabellengde(" + lengde + ")!");
		}

		lengde = lengde == 0 ? 1 : Integer.highestOneBit(lengde) << 1;

		if (lengde < 0)    // kan f� oversv�mmelse her
		{
			throw new IllegalArgumentException("For stor tabellengde!");
		}

		a = (T[]) new Object[lengde];
		fra = til = 0;
	}

	public TabellK�()   // standardkonstrukt�r
	{
		this(8);
	}

	public boolean leggInn(T t)
	{
		a[til] = t;                         // ny verdi bakerst i k�en
		til = (til + 1) & (a.length - 1);   // �ker med 1
		if (fra == til)                     // sjekker om tabellen er full
		{
			a = utvidTabell(2 * a.length);      // dobler tabellen
		}

		return leggInn(t);
	}

	public T taUt()
	{
		if (fra == til)                      // sjekker om k�en er tom
		{
			throw new NoSuchElementException("K�en er tom!");
		}

		T temp = a[fra];                     // tar vare p� den f�rste i k�en
		a[fra] = null;                       // nuller innholdet
		fra = (fra + 1) & (a.length - 1);    // �ker fra med 1
		return temp;                         // returnerer den f�rste
	}

	public T kikk()
	{
		if (fra == til)
		{
			throw new NoSuchElementException("K�en er tom!");
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
		K�<T> B = new TabellK�<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu(K�<T> A)
	{
		Stakk<T> B = new TabellStakk<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu2(K�<T> A)
	{
		K�<T> B = new TabellK�<T>();
		K�<T> C = new TabellK�<T>();

		while (!A.tom())
		{
			while (!B.tom()) C.leggInn(B.taUt());
			B.leggInn(A.taUt());
			while (!C.tom()) B.leggInn(C.taUt());
		}
		while (!B.tom()) A.leggInn(B.taUt());

	}

	public static <T> void sorter(K�<T> k�, Stakk<T> stakk, Comparator<? super T> c)
	{
		int n = k�.antall();

		while (n > 0)
		{
			stakk.leggInn(k�.taUt());       // kandidat for � v�re den st�rste

			for (int i = 1; i < n; i++)
			{
				T verdi = k�.taUt();
				if (c.compare(verdi, stakk.kikk()) > 0)
				{
					k�.leggInn(stakk.taUt());   // fant en som var st�rre - den
					stakk.leggInn(verdi);       // legges �verst p� stakken
				}
				else
				{
					k�.leggInn(verdi);
				}
			}
			n--;
		}

		while (!stakk.tom()) k�.leggInn(stakk.taUt());  // flytter fra stakk til k�
	}
}// TabellK�

/*private T[] a;      // en tabell
	private int fra;    // posisjonen til den f�rste i k�en
	private int til;    // posisjonen til f�rste ledige plass

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

	public TabellK�(int lengde)
	{
		if (lengde < 1)
			throw new IllegalArgumentException("M� ha positiv lengde!");

		a = (T[])new Object[lengde];

		fra = til = 0;    // a[fra:til> er tom
	}

	public TabellK�()   // standardkonstrukt�r
	{
		this(8);
	}

	public void leggInn(T t)
	{
		a[til] = t;                       // ny verdi bakerst i k�en
		til++;                            // �ker til med 1
		if (til == a.length) til = 0;     // hopper til 0
		if (fra == til)                   // sjekker om tabellen er full
			a = utvidTabell(2*a.length);    // dobler tabellen
	}

	public T kikk()
	{
		if (fra == til)
			throw new NoSuchElementException("K�en er tom!");

		return a[fra];
	}

	public T taUt()
	{
		if (fra == til) throw new         // sjekker om k�en er tom.
				NoSuchElementException("K�en er tom!");

		T temp = a[fra];                  // tar vare p� den f�rste i k�en
		a[fra] = null;                    // nuller innholdet
		fra++;                            // �ker fra med 1
		if (fra == a.length) fra = 0;     // hopper til 0
		return temp;                      // returnerer den f�rste
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
		K�<T> B = new TabellK�<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu(K�<T> A)
	{
		Stakk<T> B = new TabellStakk<T>();
		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) A.leggInn(B.taUt());
	}

	public static <T> void snu2(K�<T> A)
	{
		K�<T> B = new TabellK�<T>();
		K�<T> C = new TabellK�<T>();

		while (!A.tom())
		{
			while (!B.tom()) C.leggInn(B.taUt());
			B.leggInn(A.taUt());
			while (!C.tom()) B.leggInn(C.taUt());
		}
		while (!B.tom()) A.leggInn(B.taUt());
	}*/
