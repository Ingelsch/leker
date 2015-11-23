package hjelpeklasser;

/**
 * Created by inge on 19.11.2015.
 */

import java.util.*;

public class UsortertTabellPrioritetsK�<T> implements PrioritetsK�<T>
{
	private T[] a;                       // en usortert tabell
	private int antall;                  // antall verdier i k�en
	private Comparator<? super T> c;     // en komparator

	public UsortertTabellPrioritetsK�(int st�rrelse, Comparator<? super T> c)
	{
		a = (T[]) new Object[st�rrelse];   // tabellens startst�rrelse
		antall = 0;
		this.c = c;
	}

	public UsortertTabellPrioritetsK�(Comparator<? super T> c)
	{
		this(8, c);  // bruker 8 som startst�rrelse
	}

	public static <T extends Comparable<? super T>> PrioritetsK�<T> naturligOrdenK�()
	{
		return new UsortertTabellPrioritetsK�<>(Comparator.naturalOrder());
	}


	@Override
	public void leggInn(T verdi)
	{
		if (verdi == null)
		{
			throw new IllegalArgumentException("Nullverdi!");
		}

		if (antall == a.length)
		{
			a = Arrays.copyOf(a, 2 * antall + 1);  // utvider
		}

		a[antall++] = verdi;          // verdi legges inn bakerst
	}

	@Override
	public T kikk()
	{
		if (tom())
		{
			throw
					new NoSuchElementException("K�en er tom!");
		}

		return a[min()];     // returnerer den minste
	}

	@Override
	public T taUt()
	{
		if (tom())
		{
			throw new NoSuchElementException("K�en er tom!");
		}

		int m = min();       // posisjonen til den minste
		T verdi = a[m];      // tar vare p� den minste verdien

		antall--;            // reduserer antallet
		a[m] = a[antall];    // tetter igjen ved � flytte den siste verdien

		a[antall] = null;    // nuller for resirkulering
		return verdi;        // returnerer den minste
	}

	@Override
	public boolean taUt(T verdi)
	{
		int i = 0;
		for (; i < antall; i++)
		{
			if (c.compare(a[i],verdi) == 0) break;
		}

		if (i == antall) return false;  // fant ikke verdi

		antall--;            // reduserer antallet
		a[i] = a[antall];    // tetter igjen ved � flytte den siste verdien

		a[antall] = null;    // nuller for resirkulering
		return true;         // returnerer den minste
	}

	public int antall()
	{
		return antall;
	}

	public boolean tom()
	{
		return antall == 0;
	}

	private int min()  // finner posisjonen til den minste
	{
		int m = 0;
		T minverdi = a[0];

		for (int i = 0; i < antall; i++)
		{
			if (c.compare(a[i], minverdi) < 0)
			{
				m = i;
				minverdi = a[i];
			}
		}

		return m;        // returnerer posisjonen til den minste
	}

	@Override
	public void nullstill()
	{
		while (antall > 0) a[--antall] = null;
	}

}  // UsortertTabellPrioritetsK�
