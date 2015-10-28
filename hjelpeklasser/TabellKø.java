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
		if (fra == til) throw new         // sjekker om k�en er tom
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

}  // TabellK�


