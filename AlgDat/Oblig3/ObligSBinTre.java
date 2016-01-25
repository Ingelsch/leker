package AlgDat.Oblig3;

import AlgDat.Oblig2.Liste;
import hjelpeklasser.Kø;
import hjelpeklasser.TabellKø;
import hjelpeklasser.TabellToveiskø;
import hjelpeklasser.Toveiskø;

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
	private static final class Node<T>   // en indre nodeklasse
	{
		private T verdi;                   // nodens verdi
		private Node<T> venstre, høyre;    // venstre og høyre barn
		private Node<T> forelder;          // forelder

		// konstruktør
		private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
		{
			this.verdi = verdi;
			venstre = v;
			høyre = h;
			this.forelder = forelder;
		}

		private Node(T verdi, Node<T> forelder)  // konstruktør
		{
			this(verdi, null, null, forelder);
		}

		@Override
		public String toString()
		{
			return "" + verdi;
		}

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
	public boolean leggInn(T verdi)    // skal ligge i class SBinTre
	{
		Objects.requireNonNull(verdi, "Ulovlig nullverdi!");

		Node<T> p = rot, q = null;               // p starter i roten
		int cmp = 0;                             // hjelpevariabel

		while (p != null)       // fortsetter til p er ute av treet
		{
			q = p;                                 // q er forelder til p
			cmp = comp.compare(verdi, p.verdi);     // bruker komparatoren
			p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
		}

		// p er nå null, dvs. ute av treet, q er den siste vi passerte

		p = new Node<>(verdi, q);                // q er forelder til p

		if (q == null)
		{
			rot = p;                  // p blir rotnode
		}
		else if (cmp < 0)
		{
			q.venstre = p;         // venstre barn til q
		}
		else
		{
			q.høyre = p;                        // høyre barn til q
		}

		antall++;                                // én verdi mer i treet
		return true;                             // vellykket innlegging
	}

	@Override
	public boolean inneholder(T verdi)
	{
		if (verdi == null)
		{
			return false;
		}

		Node<T> p = rot;

		while (p != null)
		{
			int cmp = comp.compare(verdi, p.verdi);
			if (cmp < 0)
			{
				p = p.venstre;
			}
			else if (cmp > 0)
			{
				p = p.høyre;
			}
			else
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean fjern(T verdi)
	{
		if (verdi == null)
		{
			return false;  // treet har ingen nullverdier
		}

		Node<T> p = rot, q = null;   // q skal være forelder til p

		while (p != null)            // leter etter verdi
		{
			int cmp = comp.compare(verdi, p.verdi);      // sammenligner
			if (cmp < 0)
			{
				q = p;
				p = p.venstre;
			}      // går til venstre
			else if (cmp > 0)
			{
				q = p;
				p = p.høyre;
			}   // går til høyre
			else
			{
				break;    // den søkte verdien ligger i p
			}
		}
		if (p == null)
		{
			return false;   // finner ikke verdi
		}

		if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
		{
			Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn

			// endring i forhold til gammel kode
			if (b != null)
			{
				b.forelder = q;
			}

			if (p == rot)
			{
				rot = b;
			}
			else if (p == q.venstre)
			{
				q.venstre = b;
			}
			else
			{
				q.høyre = b;
			}
		}
		else  // Tilfelle 3)
		{
			Node<T> s = p, r = p.høyre;   // finner neste i inorden
			while (r.venstre != null)
			{
				s = r;    // s er forelder til r
				r = r.venstre;
			}

			p.verdi = r.verdi;   // kopierer verdien i r til p

			// endring i forhold til gammel kode
			if (r.høyre != null)
			{
				r.høyre.forelder = s;
			}

			if (s != p)
			{
				s.venstre = r.høyre;
			}
			else
			{
				s.høyre = r.høyre;
			}
		}

		antall--;   // det er nå én node mindre i treet
		return true;
	}

	public int fjernAlle(T verdi)
	{
		int antallFjernet = 0;
		while (fjern(verdi)) antallFjernet++;
		return antallFjernet;
	}

	@Override
	public int antall()
	{
		return antall;
	}

	public int antall(T verdi)
	{
		Node<T> p = rot;
		int antallverdier = 0;

		while (p != null)
		{
			int cmp = comp.compare(verdi, p.verdi);
			if (cmp < 0)
			{
				p = p.venstre;
			}
			else
			{
				if (cmp == 0)
				{
					antallverdier++;
				}
				p = p.høyre;
			}
		}

		return antallverdier;
	}

	@Override
	public boolean tom()
	{
		return antall == 0;
	}

	@Override
	public void nullstill()
	{
		if (!tom())
		{
			nullstill(rot);  // kaller den rekursive metoden
		}
		rot = null;
		antall = 0;
	}

	private static <T> void nullstill(Node<T> p)  // rekursiv hjelpemetode
	{
		if (p.venstre != null)
		{
			nullstill(p.venstre);
			p.venstre = null;
		}
		if (p.høyre != null)
		{
			nullstill(p.høyre);
			p.høyre = null;
		}
		p.verdi = null;
		p.forelder = null;
	}

	private static <T> Node<T> nesteInorden(Node<T> p)
	{
		if (p.høyre != null)
		{
			p = p.høyre;
			while (p.venstre != null) p = p.venstre;
		}
		else
		{
			while (p.forelder != null && p == p.forelder.høyre)
			{
				p = p.forelder;
			}

			p = p.forelder;
		}

		return p;
	}

	@Override
	public String toString()
	{
		if (tom())
		{
			return "[]";
		}

		StringJoiner s = new StringJoiner(", ", "[", "]");

		Node<T> p = rot;
		while (p.venstre != null) p = p.venstre;

		while (p != null)
		{
			s.add(p.verdi.toString());
			p = nesteInorden(p);
		}

		return s.toString();
	}

	public String omvendtString()
	{
		if (tom())
		{
			return "[]";
		}

		Stack<Node<T>> stakk = new Stack<>();
		StringJoiner s = new StringJoiner(", ", "[", "]");

		Node<T> p = rot;
		while (p.høyre != null)
		{
			stakk.push(p);
			p = p.høyre;
		}

		while (true)
		{
			s.add(p.verdi.toString());

			if (p.venstre != null)
			{
				p = p.venstre;
				while (p.høyre != null)
				{
					stakk.push(p);
					p = p.høyre;
				}
			}
			else if (!stakk.isEmpty())
			{
				p = stakk.pop();
			}
			else
			{
				break;
			}
		}

		return s.toString();
	}

	public String høyreGren()
	{
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (!tom())
		{
			Node<T> p = rot;
			while (true)
			{
				s.add(p.verdi.toString());
				if (p.høyre != null)
				{
					p = p.høyre;
				}
				else if (p.venstre != null)
				{
					p = p.venstre;
				}
				else
				{
					break;
				}
			}
		}
		return s.toString();
	}

	public String lengstGren()
	{
		// den lengste grenen går til den noden som kommer
		// til slutt i nivåorden

		if (tom())
		{
			return "[]";
		}
		Kø<Node<T>> kø = new TabellKø<>();

		Node<T> p = rot;
		kø.leggInn(p);

		while (!kø.tom())
		{
			p = kø.taUt();
			if (p.venstre != null)
			{
				kø.leggInn(p.venstre);
			}
			if (p.høyre != null)
			{
				kø.leggInn(p.høyre);
			}
		}

		return gren(p);  // bruker en hjelpemetode
	}

	// hjelpemetode - lager tegnstreng av en gren
	private static <T> String gren(Node<T> p)
	{
		Stack<T> s = new Stack<>();
		while (p != null)
		{
			s.push(p.verdi);
			p = p.forelder;
		}
		return s.toString();
	}

	// rekursiv hjelpemetode som traverserer treet
	private static <T> void grener(Node<T> p, Liste<String> liste)
	{
		if (p.venstre == null && p.høyre == null)
		{
			liste.leggInn(gren(p));
		}
		if (p.venstre != null)
		{
			grener(p.venstre, liste);
		}
		if (p.høyre != null)
		{
			grener(p.høyre, liste);
		}
	}

	public String[] grener()
	{
		Liste<String> liste = new Liste<String>()
		{
			@Override
			public boolean leggInn(String verdi)
			{
				return false;
			}

			@Override
			public void leggInn(int indeks, String verdi)
			{

			}

			@Override
			public boolean inneholder(String verdi)
			{
				return false;
			}

			@Override
			public String hent(int indeks)
			{
				return null;
			}

			@Override
			public int indeksTil(String verdi)
			{
				return 0;
			}

			@Override
			public String oppdater(int indeks, String verdi)
			{
				return null;
			}

			@Override
			public boolean fjern(String verdi)
			{
				return false;
			}

			@Override
			public String fjern(int indeks)
			{
				return null;
			}

			@Override
			public int antall()
			{
				return 0;
			}

			@Override
			public boolean tom()
			{
				return false;
			}

			@Override
			public void nullstill()
			{

			}

			@Override
			public Iterator<String> iterator()
			{
				return null;
			}
		};
		if (!tom())
		{
			grener(rot, liste);
		}
		String[] s = new String[liste.antall()];
		int i = 0;
		for (String t : liste)
		{
			s[i++] = t;
		}
		return s;
	}

	// Alternativ og mer effektiv måte
	/*private void grener2(Node<T> p, Liste<String> liste, Toveiskø<T> gren)
	{
		gren.leggInnSist(p.verdi);

		if (p.venstre != null)
		{
			grener2(p.venstre, liste, gren);
		}
		if (p.høyre != null)
		{
			grener2(p.høyre, liste, gren);
		}
		if (p.høyre == null && p.venstre == null)
		{
			liste.leggInn(gren.toString());
		}

		gren.taUtSist();     // fjerner den siste
	}

	public String[] grener2()
	{
		Liste<String> liste = new TabellListe<>();
		Toveiskø<T> gren = new TabellToveiskø<>();
		if (!tom())
		{
			grener2(rot, liste, gren);
		}
		String[] s = new String[liste.antall()];
		int i = 0;
		for (String t : liste)
		{
			s[i++] = t;
		}
		return s;
	}*/

	public String bladnodeverdier()
	{
		if (tom())
		{
			return "[]";
		}
		StringJoiner s = new StringJoiner(", ", "[", "]");
		bladnodeverdier(rot, s);
		return s.toString();
	}

	private static <T> void bladnodeverdier(Node<T> p, StringJoiner s)
	{
		if (p.venstre == null && p.høyre == null)
		{
			s.add(p.verdi.toString());
		}
		if (p.venstre != null)
		{
			bladnodeverdier(p.venstre, s);
		}
		if (p.høyre != null)
		{
			bladnodeverdier(p.høyre, s);
		}
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
			if (p == null)
			{
				return;
			}

			while (true)
			{
				if (p.venstre != null)
				{
					p = p.venstre;
				}
				else if (p.høyre != null)
				{
					p = p.høyre;
				}
				else
				{
					break;  // p er en bladnode
				}
			}
		}

		@Override
		public boolean hasNext()
		{
			return p != null;
		}

		@Override
		public T next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException("Ikke flere verdier!");
			}

			removeOK = true;

			q = p;

			while (true)
			{
				p = nesteInorden(p);
				if (p == null || (p.venstre == null && p.høyre == null))
				{
					break;
				}
			}

			return q.verdi;
		}

		@Override
		public void remove()
		{
			if (!removeOK)
			{
				throw
						new IllegalStateException("Ulovlig kall på remove()!");
			}

			removeOK = false;

			Node<T> f = q.forelder;

			if (f == null)
			{
				rot = null;  // q er rotnoden
			}
			else if (q == f.venstre)    // q er venstre barn til sin forelder
			{
				f.venstre = null;
			}
			else
			{
				f.høyre = null;        // q er høyre barn til sin forelder
			}

			antall--;  // en verdi er fjernet
		}

	} // BladnodeIterator

} // ObligSBinTre
