package AlgDat.Oblig3;

/**
 * Created by inge on 04.11.2015.
 */

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

    private final Comparator<? super T> comp;       // komparator

    public ObligSBinTre(Comparator<? super T> c)    // konstrukt�r
    {
        rot = null;
        antall = 0;
        comp = c;
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
    /*5. Der kan du kopiere Programkode 5.2 8 d), men i tillegg m� du gj�re de
    endringene som trengs for at pekeren forelder f�r korrekt verdi i alle
    noder etter en fjerning. */
    public boolean fjern(T verdi)  // h�rer til klassen SBinTre
    {
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal v�re forelder til p

        while (p != null)            // leter etter verdi
            {
                int cmp = comp.compare(verdi,p.verdi);      // sammenligner
                if (cmp < 0) { q = p; p = p.venstre; }      // g�r til venstre
                else if (cmp > 0) { q = p; p = p.h�yre; }   // g�r til h�yre
                else break;    // den s�kte verdien ligger i p
            }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.h�yre == null)  // Tilfelle 1) og 2)
            {
                Node<T> b = p.venstre != null ? p.venstre : p.h�yre;  // b for barn
                if (p == rot) rot = b;
                else if (p == q.venstre) q.venstre = b;
                else q.h�yre = b;
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

                if (s != p) s.venstre = r.h�yre;
                else s.h�yre = r.h�yre;
            }

        antall--;   // det er n� �n node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi)
    {
        throw new UnsupportedOperationException("Ikke kodet enn�!");
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

    @Override
    public void nullstill()
    {

        throw new UnsupportedOperationException("Ikke kodet enn�!");
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
                while(p.venstre != null)
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
                for (; p.h�yre != null; p = p.h�yre) stakk.push(p);

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
                        else break;          // stakken er tom - vi er ferdig

                    } // while
            }
        return s.toString();
    }

    public String h�yreGren()
    {
        throw new UnsupportedOperationException("Ikke kodet enn�!");
    }

    public String lengstGren()
    {
        throw new UnsupportedOperationException("Ikke kodet enn�!");
    }

    public String[] grener()
    {
        throw new UnsupportedOperationException("Ikke kodet enn�!");
    }

    public String bladnodeverdier()
    {
        throw new UnsupportedOperationException("Ikke kodet enn�!");
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

        private BladnodeIterator()  // konstrukt�r
        {
            throw new UnsupportedOperationException("Ikke kodet enn�!");
        }

        @Override
        public boolean hasNext()
        {
            return p != null;  // Denne skal ikke endres!
        }

        @Override
        public T next()
        {
            throw new UnsupportedOperationException("Ikke kodet enn�!");
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke kodet enn�!");
        }

    } // BladnodeIterator

} // ObligSBinTre