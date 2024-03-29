package hjelpeklasser;

/**
 * Created by inge on 18.11.2015.
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

public class TabellStakk<T> implements Stakk<T>
{
	private T[] a;                     // en T-tabell
	private int antall;                // antall objekter p� stakken

	public TabellStakk()               // konstrukt�r - tabellengde 8
	{
		this(8);
	}

	public TabellStakk(int lengde)     // valgfri tabellengde
	{
		if (lengde < 0)
			throw new IllegalArgumentException("Negativ tabellengde!");

		a = (T[])new Object[lengde];     // oppretter tabellen
		antall = 0;                      // stakken er tom
	}

	public void leggInn(T t)
	{
		if (antall == a.length)
			a = Arrays.copyOf(a, antall == 0 ? 1 : 2*antall);   // dobler

		a[antall++] = t;
	}

	public T kikk()
	{
		if (antall == 0)       // sjekker om stakken er tom
			throw new NoSuchElementException("Stakken er tom!");

		return a[antall-1];    // returnerer det �verste objektet
	}

	public T taUt()
	{
		if (antall == 0)       // sjekker om stakken er tom
			throw new NoSuchElementException("Stakken er tom!");

		antall--;             // reduserer antallet

		T temp = a[antall];   // tar var p� det �verste objektet
		a[antall] = null;     // tilrettelegger for resirkulering

		return temp;          // returnerer det �verste objektet
	}

	public boolean tom()
	{
		return antall == 0;
	}

	public int antall()
	{
		return antall;
	}

	public void nullstill()
	{
		for (int i = 0; i < antall; i++) a[i] = null;
		antall = 0;
	}

	public String toString()
	{
		if (antall == 0) return "[]";

		StringBuilder s = new StringBuilder();
		s.append('[');
		s.append(a[antall - 1]);

		for (int i = antall - 2; i >= 0; i--)
		{
			s.append(',').append(' ').append(a[i]);
		}
		s.append(']');

		return s.toString();
	}

	public static <T> void snu(Stakk<T> A)
	{
		Stakk<T> B = new TabellStakk<T>();
		Stakk<T> C = new TabellStakk<T>();

		while (!A.tom()) B.leggInn(A.taUt());
		while (!B.tom()) C.leggInn(B.taUt());
		while (!C.tom()) A.leggInn(C.taUt());
	}

	public static <T> void kopier(Stakk<T> A, Stakk<T> B)
	{
		Stakk<T> C = new TabellStakk<T>();
		while (!A.tom()) C.leggInn(A.taUt());
		while (!C.tom())
		{
			T t = C.taUt();
			B.leggInn(t);
			A.leggInn(t);
		}
	}
}  // class TabellStakk
