package no.hioa.s198749;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println("Bin�artr�r");
        System.out.println("Et perfekt tre med h�yde h har 2^(h+1)-1 noder.");
        System.out.println("Et komplett bin�rtre med n noder har h�yde h = ?log2(n)?");
		System.out.println("Definisjon 1.2.11 a)  Et bin�rtre kalles perfekt (eng: a perfect binary tree) hvis alle niv�ene i treet inneholder s� mange noder som det er plass til.");
		System.out.println("Definisjon 1.2.11 b)  Et bin�rtre kalles komplett (eng: a complete binary tree) hvis hvert niv�, " +
				"unntatt det siste (nederste) niv�et, \ninneholder s� mange noder som det er plass til. P� siste niv� kan det v�re f�rre enn det er plass til, \nmen det m� ligge tett med noder fra venstre.");
		System.out.println("Definisjon 1.2.11 c)  Et bin�rtre kalles fullt (eng: a full binary tree) hvis hver node har enten to eller ingen barn.");
		System.out.println("Definisjon 1.2.11 d)  Et bin�rtre kalles et maksimumstre (eng: a max tree) hvis hver node, bortsett fra rotnoden, \nhar en verdi som er mindre enn eller lik verdien i nodens forelder.");
		System.out.println("Definisjon 1.2.11 e)  Et bin�rtre kalles en maksimumsheap (eng: a max heap) hvis det er et komplett maksimumstre.");

    }
}