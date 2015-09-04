package algDat;

public class Oppgave1_1_3_5
{	
	public static int[] minmax(int[] a) 
	{
    /* Den skal ved hjelp av en int-tabell med lengde 2 returnere
        posisjonene til minste og største verdi i tabellen a. Hvis du har
        funnet at m1 er posisjonen til den minste og m2 til den største,
        kan du returnere tabellen b definert ved:
            int[] b = {m1, m2};
    */

	    int m1 = 0, m2 = 0; //2
	
	    for (int i = 0; i < a.length; i++) //1 + n + (n-1) = 2n
	    {
	        if (a[i] < a[m1]) // (n-1)
        	{
        		m1 = i; // x
        	}
	        	
	        if (a[i] > a[m2]) // (n-1)
	        {
	        	m2 = i; // x
	        }
	    }
       
	    return new int[] {m1, m2}; // 2
	}
}
//Hvor mange sammenligninger bruker metoden din?
// 2(n-1) (grunnleggende operasjoner= 