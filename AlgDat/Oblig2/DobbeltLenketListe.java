package AlgDat.Oblig2;

import AlgDat.Oblig2.Liste;

import java.util.*;
import java.util.function.Consumer;

//Denne klassen leveres uten utskriftsmetoder

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DobbeltLenketListe<T> implements Liste<T>
{
	private static final class Node<T>   // en indre nodeklasse
	{
		// instansvariabler
		private T verdi;
		private Node<T> forrige, neste;

		// konstruktør
		private Node(T verdi, Node<T> forrige, Node<T> neste)
		{
			this.verdi = verdi;
			this.forrige = forrige;
			this.neste = neste;
		}
	}

	// instansvariabler
	private Node<T> hode;          // peker til den første i listen
	private Node<T> hale;          // peker til den siste i listen
	private int antall;            // antall noder i listen
	private int antallEndringer;   // antall endringer i listen


	// hjelpemetode
	private Node<T> finnNode(int indeks)
	//Oppgave 3
	// starte fra hode hvis indeks = antall/2, ellers starte fra hale
	{
		Node<T> hjelpeNode;

		if (indeks < (antall / 2))
		{
			hjelpeNode = hode;
			for (int i = 0; i < indeks; i++)
			{
				hjelpeNode = hjelpeNode.neste;
			}
		}
		else
		{
			hjelpeNode = hale;
			for (int i = antall - 1; i > indeks; i--)
			{
				hjelpeNode = hjelpeNode.forrige;
			}
		}

		return hjelpeNode;
	}

	// konstruktør
	public DobbeltLenketListe()
	{
		hode = hale = null;
		antall = 0;
		antallEndringer = 0;
	}

	// konstruktør
	public DobbeltLenketListe(T[] a)
	//Den skal lage en dobbeltlenket liste med verdiene fra tabellen a.
	{
		Objects.requireNonNull(a, "a er null");
		hode = hale = new Node<>(null, null, null);

		for (T verdi : a)
		{
			if (verdi != null)
			{
				hale = hale.neste = new Node<>(verdi, hale, null);
				antall++;
			}
		}

		if (antall == 0)
		{
			hode = hale = null;
		}
		else
		{
			hode = hode.neste;
			hode.forrige = null;
		}
	}

	@Override
	public int antall()
	//skal returnere antallet verdier i listen
	{
		return antall;
	}

	@Override
	public boolean tom()
	//skal returnere true/false avhengig av om listen er tom eller ikke.
	{
		return antall == 0;
	}

	@Override
	public boolean leggInn(T verdi)
	/*2a
	* Det er ikke tillatt med null-verdier i listen. Start derfor med en sjekk
	* (bruk en requireNonNull-metode fra klassen Objects). Innleggingsmetoden skal legge
	* en ny node med oppgitt verdi bakerst i listen og returnere true. Her må du skille mellom
	* to tilfeller: 1) at listen på forhånd er tom og 2) at den ikke er tom.
	* I en tom liste skal både hode og hale være null (og antall lik 0).
	* I tilfelle 1) skal både hode og hale etter innleggingen peke på den nye noden
	* (både forrige-peker og neste-peker i noden skal da være null).
	* I tilfelle 2) er det kun hale-pekeren som skal endres etter innleggingen. Pass da
	* på at forrige-peker og neste-peker i den nye noden og i den noden som oppprinnelig lå bakerst,
	 *får korrekte verdier. Husk at antallet må økes etter en innlegging.
	 * Det samme med variablen antallEndringer. Metoden skal returnere true.*/
	{
		Objects.requireNonNull(verdi, "verdi er null");
		if (tom())
		{
			hode = hale = new Node<>(verdi, null, null);
		}
		else
		{
			hale = hale.neste = new Node<>(verdi, hale, null);
		}
		antall++;
		return true;
	}

	@Override
	public void leggInn(int indeks, T verdi)
	//Oppgave 5
	// Den skal legge verdi inn i listen slik at den får indeks/posisjon indeks.
	// Husk at negative indekser og indekser større enn antall er ulovlige (indekser
	// fra og med 0 til og med antall er lovlige). Her må du passe på de fire tilfellene 1)
	// listen er tom, 2) verdien skal legges først, 3) verdien skal legges bakerst og 4) verdien
	// skal legges mellom to andre verdier. Sørg for at neste- og forrige-pekere får korekte verdier i alle noder.
	// Spesielt skal forrige-peker i den første noden være null og neste-peker i den siste noden være null.
	{
		// Sjekk for null
		Objects.requireNonNull(verdi, "Verdien kan ikke være et null-objekt");

		// indekssjekk
		if (indeks < 0)
		{
			throw new IndexOutOfBoundsException("Indeks " + indeks + " er negativ!");
		}
		else if (indeks > antall)
		{
			throw new IndexOutOfBoundsException("Indeks " + indeks + " > antall(" + antall + ") noder!");
		}

		// Tom -> først
		if (antall == 0 && indeks == 0)
		{
			hode = hale = new Node<T>(verdi, null, null);
		}

		else if (indeks == 0)
		// Legges først
		{
			hode = new Node<T>(verdi, null, hode);
			hode.neste.forrige = hode;
		}
		else if (indeks == antall)
		// Legges sist
		{
			hale = new Node<T>(verdi, hale, null);
			hale.forrige.neste = hale;
		}
		else
		{
			Node<T> node = hode;

			for (int i = 0; i < indeks; i++)
			{
				node = node.neste;
			}

			node = new Node<T>(verdi, node.forrige, node);
			node.neste.forrige = node.forrige.neste = node;
		}

		antall++;
		antallEndringer++;
	}

	@Override
	public boolean inneholder(T verdi)
	// Oppgave 3
	// Den skal returnere true hvis listen inneholder verdi og returnere false ellers.
	// Her lønner det seg å bruke et kall på metoden indeksTil som en del av koden.
	{
		return indeksTil(verdi) != -1;
	}

	@Override
	public T hent(int indeks)
	//Oppgave 3
	// ved å bruke finnNode(). Pass på at indeks sjekkes.
	// Bruk metoden indeksKontroll() som arves fra Liste2 (bruk false som parameter).
	{
		indeksKontroll(indeks, false);
		return finnNode(indeks).verdi;
	}

	@Override
	public int indeksTil(T verdi)
	//Oppgave 4
	// Den skal returnere indeksen/posisjonen til verdi hvis den finnes i listen
	// og returnere -1 hvis den ikke finnes. Her skal det ikke kastes unntak hvis verdi er null.
	// Metoden skal isteden returnere -1. Det er logisk siden null ikke finnes i listen.
	// Hvis verdi forekommer flere ganger, skal indeksen til den første av dem (fra venstre) returneres.
	// (ikke ok enda!)
	{
		Node<T> node = hode;

		boolean funnet = false;
		int indeks = 0;

		if (node != null)
		{
			do
			{
				if (node.verdi.equals(verdi))
				{
					funnet = true;
					break;
				}
				node = node.neste;
				indeks++;
			}
			while (node != null);
		}

		return node == null ? -1 : funnet ? indeks : -1;

	}

	@Override
	public T oppdater(int indeks, T nyverdi)
	//Oppgave 3
	// skal erstatte verdien på plass indeks med nyverdi og returnere det som lå der fra før.
	// Husk at indeks må sjekkes, at null-verdier ikke skal kunne legges inn og at antallEndringer skal økes.
	{
		indeksKontroll(indeks, false);
		Objects.requireNonNull(nyverdi, "Den nye verdien kan ikke være et null-objekt");

		Node<T> node = finnNode(indeks);
		T gammelVerdi = node.verdi;

		node.verdi = nyverdi;

		antallEndringer++;
		return gammelVerdi;
	}

	@Override
	public boolean fjern(T verdi)
	/*
	Oppgave 6
	skal fjerne verdi fra listen og så returnere true. Hvis det er flere forekomster av verdi er det den
	første av dem (fra venstre) som skal fjernes. Hvis verdi ikke er i listen, skal metoden returnere false.
	Her skal det ikke kastes unntak hvis verdi er null. Metoden skal isteden returnere false. Det er logisk
	siden null ikke finnes i listen.
	Den skal være så effektive som mulig.
	Pass på tilfellene 1) den første fjernes, 2) den siste fjernes og
	3) en verdi mellom to andre fjernes. Alle neste- og forrige-pekere må være korrekte etter fjerningen.
	Variabelen antall skal også reduseres og variabelen antallEndringer økes. Sjekk også tilfellet at listen blir tom
	etter fjerningen, blir korrekt behandlet.
	Bruk metodene toString() og omvendtString() til å sjekke at alle pekerne er satt riktig.
	*/
	{
		if (verdi == null)
		{
			return false;
		}
		Node<T> n = hode;
		while (n != null)
		{
			if (verdi.equals(n.verdi))
			{
				if (n.forrige == null && n.neste == null)
				{
					hode = null;
					hale = null;
				}
				else if (n.forrige == null)
				{
					hode = n.neste;
					n.neste.forrige = null;
				}
				else if (n.neste == null)
				{
					hale = n.forrige;
					n.forrige.neste = null;
				}
				else
				{
					n.forrige.neste = n.neste;
					n.neste.forrige = n.forrige;
				}
				antall--;
				antallEndringer++;
				return true;
			}
			n = n.neste;
		}
		return false;
	}

	@Override
	public T fjern(int indeks)
	/*
	Oppgave 6
	skal fjerne (og returnere) verdien på posisjon indeks (som først må sjekkes).
	Den skal være så effektive som mulig.
	Pass på tilfellene 1) den første fjernes, 2) den siste fjernes og
	3) en verdi mellom to andre fjernes. Alle neste- og forrige-pekere må være korrekte etter fjerningen.
	Variabelen antall skal også reduseres og variabelen antallEndringer økes. Sjekk også tilfellet at listen blir tom
	etter fjerningen, blir korrekt behandlet.
	Bruk metodene toString() og omvendtString() til å sjekke at alle pekerne er satt riktig.
	*/
	{
		indeksKontroll(indeks, false);
		Node<T> n = finnNode(indeks);
		if (hale == hode)
		{
			hode = hale = null;
		}
		else if (n == hale)
		{
			hale = hale.forrige;
			hale.neste = null;
		}
		else if (n == hode)
		{
			hode = hode.neste;
			hode.forrige = null;
		}
		else
		{
			n.neste.forrige = n.forrige;
			n.forrige.neste = n.neste;
		}
		antall--;
		antallEndringer++;
		return n.verdi;
	}

	@Override
	public void nullstill()
	/*Oppgave 7
	Den skal «tømme» listen og nulle alt slik at «søppeltømmeren» kan hente alt som ikke lenger brukes.
	Kod den på to måter og velg den som er mest effektiv (gjør tidsmålinger):
	1 måte: Start i hode og gå mot hale ved hjelpe pekeren neste. For hver node «nulles» nodeverdien og alle nodens pekere.
	Til slutt settes både hode og hale til null, antall til 0 og antallEndringer økes.
	2. måte: Lag en løkke som inneholder metodekallet fjern(0) (den første noden fjernes) og som går inntil listen er tom*/
	{
		for (Node<T> temp = hode; temp != null; temp = temp.neste)
		{
			temp.verdi = null;
			temp.forrige = temp.neste = null;
		}

		hode = hale = null;
		antall = 0;
		antallEndringer++;

	}

	@Override
	public String toString()
	//2b
	{
		StringJoiner s = new StringJoiner(", ", "[", "]");
		Node<T> p = hode;

		while (p != null)
		{
			s.add(p.verdi.toString());
			p = p.neste;
		}
		return s.toString();
	}

	public String omvendtString()
	//2b
	{
		StringJoiner s = new StringJoiner(", ", "[", "]");
		Node<T> p = hale;

		while (p != null)
		{
			s.add(p.verdi.toString());
			p = p.forrige;
		}
		return s.toString();
	}

	@Override
	public Iterator<T> iterator()
	/*Oppgave 8b) Den skal returnere en iterator som er slik at første kall på next() vil gi den første verdien i listen.*/
	{
		return new DobbeltLenketListeIterator();
	}

	public Iterator<T> iterator(int indeks)
	/*Oppgave 8d) Det må først sjekkes at indeksen er lovlig. Den skal returnere en iterator som er slik at første
	kall på next() vil gi verdien med oppgitt indeks. Hint: Bruke konstruktøren i punkt c).*/
	{
		indeksKontroll(indeks, false);
		return new DobbeltLenketListeIterator(indeks);
	}

	@Override
	public void forEach(Consumer<? super T> action)
	/*Oppgave 9a)
	* Omkod metoden void forEach(Consumer<? super T> handling), dvs. lag din egen versjon
	* av den i klassen DobbeltLenketListe. Den skal traversere pekerkjeden ved å følge pekere
	* fra hode til hale og for hver verdi utføre «handling».
	* Metoden skal kaste en NullPointerException hvis handling er null. I din løsning skal du ikke
	* bruke iteratoren.*/

	{
		if (action == null)
		{
			throw new NullPointerException();
		}
		Node<T> n = hode;
		while (n != null)
		{
			action.accept(n.verdi);
			n = n.neste;
		}
	}

	@Override
	public boolean fjernHvis(Predicate<? super T> p)
	/*Oppg 10b
	*  Metoden boolean fjernHvis(Predicate<? super T> sjekk) er en default-metode
	*  i grensesnittet Beholder. Den vil virke så sant metoden remove() i iteratorklassen er kodet.
	 *  Her er et eksempel på hvordan den kan brukes. Sjekk at det virker.
	 *  Lag deretter et predikat som gjør at de navnene som slutter på r fjernes.*/
	{
		Node<T> n = hode;
		boolean noeFjernet = false;
		while (n != null)
		{
			if (p.test(n.verdi))
			{
				fjern(n.verdi);
				noeFjernet = true;
			}
			n = n.neste;
		}
		return noeFjernet;
	}

	private class DobbeltLenketListeIterator implements Iterator<T>
	{
		private Node<T> denne;
		private boolean fjernOK;
		private int forventetAntallEndringer;

		private DobbeltLenketListeIterator()
		{
			denne = hode;     // denne starter på den første i listen
			fjernOK = false;  // blir sann når next() kalles
			forventetAntallEndringer = antallEndringer;  // teller endringer
		}

		private DobbeltLenketListeIterator(int indeks)
		/*Oppgave 8c) Den skal være lik den andre konstruktøren borsett fra at her skal pekeren denne
		settes til å peke på den noden som har den gitte indeksen.*/
		{
			denne = finnNode(indeks);
			fjernOK = false;
			forventetAntallEndringer = antallEndringer;
		}

		@Override
		public boolean hasNext()
		{
			return denne != null;  // denne koden skal ikke endres!
		}

		@Override
		public T next()
		/*oppgave 8a)Den skal først sjekke om forventetAntallEndringer er lik antallEndringer.
		Hvis ikke, kastes en ConcurrentModificationException. Videre kastes en NoSuchElementException
		hvis det ikke er flere igjen i listen (dvs. hvis hasNext() ikke er sann/true). Deretter settes fjernOK til
		sann/true, verdien til denne returneres og denne flyttes til den neste node.
		*/
		{
			if (forventetAntallEndringer != antallEndringer)
			{
				throw new ConcurrentModificationException();
			}
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			Node<T> temp = denne;
			denne = denne.neste;
			fjernOK = true;
			forventetAntallEndringer++;
			return temp.verdi;
		}


		@Override
		public void remove()
		/*Oppgave 10
			a) Lag metoden void remove() i iteratorklassen. Her kan du ikke bruke noen av de ordinære fjern-metodene.
			For det først vet vi ikke hvilken indeks det er snakk om her og for andre kan verdien som skal fjernes,
			forkomme flere steder og dermed kan gal verdi bli fjernet. Her må remove() kodes direkte.

			Hvis det ikke er tillatt å kalle denne metoden, skal det kastes en IllegalStateException.
			Hvis antallEndringer og forventetAntallEndringer er forskjellige, kastes en
			Concurrent-ModificationException. Hvis disse hindrene passeres, settes fjernOK til usann/false.
			Så skal noden rett til venstre for p fjernes. Den finner vi lett siden det går en peker dit.
			Her må en passe på alle tilfellene.

			Hvis den som skal fjernes er eneste verdi (antall == 1), så nulles hode og hale.
			Hvis den siste skal fjernes (p == null), så må hale oppdateres.
			Hvis den første skal fjernes (p.forrige == hode), så må hode oppdateres.
			Hvis en node inne i listen skal fjernes (noden p.forrige), så må pekerne i nodene på hver side oppdateres.
			Til slutt reduseres antall og både antallEndringer og forventetAntallEndringer økes.*/
		{
			if (!fjernOK)
			{
				throw new IllegalStateException();
			}
			else if (forventetAntallEndringer != antallEndringer)
			{
				throw new ConcurrentModificationException();
			}
			else
			{
				fjernOK = false;
				if (antall == 1)
				{
					hode = hale = null;
				}
				else if (denne == null)
				{
					hale = hale.forrige;
					hale.neste = null;
				}
				else if (denne.forrige == hode)
				{
					hode = hode.neste;
					hode.forrige = null;
				}
				else
				{
					denne.forrige.forrige.neste = denne;
					denne.forrige = denne.forrige.forrige;
				}
				antall--;
				forventetAntallEndringer = ++antallEndringer;
			}
		}

		@Override
		public void forEachRemaining(Consumer<? super T> action)
		/*Oppg 9b
		* Overskriv metoden void forEachRemaining(Consumer<? super T> handling), dvs.
		* lag din egen versjon av den i iteratorklassen DobbeltLenketListeIterator. Den skal
		* traversere pekerkjeden (ved å følge pekere) fra og med der denne står og til hale og
		* for hver verdi utføre «handling». Metoden skal kaste en NullPointerException hvis handling
		* er null.*/
		{
			if (action == null)
			{
				throw new NullPointerException();
			}
			while (hasNext()) action.accept(next());

		} // DobbeltLenketListeIterator

	} // DobbeltLenketListen
}