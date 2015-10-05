package no.hioa.s198749;

public class Diverse
{

	public static boolean FeilSvar(int num)
	//Gir feil svar for num < 0
	{
		return num % 2 == 1;
	}

	//Er tallet oddetall?
	public static boolean oddOrNot(int num)
	//Denne er mest riktig, stemmer for num < 0 i tillegg
	{
		return (num & 1) != 0;
	}

    public static void main(String... args)
    {
        int num1 = 1;
		int num2 = -35;

		boolean tre = FeilSvar(num1);
		boolean fire = FeilSvar(num2);
		boolean en = oddOrNot(num1);
		boolean to = oddOrNot(num2);

		System.out.println("Er tallet oddetall?");
		System.out.println(num1 + "\n " + tre);
		System.out.println(num2+ "\n " + fire);
		System.out.println("Dette stemmer jo ikke... for nummeret er negativt\n");


		System.out.println("Men her da?");
		System.out.println(num1 + "\n " + en);
		System.out.println(num2+ "\n " + to );
		System.out.println("Dette stemmer jo bra\n");

		int test1 = 'H';
		int test2 = 'A';
		String test3 = "H";
		String test4 = "A";

		System.out.println(test3 + test4);

		String s = "Tallene med enkle gåseøine gjores om til ascii-tegn\n";
		s += "Tallene tilsvarer \'" + test1 + "\' og \'" + test2 + "\'";
		s += "\nDet adderes og blir: ";
		System.out.print(s);
		System.out.println(test1 + test2);



    /*System.out.println("Binaertraer");
    System.out.println("Et perfekt tre med hoeyde h har 2^(h+1)-1 noder.");
    System.out.println("Et komplett binaertre med n noder har hoeyde h = ?log2(n)?");
	System.out.println("Definisjon 1.2.11 a)  Et binaertre kalles perfekt (eng: a perfect binary tree) hvis alle nivaaene i treet inneholder saa mange noder som det er plass til.");
	System.out.println("Definisjon 1.2.11 b)  Et binaertre kalles komplett (eng: a complete binary tree) hvis hvert nivaa, " +
      "unntatt det siste (nederste) nivaaet, \ninneholder saa mange noder som det er plass til. Paa siste nivaa kan det vaere faerre enn det er plass til, \nmen det maa ligge tett med noder fra venstre.");
	System.out.println("Definisjon 1.2.11 c)  Et binaertre kalles fullt (eng: a full binary tree) hvis hver node har enten to eller ingen barn.");
	System.out.println("Definisjon 1.2.11 d)  Et binaertre kalles et maksimumstre (eng: a max tree) hvis hver node, bortsett fra rotnoden, \nhar en verdi som er mindre enn eller lik verdien i nodens forelder.");
	System.out.println("Definisjon 1.2.11 e)  Et binaertre kalles en maksimumsheap (eng: a max heap) hvis det er et komplett maksimumstre.");*/


	}
}