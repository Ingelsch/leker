public class Oppgave1_1_2_2 
{
	public static int min(int[] a) // a er en heltallstabell 
	{
		if (a.length < 1)
			{
			throw new java.util.NoSuchElementException("Empty list");
			}

    int min = 0;
    
    for (int i = 0; i < a.length; i++)
    {
    	if (a[i] < a[min])
    	{
    		 min = i;
    	}
    }
    return min;
	}

  public static void main(String[] args) 
  {
  	int[] a = {8,3,5,7,9,6,10,2,1,4};
    int i = min(a);
    System.out.println("indeks:" + i + ", verdi: " + a[i]);
  }
}