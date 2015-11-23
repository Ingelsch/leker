package hjelpeklasser;

/**
 * Created by inge on 19.11.2015.
 */
import java.util.*;

public class UsortertLenketPrioritetsKø<T> implements PrioritetsKø<T>
{
	private static final class Node<T>
	{
		private T verdi;
		private Node<T> neste;

		private Node(T verdi, Node<T> neste)
		{
			this.verdi = verdi; this.neste = neste;
		}
	} // class Node

	private Node<T> hode;
	private Comparator<? super T> c;
	private int antall;

	public UsortertLenketPrioritetsKø(Comparator<? super T> c)
	{
		this.c = c;
		hode = null;
		antall = 0;
	}

	public void leggInn(T verdi)
	{
		hode = new Node<T>(verdi,hode);
		antall++;
	}

	public T kikk()
	{
		if (antall == 0) throw new NoSuchElementException();

		T maksverdi = hode.verdi;

		for (Node<T> p = hode.neste; p != null; p = p.neste)
			if (c.compare(p.verdi,maksverdi) > 0)
				maksverdi = p.verdi;

		return maksverdi;
	}

	public T taUt()
	{
		if (antall == 0) throw new NoSuchElementException();

		T maksverdi = hode.verdi;
		Node<T> q = null;

		for (Node<T> p = hode; p.neste != null; p = p.neste)
			if (c.compare(p.neste.verdi,maksverdi) > 0)
			{
				maksverdi = p.neste.verdi;
				q = p;
			}

		if (q == null) hode = hode.neste;
		else q.neste = q.neste.neste;

		antall--;

		return maksverdi;
	}

	@Override
	public boolean taUt(T verdi)
	{
		return false;
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
		hode = null;
		antall = 0;
	}

	public String toString()
	{
		if (antall == 0) return "[]";

		StringBuilder s = new StringBuilder();
		s.append('['); s.append(hode.verdi);

		for (Node<T> p = hode.neste; p != null; p = p.neste)
		{
			s.append(','); s.append(' '); s.append(p.verdi);
		}
		s.append(']');

		return s.toString();
	}

}  // class UsortertLenketPrioritetsKø
