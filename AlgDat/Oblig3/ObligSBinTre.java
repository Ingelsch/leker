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

		Node<T> p = rot, q = null;               // p starter i roten
		int cmp = 0;                             // hjelpevariabel

		while (p != null)       // fortsetter til p er ute av treet
			{
				q = p;                                 // q er forelder til p
				cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
				p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
			}

		// p er nå null, dvs. ute av treet, q er den siste vi passerte

		p = new Node<>(verdi);                  // oppretter en ny node

		if (q == null) rot = p;                  // p blir rotnode
		else if (cmp < 0) q.venstre = p;         // venstre barn til q
		else q.høyre = p;                        // høyre barn til q

		if (q != null)
			{
				p.forelder = q;
			}
		else
			{
				p.forelder = null;
			}

		antall++;                                // én verdi mer i treet
		return true;                             // vellykket innlegging
	}

	@Override
	/*avgjør om en verdi ligger i treet eller ikke*/
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
	/*returnerer antall verdier i treet*/
	public int antall()
	{
		return antall;
	}

	/*skal returnere antall forekomster av verdi i treet. Det er
	tillatt med duplikater og det betyr at en verdi kan forekomme
	flere ganger. Hvis verdi ikke er i treet, skal metoden returnere 0.
	 Lag så trær der du legger inn flere like verdier og sjekk at
	 metoden din gir korrekt svar.*/
	public int antall(T verdi)
	{
		if (verdi == null)
			{
				return 0;
			}

		int a=0; //oppstartsverdi

		Node<T> p = rot;
		while (p != null)
			{
				int cmp = comp.compare(verdi, p.verdi);
				if (cmp < 0)
					{
						p = p.venstre; //går til venstre
					}
				else if (cmp > 0)
					{
						p = p.høyre; //går til høyre
					}
				else
					{
						a++;
						p = p.høyre;
					}
			}

		return a;
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

	/*Du kan ta som gitt at parameteren p ikke er null.
	Den skal returnere den noden som kommer etter p i inorden.
	Hvis p er den siste i inorden, skal metoden returne null.
	Husk at hvis p har et høyre subtre, så vil den neste i inorden
	være den noden som ligger lengst ned til venstre i det subtreet.
	Hvis p ikke har et høyre subtre og p ikke er den siste, vil den neste
	i inorden være høyere opp i treet.
	Den finner du ved hjelp forelder-pekerne.*/
	private static <T> Node<T> nesteInorden(Node<T> p)
	{
		if (p == null)
			{
				return null;
			}
		if (p.høyre != null)
			{
				return finnLaveste(p.høyre);
			}
		Node x = p.forelder;
		Node y = p;
		while (y != null && x == y.høyre)
		{
			x = y;
			y = y.forelder;
		}
		return y;

	}

	/* hjelpemetode til nesteInOrden*/
	private static<T> Node<T> finnLaveste(Node<T> p){
		if (p == null)
			return null;
		if (p.venstre != null)
			return finnLaveste(p.venstre);
		return p;
	}

	@Override
	/*Den skal returnere en tegnstreng med treets verdier i inorden.
	Verdiene skal rammes inn av [ og ]. Mellom verdiene (hvis det er flere)
	skal det være komma og mellomrom. Hvis treet er tomt, skal strengen
	inneholde "[]". Du skal bruke verken rekursjon eller hjelpestakk.
	Du skal bruke hjelpemetoden nesteInorden(). Start med (en egen while-løkke)
	for å finne den første noden p i inorden. Deretter vil gjentatte kall (en løkke)
	  på setningen:
	 p = nesteInorden(p); gi den neste, osv. til p blir null.*/
	public String toString()
	{
		StringJoiner s = new StringJoiner(", ","[ "," ]");
		Node p = rot;
		if (p != null)
			{
				while (p.venstre != null)
					{
						p = finnLaveste(p);

					}

			}
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