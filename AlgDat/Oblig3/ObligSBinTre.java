//Inge L. Schiager s198749 INFORMATIK14HA

package AlgDat.Oblig3;

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
	private static final class Node<T>   // en indre nodeklasse
	{
		private T verdi;                   // nodens verdi
		private Node<T> venstre, h�yre;    // venstre og h�yre barn
		private Node<T> forelder;          // forelder

		// konstrukt�r
		private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
		{
			this.verdi = verdi;
			venstre = v;
			h�yre = h;
			this.forelder = forelder;
		}

		private Node(T verdi, Node<T> forelder)  // konstrukt�r
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
	private int endringer;

	private final Comparator<? super T> comp;       // komparator

	public ObligSBinTre(Comparator<? super T> c)    // konstrukt�r
	{
		rot = null;
		antall = 0;
		comp = c;
		endringer = 0;

	}

	@Override
	public boolean leggInn(T verdi)
	/*1. Nodeklassen Node i ObligSBinTre har i tillegg til pekere til venstre og h�yre barn,
	en peker til nodens forelder. Denne m� f� riktig verdi ved hver innlegging. Spesielt skal
	forelder v�re null i rotnoden. Lag metoden public boolean leggInn(T verdi). Der kan du kopiere
	Programkode 5.2 3 a), men i tillegg m� du gj�re de endringene som trengs for at pekeren forelder
	f�r korrekt verdi i hver node.
	Teknikken med en forelder-peker i hver node brukes f.eks. i klassen TreeSet i java.util.*/
	{
		Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

		Node<T> p = rot, q = null;               // p starter i roten
		int cmp = 0;                             // hjelpevariabel

		while (p != null)       // fortsetter til p er ute av treet
		{
			q = p;                                 // q er forelder til p
			cmp = comp.compare(verdi, p.verdi);     // bruker komparatoren
			p = cmp < 0 ? p.venstre : p.h�yre;     // flytter p
		}

		// p er n� null, dvs. ute av treet, q er den siste vi passerte

		p = new Node<>(verdi, q);                  // oppretter en ny node

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
			q.h�yre = p;                        // h�yre barn til q
		}

		endringer++;
		antall++;                                // �n verdi mer i treet
		return true;                             // vellykket innlegging
	}

	@Override
	/*2. avgj�r om en verdi ligger i treet eller ikke*/
	public boolean inneholder(T verdi)
	{

		Objects.requireNonNull(verdi);

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
				p = p.h�yre;
			}
			else
			{
				return true;
			}
		}

		return false;
	}

	@Override
	/*5. kopier Programkode 5.2 8 d), men i tillegg m� du gj�re de
	endringene som trengs for at pekeren forelder f�r korrekt verdi i alle
    noder etter en fjerning. */
	public boolean fjern(T verdi)
	{
		if (verdi == null)
		{
			return false;  // treet har ingen nullverdier
		}

		Node<T> p = rot, q = null;   // q skal v�re forelder til p

		while (p != null)            // leter etter verdi
		{
			int cmp = comp.compare(verdi, p.verdi);      // sammenligner

			if (cmp < 0)
			{
				q = p;
				p = p.venstre;
			}      // g�r til venstre
			else if (cmp > 0)
			{
				q = p;
				p = p.h�yre;
			}   // g�r til h�yre
			else
			{
				break;    // den s�kte verdien ligger i p
			}
		}
		if (p == null)
		{
			return false;   // finner ikke verdi
		}

		if (p.venstre == null || p.h�yre == null)  // Tilfelle 1) og 2)
		{
			Node<T> b = (p.venstre != null) ? p.venstre : p.h�yre;  // b for barn
			if (p == rot)
			{
				rot = b;
				if (b != null)
				{
					b.forelder = null;
				}
			}
			else if (p == q.venstre)
			{
				if (b != null)
				{
					b.forelder = q;
				}
				q.venstre = b;
			}
			else
			{
				if (b != null)
				{
					b.forelder = q;
				}
				q.h�yre = b;
			}
		}
		else  // Tilfelle 3)
		{
			Node<T> s = p, r = p.h�yre;   // finner neste i inorden
			while (r.venstre != null)
			{
				s = r;    // s er forelder til r
				r = r.venstre;
			}

			p.verdi = r.verdi;   // kopierer verdien i r til p

			if (s != p)
			{
				s.venstre = r.h�yre;
				if (s.venstre != null)
				{
					s.venstre.forelder = s;
				}
			}
			else
			{
				s.h�yre = r.h�yre;
				if (p.h�yre != null)
				{
					p.h�yre.forelder = p;
				}
			}
		}

		antall--;   // det er n� �n node mindre i treet
		return true;
	}

	/*5. Den skal fjerne alle forekomstene av verdi i treet. Husk at duplikater er tillatt. Dermed kan en og samme verdi
	ligge flere steder i treet.
	Metoden skal returnere antallet som ble fjernet. Hvis treet er tomt, skal 0 returneres. */
	public int fjernAlle(T verdi)
	{
		int i = 0;
		boolean fjernet = true;
		while (fjernet)
		{
			if (fjern(verdi))
			{
				i++;
			}
			else
			{
				fjernet = false;
			}
		}
		return i;
	}

	@Override
	/*2. returnerer antall verdier i treet*/
	public int antall()
	{
		return antall;
	}

	/*2. skal returnere antall forekomster av verdi i treet. Det er
	tillatt med duplikater og det betyr at en verdi kan forekomme
	flere ganger. Hvis verdi ikke er i treet, skal metoden returnere 0.
	 Lag s� tr�r der du legger inn flere like verdier og sjekk at
	 metoden din gir korrekt svar.*/
	public int antall(T verdi)
	{
		if (verdi == null)
		{
			return 0;
		}

		int a = 0; //oppstartsverdi

		Node<T> p = rot;
		while (p != null)
		{
			int cmp = comp.compare(verdi, p.verdi);
			if (cmp < 0)
			{
				p = p.venstre; //g�r til venstre
			}
			else if (cmp > 0)
			{
				p = p.h�yre; //g�r til h�yre
			}
			else
			{
				a++;
				p = p.h�yre;
			}
		}

		return a;
	}

	@Override
	public boolean tom()
	{
		return antall == 0;
	}

	/*5. Den skal traversere treet i en eller annen rekkef�lge og s�rge
	for at samtlige pekere og nodeverdier i treet blir nullet.*/
	@Override
	public void nullstill()
	{
		if (!tom())
		{
			nullstill(rot);  // nullstiller
		}
		rot = null;
		antall = 0;      // treet er n� tomt
	}

	private void nullstill(Node<T> p)
	{
		if (p.venstre != null)
		{
			nullstill(p.venstre);      // venstre subtre
			p.venstre = null;          // nuller peker
		}
		if (p.h�yre != null)
		{
			nullstill(p.h�yre);        // h�yre subtre
			p.h�yre = null;            // nuller peker
		}
		p.verdi = null;              // nuller verdien
	}

	/*3. Du kan ta som gitt at parameteren p ikke er null.
	Den skal returnere den noden som kommer etter p i inorden.
	Hvis p er den siste i inorden, skal metoden returne null.
	Husk at hvis p har et h�yre subtre, s� vil den neste i inorden
	v�re den noden som ligger lengst ned til venstre i det subtreet.
	Hvis p ikke har et h�yre subtre og p ikke er den siste, vil den neste
	i inorden v�re h�yere opp i treet.
	Den finner du ved hjelp forelder-pekerne.*/
	private static <T> Node<T> nesteInorden(Node<T> p)
	{
		if (p.h�yre != null)
		{
			p = p.h�yre;
			while (p.venstre != null)
			{
				p = p.venstre;
			}
		}
		else
		{
			while (p.forelder != null && p.forelder.h�yre == p)
			{
				p = p.forelder;
			}
			p = p.forelder;
		}
		return p;

	}

	@Override
	/*3. Den skal returnere en tegnstreng med treets verdier i inorden.
	Verdiene skal rammes inn av [ og ]. Mellom verdiene (hvis det er flere)
	skal det v�re komma og mellomrom. Hvis treet er tomt, skal strengen
	inneholde "[]". Du skal bruke verken rekursjon eller hjelpestakk.
	Du skal bruke hjelpemetoden nesteInorden(). Start med (en egen while-l�kke)
	for � finne den f�rste noden p i inorden. Deretter vil gjentatte kall (en l�kke)
	  p� setningen:
	 p = nesteInorden(p); gi den neste, osv. til p blir null.*/
	public String toString()
	{
		StringJoiner s = new StringJoiner(", ", "[", "]");
		Node<T> p = rot;
		if (p != null)
		{
			while (p.venstre != null)
			{
				p = p.venstre;
			}
			s.add(p.verdi.toString());
			while (true)
			{
				p = nesteInorden(p);
				if (p == null)
				{
					break;
				}
				s.add(p.verdi.toString());
			}
		}

		return s.toString();
	}

	/*4. Den skal gj�re som metoden toString(), men med verdiene i motsatt rekkef�lge.
	 Du skal l�se dette ved � traversere treet i omvendt inorden (dvs. motsatt vei
	 av inorden) iterativt. Her skal du bruke en hjelpestakk. F.eks. en
	 TabellStakk eller en stakk fra java.util (f.eks. en ArrayDeque). Koden din
	 skal ikke noe sted benytte forelderpekerne. Med andre ord skal koden din
	 ogs� kunne virke i et bin�rtre uten forelderpekere. Ta f.eks. utgangspunkt
	 i den iterative inorden-metoden fra Programkode 5.1 10 e). Men i denne
	 oppgaven skal traverseringen g� motsatt vei.
	Det betyr at du m� gj�re noen endringer for � f� det til � g� den motsatte
	veien.*/
	public String omvendtString()
	{
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (!tom())            // tomt tre
		{

			Stack<Node<T>> stakk = new Stack<>();
			Node<T> p = rot;   // starter i roten og g�r til venstre
			for (; p.h�yre != null; p = p.h�yre)
			{
				stakk.push(p);
			}

			while (true)
			{
				s.add(p.verdi.toString());
				if (p.venstre != null)
				{
					for (p = p.venstre; p.h�yre != null; p = p.h�yre)
					{
						stakk.push(p);
					}
				}
				else if (!stakk.isEmpty())
				{
					p = stakk.pop();   // p.h�yre == null, henter fra stakken
				}
				else
				{
					break;          // stakken er tom - vi er ferdig
				}

			} // while
		}
		return s.toString();
	}

	/*6. skal returnere en tegnstreng med grenens verdier. Tegnstrengen skal som vanlig v�re �innrammet� av
	hakeparentesene [ og ]. Verdiene skal (hvis det er flere) v�re adskilt med komma og mellomrom. Hvis treet
	er tomt skal kun "[]" returneres.
	a) skal gi den grenen som ender i den bladnoden som ligger lengst til h�yre i treet.*/
	public String h�yreGren()
	{
		StringJoiner s = new StringJoiner(", ", "[", "]");
		if (!tom())
		{
			Node<T> p = rot;
			while (p.h�yre != null)
			{
				p = p.h�yre;
				s.add(p.verdi.toString());
				while (p.h�yre != null)
				{
					p = p.h�yre;
					s.add(p.verdi.toString());
				}
			}
			s.add(p.verdi.toString());
		}
		return s.toString();
	}

	private static class BladNode<T>
	{
		private int niv� = 0;
		private T verdi = null;
	}

	private static <T> void lengstGren(Node<T> p, int niv�, BladNode<T> blad)
	{
		if ((p.venstre == null) && (p.h�yre == null))
		{
			if (niv� >= blad.niv�)
			{
				blad.niv� = niv�;
				blad.verdi = p.verdi;
			}
		}
		if (p.venstre != null)
		{
			lengstGren(p.venstre, niv� + 1, blad);
		}
		if (p.h�yre != null)
		{
			lengstGren(p.h�yre, niv� + 1, blad);
		}
	}

	/*6. skal returnere en tegnstreng med grenens verdier. Tegnstrengen skal som vanlig v�re �innrammet� av
	hakeparentesene [ og ]. Verdiene skal (hvis det er flere) v�re adskilt med komma og mellomrom. Hvis treet
	er tomt skal kun "[]" returneres.
	b) skal gi den lengste grenen, dvs. grenen som ender i den bladnoden som ligger lengst ned i treet. Hvis det
	er flere lengste grener, skal den av dem som ligger lengst til h�yre returneres (Hint om lengst gren: Er det
	noen av traverseringene (pre-, in-, post- eller niv�orden) som stopper i den aktuelle noden?). Pass p� at hvis
	treet har kun �n gren, s� er denne grenen b�de h�yre gren og lengste gren. Hvis treet har kun �n node
	(kun rotnoden), er dette ogs� en gren.*/
	public String lengstGren()
	{
		if (tom())
		{
			return "[]";
		}
		BladNode<T> blad = new BladNode();
		lengstGren(rot, 0, blad);
		return gren(blad.verdi);
	}

	private String gren(T bladnodeverdi)
	{
		Node<T> p = rot;
		StringJoiner s = new StringJoiner(", ", "[", "]");

		while (p != null)
		{
			s.add(p.verdi.toString());
			p = comp.compare(bladnodeverdi, p.verdi) < 0 ? p.venstre : p.h�yre;
		}
		return s.toString();
	}

	/*7. Den skal returnere en String-tabell som inneholder (som tabellelementer) alle grenene i treet i rekkef�lge
	fra venstre mot h�yre. Hvis treet er tomt skal det returneres en tom tabell. */
	public String[] grener()
	{
		if (tom())
		{
			return new String[0];
		}
		String[] stringTabell = new String[1];
		StringJoiner s;
		ArrayDeque<Node<T>> que = new ArrayDeque();
		ArrayDeque<Node<T>> nodegrenque = new ArrayDeque();

		boolean tomListe = false;

		Node<T> p = rot;
		int i = 0;

		while (!tomListe)
		{
			s = new StringJoiner(", ", "[", "]");

			while (p.venstre != null || p.h�yre != null)
			{
				if (p.venstre != null)
				{
					if (p.h�yre != null)
					{
						que.add(p.h�yre);
					}
					p = p.venstre;
				}
				else if (p.h�yre != null)
				{
					p = p.h�yre;
				}
			}

			while (p != null)
			{
				nodegrenque.add(p);
				p = p.forelder;
			}

			while (!nodegrenque.isEmpty())
				s.add(nodegrenque.pollLast().toString());

			if (stringTabell[stringTabell.length - 1] != null)
			{
				stringTabell = Arrays.copyOf(stringTabell, stringTabell.length + 1);
			}
			stringTabell[i++] = s.toString();

			if (!que.isEmpty())
			{
				p = que.pollLast();
			}
			else
			{
				tomListe = true;
			}
		}
		return stringTabell;
	}

	/*Den skal returnere en tegnstreng med verdiene i bladnodene. Tegnstrengen skal se ut som vanlig
	(med [ og ] og med komma og mellomrom). Her skal du bruke en rekursiv hjelpemetode som traverserer treet.
	Bladnodeverdiene skal i tegnstrengen st� i rekkef�lge fra venstre mot h�yre. */
	public String bladnodeverdier()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node p = rot;
		finnBladnode(p, sb);
		sb.append("]");
		return sb.toString();
	}

	private void finnBladnode(Node p, StringBuilder sb)
	{
		if (p == null)
		{
			return;
		}

		if (p.venstre == null && p.h�yre == null)
		{
			if (!sb.toString().equals("["))
			{
				sb.append(",").append(" ").append(p);
			}
			else
			{
				sb.append(p);
			}
		}
		finnBladnode(p.venstre, sb);
		finnBladnode(p.h�yre, sb);
	}

	@Override
	public Iterator<T> iterator()
	{
		return new BladnodeIterator();
	}

	/*9. Klassen BladnodeIterator er satt opp med instansvariabler og konstrukt�r. Det er mulig � l�se alt det
	sp�rres om uten flere intansvariabler i iteratorklassen. Men hvis du skulle mene at det er lettere � f� til
	dette ved � bruke en hjelpestakk, m� du gjerne gj�re det. Metoden iterator() er ferdigkodet.*/
	private class BladnodeIterator implements Iterator<T>
	{
		private Node<T> p = rot, q = null;
		private boolean removeOK = false;
		private Node<T> blad;
		private int anInt = endringer;

		/*9. Konstrukt�ren skal s�rge for � flytte pekeren p til f�rste bladnode, dvs. til den som er lengst til venstre
		hvis det er flere bladnoder. Hvis treet er tomt, skal ikke p endres. */
		private BladnodeIterator()  // konstrukt�r
		{
			if (p != null)
			{
				while (p.venstre != null || p.h�yre != null)
				{
					while (p.venstre != null)
					{
						p = p.venstre;
					}
					if (p.h�yre != null)
					{
						p = p.h�yre;
					}
				}
			}
		}

		@Override
		public boolean hasNext()
		{
			return p != null;  // Denne skal ikke endres!
		}

		@Override
		/*10. Lag metoden public void remove() i klassen BladnodeIterator. Pass p� at metoden next() setter variablen
		removeOK til true og at remove() setter den til false. Pekeren q skal ligge �n bak p. Dvs. at n�r p i metoden
		next() flyttes til neste bladnode (eller til null hvis det var den siste), skal q peke p� den som p pekte p�.
		Med andre ord er det noden som q peker p� som skal fjernes. Det skal gj�res med direkte kode. Metoden fjern()
		i klassen ObligSBinTre kan ikke brukes her fordi verdien i noden q kan ogs� ligge et sted h�yere opp i treet.
		Pass p� at metoden kaster en IllegalStateException hvis det er ulovlig � kalle den.
		9. Metoden next() skal kaste en NoSuchElementException hvis det ikke er flere bladnoder igjen.
		Hvis ikke skal den gi en bladnodeverdi. Bladnodeverdiene skal komme i rekkef�lge fra venstre mot h�yre.*/
		public T next()
		{
			blad = p;
			if (endringer != anInt)
			{
				throw new ConcurrentModificationException("Treet er endret");
			}
			if (blad == null)
			{
				throw new NoSuchElementException("Ingen elementer i treet");
			}

			T verdi = p.verdi;
			p = p.forelder;
			removeOK = true;

			if (p == null)
			{
				return blad.verdi;
			}
			while ((p != null && p.h�yre == null) || (p != null && comp.compare(verdi, p.h�yre.verdi) == 0))
			{
				verdi = p.verdi;
				p = p.forelder;
			}
			if (p == null)
			{
				return blad.verdi;
			}

			if (p.h�yre != null)
			{
				p = p.h�yre;

				while (p.venstre != null || p.h�yre != null)
				{
					while (p.venstre != null)
						p = p.venstre;
					if (p.h�yre != null)
					{
						p = p.h�yre;
					}
				}
			}
			return blad.verdi;
		}

		@Override
		/*10. Lag metoden public void remove() i klassen BladnodeIterator. Pass p� at metoden next() setter variablen
		removeOK til true og at remove() setter den til false. Pekeren q skal ligge �n bak p. Dvs. at n�r p i metoden
		next() flyttes til neste bladnode (eller til null hvis det var den siste), skal q peke p� den som p pekte p�.
		Med andre ord er det noden som q peker p� som skal fjernes. Det skal gj�res med direkte kode. Metoden fjern()
		i klassen ObligSBinTre kan ikke brukes her fordi verdien i noden q kan ogs� ligge et sted h�yere opp i treet.
		Pass p� at metoden kaster en IllegalStateException hvis det er ulovlig � kalle den.
		*/
		public void remove()
		{
			if ((blad == null) || (q == null) || !removeOK)
			{
				throw new IllegalStateException("Kan ikke fjernes");
			}
			removeOK = false;

			antall--;

			if (blad == q)
			{
				blad = q = rot = null;
				return;
			}
			else if (blad.forelder != null)
			{
				if (blad.forelder.venstre != null && blad.forelder.venstre == blad)
				{
					blad.forelder.venstre = null;
				}
				else if (blad.forelder.h�yre != null && blad.forelder.h�yre == blad)
				{
					blad.forelder.h�yre = null;
				}
			}
		}

	} // BladnodeIterator

} // ObligSBinTre