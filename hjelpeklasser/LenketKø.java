package hjelpeklasser;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Created by inge on 19.11.2015.
 */
public class LenketK�<T> implements K�<T>
{
	private static final class Node<T>   // en indre nodeklasse
	{
		private T verdi;        // nodens verdi
		private Node<T> neste;  // peker til neste node

		Node(Node<T> neste)     // nodekonstrukt�r
		{
			verdi = null; this.neste = neste;
		}
	}   // class Node

	private Node<T> fra, til;  // fra: f�rst i k�en, til: etter den siste
	private int antall;        // antall i k�en

	private static final int START_ST�RRELSE = 8;

	public LenketK�(int st�rrelse)  // konstrukt�r
	{
		til = fra = new Node<>(null);  // lager den f�rste noden

		Node<T> p = fra;               // en hjelpevariabel
		for (int i = 1; i < st�rrelse; i++)
		{
			p = new Node<>(p);           // lager resten av nodene
		}
		fra.neste = p;                 // for � f� en sirkel

		antall = 0;                    // ingen verdier forel�pig
	}

	public LenketK�()  // standardkonstrukt�r
	{
		this(START_ST�RRELSE);
	}

	@Override
	public boolean leggInn(T verdi)   // null-verdier skal v�re tillatt
	{
		til.verdi = verdi;              // legger inn bakerst

		if (til.neste == fra)           // k�en vil bli full - m� utvides
		{
			til.neste = new Node<>(fra);  // ny node mellom til og fra
		}

		til = til.neste;                // flytter til
		antall++;                       // �ker antallet

		return true;                    // vellykket innlegging
	}

	public T kikk()
	{
		if (tom()) throw new NoSuchElementException("K�en er tom!");
		return fra.verdi;           // returnerer verdien
	}

	public T taUt()
	{
		if (tom()) throw new NoSuchElementException("K�en er tom!");

		T tempverdi = fra.verdi;    // tar vare p� verdien i fra
		fra.verdi = null;           // nuller innholdet i fra

		fra = fra.neste;            // flytter fra
		antall--;                   // reduserer antallet

		return tempverdi;           // returnerer verdien
	}

	public int antall()
	{
		return antall;
	}

	public boolean tom()
	{
		return fra == til;  // eller antall == 0
	}

	@Override
	public void nullstill()  // tar vare p� en del av nodene
	{
		Node<T> p = fra;
		for (int i = 1; i < START_ST�RRELSE; i++)
		{
			p.verdi = null;
			if (p.neste == fra) break;
			p = p.neste;
		}

		Node<T> q = p.neste;
		while (q != fra)
		{
			q.verdi = null;
			q = q.neste;
		}

		p.verdi = null;
		til = p.neste = fra;
		antall = 0;
	}

	public String toString()
	{
		StringJoiner s = new StringJoiner(", ", "[", "]");

		Node<T> p = fra;

		for (int i = 0; i < antall; i++, p = p.neste)
		{
			s.add(p.verdi.toString());
		}

		return s.toString();
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

} // class LenketK�
