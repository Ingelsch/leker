package hjelpeklasser;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Created by inge on 19.11.2015.
 */
public class LenketKø<T> implements Kø<T>
{
	private static final class Node<T>   // en indre nodeklasse
	{
		private T verdi;        // nodens verdi
		private Node<T> neste;  // peker til neste node

		Node(Node<T> neste)     // nodekonstruktør
		{
			verdi = null; this.neste = neste;
		}
	}   // class Node

	private Node<T> fra, til;  // fra: først i køen, til: etter den siste
	private int antall;        // antall i køen

	private static final int START_STØRRELSE = 8;

	public LenketKø(int størrelse)  // konstruktør
	{
		til = fra = new Node<>(null);  // lager den første noden

		Node<T> p = fra;               // en hjelpevariabel
		for (int i = 1; i < størrelse; i++)
		{
			p = new Node<>(p);           // lager resten av nodene
		}
		fra.neste = p;                 // for å få en sirkel

		antall = 0;                    // ingen verdier foreløpig
	}

	public LenketKø()  // standardkonstruktør
	{
		this(START_STØRRELSE);
	}

	@Override
	public boolean leggInn(T verdi)   // null-verdier skal være tillatt
	{
		til.verdi = verdi;              // legger inn bakerst

		if (til.neste == fra)           // køen vil bli full - må utvides
		{
			til.neste = new Node<>(fra);  // ny node mellom til og fra
		}

		til = til.neste;                // flytter til
		antall++;                       // øker antallet

		return true;                    // vellykket innlegging
	}

	public T kikk()
	{
		if (tom()) throw new NoSuchElementException("Køen er tom!");
		return fra.verdi;           // returnerer verdien
	}

	public T taUt()
	{
		if (tom()) throw new NoSuchElementException("Køen er tom!");

		T tempverdi = fra.verdi;    // tar vare på verdien i fra
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
	public void nullstill()  // tar vare på en del av nodene
	{
		Node<T> p = fra;
		for (int i = 1; i < START_STØRRELSE; i++)
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

} // class LenketKø
