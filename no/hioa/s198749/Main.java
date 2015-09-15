package no.hioa.s198749;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println("Binæartrær");
        System.out.println("Et perfekt tre med høyde h har 2^(h+1)-1 noder.");
        System.out.println("Et komplett binærtre med n noder har høyde h = ?log2(n)?");
		System.out.println("Definisjon 1.2.11 a)  Et binærtre kalles perfekt (eng: a perfect binary tree) hvis alle nivåene i treet inneholder så mange noder som det er plass til.");
		System.out.println("Definisjon 1.2.11 b)  Et binærtre kalles komplett (eng: a complete binary tree) hvis hvert nivå, " +
				"unntatt det siste (nederste) nivået, \ninneholder så mange noder som det er plass til. På siste nivå kan det være færre enn det er plass til, \nmen det må ligge tett med noder fra venstre.");
		System.out.println("Definisjon 1.2.11 c)  Et binærtre kalles fullt (eng: a full binary tree) hvis hver node har enten to eller ingen barn.");
		System.out.println("Definisjon 1.2.11 d)  Et binærtre kalles et maksimumstre (eng: a max tree) hvis hver node, bortsett fra rotnoden, \nhar en verdi som er mindre enn eller lik verdien i nodens forelder.");
		System.out.println("Definisjon 1.2.11 e)  Et binærtre kalles en maksimumsheap (eng: a max heap) hvis det er et komplett maksimumstre.");

    }
}