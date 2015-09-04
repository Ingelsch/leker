package algDat;

public class Oppgave1_1_2_2 
{
	public static int min(int[] a) // a er en heltallstabell 
	{
		if (a.length < 1)
			{
			throw new java.util.NoSuchElementException("Empty list");
			}

    int min = 0;
    int i = 0;
    for (; i < a.length; i++)
    { 
    	if (a[i] < a[min]) 
    	{
    		 min = i;
    	}
    }
    return min;
	}


}