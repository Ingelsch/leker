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
		if (fra == til) throw new         // sjekker om køen er tom
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

}  // TabellKø


