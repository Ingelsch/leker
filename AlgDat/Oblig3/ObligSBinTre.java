package AlgDat.Oblig3;

/**
 * Created by inge on 04.11.2015.
 */
import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
	ObligSBinTre<String> stringObligSBinTre = new ObligSBinTre<>(Comparator.<String>naturalOrder());
	ObligSBinTre<Integer> integerObligSBinTre = new ObligSBinTre<>(Comparator.<Integer>naturalOrder());
	ObligSBinTre<Character> characterObligSBinTre = new ObligSBinTre<>(Comparator.<Character>naturalOrder());

	private static final class Node<T>   // en indre nodeklasse
	{
		private T verdi;                   // nodens verdi
		private Node<T> venstre, høyre;    // venstre og høyre barn
		private Node<T> forelder;          // forelder

		// konstruktør
		private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
		{
			this.verdi = verdi;
			venstre = v; høyre = h;
			this.forelder = forelder;
		}

		private Node(T verdi, Node<T> forelder)  // konstruktør
		{
			this(verdi, null, null, forelder);
		}

		@Override
		public String toString(){ return "" + verdi;}

	} // class Node

	private Node<T> rot;                            // peker til rotnoden
	private int antall;                             // antall noder

	private final Comparator<? super T> comp;       // komparator

	public ObligSBinTre(Comparator<? super T> c)    // konstruktør
	{
		rot = null;
		antall = 0;
		comp = c;
	}

	@Override
	public boolean leggInn(T verdi)
	/*1. Nodeklassen Node i ObligSBinTre har i tillegg til pekere til venstre og høyre barn,
	en peker til nodens forelder. Denne må få riktig verdi ved hver innlegging. Spesielt skal
	forelder være null i rotnoden. Lag metoden public boolean leggInn(T verdi). Der kan du kopiere
	Programkode 5.2 3 a), men i tillegg må du gjøre de endringene som trengs for at pekeren forelder
	får korrekt verdi i hver node.
	Teknikken med en forelder-peker i hver node brukes f.eks. i klassen TreeSet i java.util.*/
	{
		Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

		Node<T> p = rot, q = null, forelder = null;               // p starter i roten
		int cmp = 0;                             // hjelpevariabel

		while (p != null)       // fortsetter til p er ute av treet
		{
			q = forelder = p;                                 // q er forelder til p
			cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
			p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
		}

		// p er nå null, dvs. ute av treet, q er den siste vi passerte

		p = new Node<>(verdi,null,null,q);                   // oppretter en ny node

		if (q == null) rot = p;                  // p blir rotnode
		else if (cmp < 0) q.venstre = p;         // venstre barn til q
		else q.høyre = p;                        // høyre barn til q

		antall++;                                // én verdi mer i treet
		return true;                             // vellykket innlegging
	}

	@Override
	public boolean inneholder(T verdi)
	{
		if (verdi == null) return false;

		Node<T> p = rot;

		while (p != null)
		{
			int cmp = comp.compare(verdi, p.verdi);
			if (cmp < 0) p = p.venstre;
			else if (cmp > 0) p = p.høyre;
			else return true;
		}

		return false;
	}

	@Override
	public boolean fjern(T verdi)
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	public int fjernAlle(T verdi)
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	@Override
	public int antall()
	{
		return antall;
	}

	public int antall(T verdi)
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	@Override
	public boolean tom()
	{
		return antall == 0;
	}

	@Override
	public void nullstill()
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	private static <T> Node<T> nesteInorden(Node<T> p)
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	@Override
	public String toString()
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	public String omvendtString()
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	public String høyreGren()
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	public String lengstGren()
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	public String[] grener()
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	public String bladnodeverdier()
	{
		throw new UnsupportedOperationException("Ikke kodet ennå!");
	}

	@Override
	public Iterator<T> iterator()
	{
		return new BladnodeIterator();
	}

	private class BladnodeIterator implements Iterator<T>
	{
		private Node<T> p = rot, q = null;
		private boolean removeOK = false;

		private BladnodeIterator()  // konstruktør
		{
			throw new UnsupportedOperationException("Ikke kodet ennå!");
		}

		@Override
		public boolean hasNext()
		{
			return p != null;  // Denne skal ikke endres!
		}

		@Override
		public T next()
		{
			throw new UnsupportedOperationException("Ikke kodet ennå!");
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException("Ikke kodet ennå!");
		}

	} // BladnodeIterator

} // ObligSBinTre