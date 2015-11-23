package hjelpeklasser;

/**
 * Created by inge on 18.11.2015.
 */

import java.util.*;

public class LenketToveisk�<T> implements Toveisk�<T>
{
	private static final class Node<T>     // en indre nodeklasse
	{
		T verdi;                             // nodens verdi
		Node<T> forrige;                     // peker til forrige node
		Node<T> neste;                       // peker til neste node

		Node(T verdi, Node<T> forrige, Node<T> neste)  // konstrukt�r
		{
			this.verdi = verdi;
			this.forrige = forrige;
			this.neste = neste;
		}
	} // class Node

	private Node<T> start;                 // k�ens start
	private Node<T> slutt;                 // k�ens slutt
	private int antall;                    // antall i k�en

	public LenketToveisk�()                // standardkonstrukt�r
	{
		start = slutt = null;
		antall = 0;
	}

	public void leggInnF�rst(T t)
	{
		if (antall == 0)   // k�en er tom
		{
			start = slutt = new Node<T>(t, null, null);
		}
		else
		{
			start = start.forrige = new Node<T>(t, null, start);
		}

		antall++;
	}

	public void leggInnSist(T t)
	{
		if (antall == 0)   // k�en er tom
		{
			start = slutt = new Node<T>(t, null, null);
		}
		else
		{
			slutt = slutt.neste = new Node<T>(t, slutt, null);
		}

		antall++;
	}

	public T kikkF�rst()
	{
		if (antall == 0)  // k�en er tom
		{
			throw new NoSuchElementException("K�en er tom!");
		}

		return start.verdi;
	}

	public T kikkSist()
	{
		if (antall == 0)  // k�en er tom
		{
			throw new NoSuchElementException("K�en er tom!");
		}

		return slutt.verdi;
	}

	public T taUtF�rst()
	{
		if (antall == 0)  // k�en er tom
		{
			throw new NoSuchElementException("K�en er tom!");
		}

		T temp = start.verdi;
		start.verdi = null;
		start = start.neste;

		if (antall == 1)
		{
			slutt = null;
		}
		else
		{
			start.forrige = null;
		}

		antall--;
		return temp;
	}

	public T taUtSist()
	{
		if (antall == 0)  // k�en er tom
		{
			throw new NoSuchElementException("K�en er tom!");
		}

		T temp = slutt.verdi;
		slutt.verdi = null;
		slutt = slutt.forrige;

		if (antall == 1)
		{
			start = null;
		}
		else
		{
			slutt.neste = null;
		}

		antall--;
		return temp;
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
		Node<T> p = start;

		while (p != null)
		{
			Node<T> q = p.neste;
			p.neste = p.forrige = null;

			p.verdi = null;
			p = q;
		}
		antall = 0;
		start = slutt = null;
	}

	public String toString()
	{
		if (antall == 0)
		{
			return "[]";
		}
		StringBuilder s = new StringBuilder();
		Node<T> p = start;
		s.append('[');
		s.append(p.verdi);
		p = p.neste;

		while (p != null)
		{
			s.append(", ");
			s.append(p.verdi);
			p = p.neste;
		}
		s.append(']');

		return s.toString();
	}
} // class LenketToveisk�
