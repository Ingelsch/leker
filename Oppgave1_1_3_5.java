
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

    int min = 0, max = 0;

    for (int i = 0; i < a.length; i++) {
        if (a[i] < a[min]) min = i;
        if (a[i] > a[max]) max = i;
    }
    return new int[] {min, max};
	}
	public static void main(String[] args)
	{
    int[] a = {8,3,5,7,9,6,10,2,1,4};
    System.out.println("Her er posisjonene til minste og storste verdi: \n[min, maks]");
    System.out.println(java.util.Arrays.toString(minmax(a)));

	}

}
