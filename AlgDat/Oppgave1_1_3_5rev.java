package AlgDat;

public class Oppgave1_1_3_5rev //fasit
{
	public static int[] minmax(int[] a) 
	{
    /* Den skal ved hjelp av en int-tabell med lengde 2 returnere
        posisjonene til minste og største verdi i tabellen a. Hvis du har
        funnet at m1 er posisjonen til den minste og m2 til den største,
        kan du returnere tabellen b definert ved:
            int[] b = {m1, m2};
    */

		int mi = 0, minverdi = a[0]; // 3
	    int ma = 0, maksverdi = a[0]; // 3

	    int verdi = 0; // 1

	    for (int i = 1; i < a.length; i++) // 1 + n + (n-1) = 2n
	    {
	      verdi = a[i]; // 2

	      if (verdi > maksverdi) { maksverdi = verdi; ma = i;} // (n-1) + 2x
	      else if (verdi < minverdi) {minverdi = verdi; mi = i;} // (n-1) + 2x
	    }//4n

	    return new int[]{mi,ma}; // 2
	}
}
//Hvor mange sammenligninger bruker metoden din?
// 2n-2		4x + 4n + 9