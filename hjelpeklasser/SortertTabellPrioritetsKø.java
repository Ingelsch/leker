package hjelpeklasser;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Created by inge on 19.11.2015.
 */
public class SortertTabellPrioritetsK�<T> implements PrioritetsK�<T>
{
	private T[] a;                       // en usortert tabell
	private int antall;                  // antall verdier i k�en
	private Comparator<? super T> c;     // en komparator

	public SortertTabellPrioritetsK�(int st�rrelse, Comparator<? super T> c)
	{
		a = (T[]) new Object[st�rrelse];   // tabellens startst�rrelse
		antall = 0;
		this.c = c;
	}

	public SortertTabellPrioritetsK�(Comparator<? super T> c)
	{
		this(8, c);  // bruker 8 som startst�rrelse
	}

	public static <T extends Comparable<? super T>> PrioritetsK�<T> naturligOrdenK�()
	{
		return new SortertTabellPrioritetsK�<>(Comparator.naturalOrder());
	}

	@Override
	public void leggInn(T verdi)
	{
		if (antall == a.length)
		{
			a = Arrays.copyOf(a, 2 * antall + 1);
		}

		int i = antall - 1;   //  vi sammenligner og flytter
		for (; i >= 0 && c.compare(verdi, a[i]) > 0; i--)
		{
			a[i + 1] = a[i];
		}
		a[i + 1] = verdi;
		antall++;
	}

	public boolean tom()
	{
		return antall == 0;
	}

	public T kikk()
	{
		if (tom())
		{
			throw new NoSuchElementException("K�en er tom!");
		}
		return a[antall - 1];
	}

	public T taUt()
	{
		if (antall == 0)
		{
			throw new NoSuchElementException("K�en er tom!");
		}

		T minverdi = a[--antall];      // tar vare p� den bakerste
		a[antall] = null;              // klargj�r for resirkulering
		return minverdi;               // returnerer den st�rste
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

	@Override
	public int antall()
	{
		return antall;
	}

	@Override
	public void nullstill()
	{
		while (antall > 0) a[--antall] = null;
	}


}
